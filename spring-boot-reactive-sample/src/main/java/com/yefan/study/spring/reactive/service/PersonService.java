package com.yefan.study.spring.reactive.service;

import com.yefan.study.spring.reactive.domain.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {

    Flux<Person> list();

    Mono<Person> findById(String id);

    Mono<Boolean> deleteOneById(String id);

    Mono<Boolean> createOne(Person person);

    Mono<Boolean> updateOneById(Person person);

}
