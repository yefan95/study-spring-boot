package com.yefan.study.spring.scheduling;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan(basePackages = {"com.yefan.study.spring.scheduling.dao"})
@EnableScheduling
@SpringBootApplication
public class SpringBootSchedulingTaskSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSchedulingTaskSampleApplication.class, args);
    }
}
