package com.yefan.study.spring.reactive.service.impl;

import com.yefan.study.spring.reactive.config.Config;
import com.yefan.study.spring.reactive.domain.Person;
import com.yefan.study.spring.reactive.exception.ForbiddenResourceOverrideException;
import com.yefan.study.spring.reactive.exception.NotFoundResourceException;
import com.yefan.study.spring.reactive.service.PersonService;
import com.yefan.study.spring.reactive.utils.MonoUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PersonServiceImpl implements PersonService {

    private final List<Person> persons;

    public PersonServiceImpl() {
        this.persons = Stream.of(
                new Person("1", "Foo", "Bar", 50),
                new Person("2", "Toto", "Tuto", 30),
                new Person("3", "John", "Doe", 30),
                new Person("4", "John", "Duff", 16)
        ).collect(Collectors.toCollection(CopyOnWriteArrayList::new));
    }

    @Override
    public Flux<Person> list() {
        return Flux.fromIterable(this.persons).subscribeOn(Config.APPLICATION_SCHEDULER);
    }

    @Override
    public Mono<Person> findById(String id) {
        // to make sure nothing gets executed until one subscribes to the mono
        return Mono.fromSupplier(() -> this.persons).
                flatMap((persons) -> MonoUtils.fromOptionalWithNotFoundException(
                        persons.stream().filter((person) -> person.getId().equals(id)).findFirst(),
                        "person " + id
                )).subscribeOn(Config.APPLICATION_SCHEDULER);
    }

    @Override
    public Mono<Boolean> deleteOneById(String id) {
        return this.findById(id).
                map(this.persons::remove).
                flatMap((succeeded) ->
                        succeeded ? Mono.just(true) : Mono.error(new NotFoundResourceException("person " + id))
                ).
                subscribeOn(Config.APPLICATION_SCHEDULER);
    }

    @Override
    public Mono<Boolean> createOne(Person person) {
        return this.findById(person.getId()).
                flatMap((notUsed) -> Mono.<Boolean>error(new ForbiddenResourceOverrideException())).
                onErrorResume(
                        NotFoundResourceException.class,
                        (notUsedException) -> Mono.fromCallable(() -> {
                            this.persons.add(person);
                            return true;
                        })
                ).subscribeOn(Config.APPLICATION_SCHEDULER);
    }

    @Override
    public Mono<Boolean> updateOneById(Person person) {
        return this.findById(person.getId()).
                map((previous) -> {
                    this.persons.remove(previous);
                    this.persons.add(person);
                    return true;
                }).
                subscribeOn(Config.APPLICATION_SCHEDULER);
    }


}
