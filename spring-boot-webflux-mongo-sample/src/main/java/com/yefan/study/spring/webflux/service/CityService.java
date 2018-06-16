package com.yefan.study.spring.webflux.service;

import com.yefan.study.spring.webflux.bean.City;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CityService {

    Mono<City> save(City city);

    Mono<City> findCityById(Long id);

    Flux<City> findAllCity();

    Mono<City> modifyCity(City city);

    Mono<Long> deleteCity(Long id);

}
