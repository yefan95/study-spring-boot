package com.yefan.study.spring.webflux.config;

import com.yefan.study.spring.webflux.bean.City;
import com.yefan.study.spring.webflux.service.CityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicReference;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Routes {

    private CityService cityService;

    public Routes(CityService cityService) {
        this.cityService = cityService;
    }

    @Bean
    public RouterFunction<?> routerFunction() {
        return route(GET("/api/city").and(accept(MediaType.ALL)),
                serverRequest -> {
                    Flux<City> city = cityService.findAllCity();
                    return ServerResponse.ok().body(fromPublisher(city, City.class));
                })
                .and(route(GET("/api/city/{id}").and(accept(MediaType.APPLICATION_JSON)),
                        serverRequest -> {
                            String id = serverRequest.pathVariable("id");
                            Mono<City> city = cityService.findCityById(Long.valueOf(id));
                            return ServerResponse.ok().body(fromPublisher(city, City.class));
                        })
                )
                .and(route(POST("/api/city/save").and(accept(MediaType.APPLICATION_JSON)),
                        serverRequest -> {
                            Mono<City> cityMono = serverRequest.bodyToMono(City.class);
                            AtomicReference<City> city = new AtomicReference<>();
                            cityMono.subscribe(value -> {
                                city.set(value);
                            });
                            return ServerResponse.ok().body(BodyInserters.fromPublisher(cityService.save(city.get()), City.class));
                        }));
    }

}
