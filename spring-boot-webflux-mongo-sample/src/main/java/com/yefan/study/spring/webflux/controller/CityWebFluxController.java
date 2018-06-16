package com.yefan.study.spring.webflux.controller;

import com.yefan.study.spring.webflux.service.CityService;
import com.yefan.study.spring.webflux.bean.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/city")
public class CityWebFluxController {

    @Autowired
    private CityService cityService;

    @GetMapping(value = "/{id}")
    public Mono<City> findCityById(@PathVariable("id") Long id) {
        return cityService.findCityById(id);
    }

    @GetMapping(value = "/all")
    public Flux<City> findAllCity() {
        return cityService.findAllCity();
    }

    @PostMapping(value = "save")
    public Mono<City> saveCity(@RequestBody City city) {
        return cityService.save(city);
    }

    @PutMapping(value = "/update")
    public Mono<City> modifyCity(@RequestBody City city) {
        return cityService.modifyCity(city);
    }

    @PostMapping(value = "/{id}")
    public Mono<Long> deleteCity(@PathVariable("id") Long id) {
        return cityService.deleteCity(id);
    }


}
