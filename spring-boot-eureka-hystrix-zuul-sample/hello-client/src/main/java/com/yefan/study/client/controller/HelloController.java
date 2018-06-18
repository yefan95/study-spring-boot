package com.yefan.study.client.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "")
public class HelloController {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback", groupKey = "Hello",
            commandKey = "hello",
            threadPoolKey = "helloThread"
    )
    @GetMapping(value = "/hello")
    public String hello() {
//        String url = "http://hello-service/hello";
        String url = "http://localhost:8079/api/server/hello";
        return restTemplate.getForObject(url, String.class) + " including client";
    }

    public String fallback(Throwable hystrixCommand) {
        System.out.println(hystrixCommand.toString());
        return "Fall Back Hello world";
    }


}
