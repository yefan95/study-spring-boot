package com.yefan.study.spring.webflux.repository;

import com.yefan.study.spring.webflux.bean.City;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends ReactiveMongoRepository<City,Long>{
}
