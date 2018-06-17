package com.yefan.study.spring.reactive.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yefan.study.spring.reactive.domain.Person;
import com.yefan.study.spring.reactive.exception.ForbiddenResourceOverrideException;
import com.yefan.study.spring.reactive.exception.InvalidRequestBodyException;
import com.yefan.study.spring.reactive.exception.NotFoundResourceException;
import com.yefan.study.spring.reactive.json.JsonWriter;
import com.yefan.study.spring.reactive.json.reader.PersonReader;
import com.yefan.study.spring.reactive.service.PersonService;
import com.yefan.study.spring.reactive.utils.MonoUtils;
import com.yefan.study.spring.reactive.utils.Responses;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.xml.ws.WebEndpoint;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterConfig {

    // Path variables
    public static final String PERSON_ID_PATH_VARIABLE = "id";

    // Paths
    public static final String BASE_PATH = "";
    public static final String ONE_PERSON = BASE_PATH + "/{" + PERSON_ID_PATH_VARIABLE + "}";

    private final PersonService personService;

    public final RouterFunction<ServerResponse> routerFunction;

    public RouterConfig(PersonService personService) {
        this.personService = personService;

        this.routerFunction = RouterFunctions.
                route(GET(BASE_PATH), this::getAllPersons).
                andRoute(POST(BASE_PATH).and(contentType(MediaType.APPLICATION_JSON)), this::createOnePerson).
                andRoute(GET(ONE_PERSON), this::getOnePersonById).
                andRoute(POST(ONE_PERSON).and(contentType(MediaType.APPLICATION_JSON)), this::updateOnePersonById).
                andRoute(DELETE(ONE_PERSON), this::deleteOnePersonById);
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunctions() {
        return routerFunction;
    }

    private Mono<ServerResponse> getAllPersons(ServerRequest request) {
        return this.personService.
                list().
                collectList().
                flatMap(JsonWriter::write).
                flatMap(Responses::ok).
                onErrorResume(JsonProcessingException.class, Responses::internalServerError).
                subscribeOn(Config.APPLICATION_SCHEDULER);
    }

    private Mono<ServerResponse> getOnePersonById(ServerRequest request) {
        return this.personService.
                findById(request.pathVariable(PERSON_ID_PATH_VARIABLE)).
                flatMap(JsonWriter::write).
                flatMap(Responses::ok).
                onErrorResume(NotFoundResourceException.class, Responses::notFound).
                onErrorResume(JsonProcessingException.class, Responses::internalServerError).
                subscribeOn(Config.APPLICATION_SCHEDULER);
    }

    private Mono<ServerResponse> updateOnePersonById(ServerRequest request) {
        return request.bodyToMono(String.class).
                flatMap(this::readPersonFromRequestBody).
                flatMap(this.personService::updateOneById).
                flatMap((success) -> success ? Responses.noContent() : Responses.internalServerError()).
                onErrorResume(NotFoundResourceException.class, Responses::notFound).
                onErrorResume(InvalidRequestBodyException.class, Responses::badRequest).
                subscribeOn(Config.APPLICATION_SCHEDULER);
    }

    private Mono<ServerResponse> deleteOnePersonById(ServerRequest request) {
        return this.personService.
                deleteOneById(request.pathVariable(PERSON_ID_PATH_VARIABLE)).
                flatMap((success) -> success ? Responses.noContent() : Responses.internalServerError()).
                onErrorResume(NotFoundResourceException.class, Responses::notFound).
                subscribeOn(Config.APPLICATION_SCHEDULER);
    }

    private Mono<ServerResponse> createOnePerson(ServerRequest request) {
        return request.bodyToMono(String.class).
                flatMap(this::readPersonFromRequestBody).
                flatMap(this.personService::createOne).
                flatMap((success) -> success ? Responses.noContent() : Responses.internalServerError()).
                onErrorResume(NotFoundResourceException.class, Responses::notFound).
                onErrorResume(ForbiddenResourceOverrideException.class, Responses::forbidden).
                onErrorResume(InvalidRequestBodyException.class, Responses::badRequest).
                subscribeOn(Config.APPLICATION_SCHEDULER);
    }

    private Mono<Person> readPersonFromRequestBody(String body) {
        return MonoUtils.fromOptional(
                PersonReader.read(body),
                () -> new InvalidRequestBodyException(Person.class)
        );
    }

}
