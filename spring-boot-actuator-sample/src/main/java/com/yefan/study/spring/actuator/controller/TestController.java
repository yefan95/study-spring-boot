package com.yefan.study.spring.actuator.controller;

import com.yefan.study.spring.actuator.metrics.CounterService;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private CounterService counterService;

    @Autowired
    private MeterRegistry registry;

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags("region", "us-east-1");
    }

    @ReadOperation
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        registry.gauge("region", 200);
        return "hello";
    }

    @RequestMapping(value = "/increment")
    public void increment() {
        counterService.handleMessage("increment", 1);
    }

    @RequestMapping(value = "/decrement")
    public void decrement() {
        counterService.handleMessage("decrement", -1);
    }

    @RequestMapping(value = "/reset")
    public void rest() {
        counterService.handleMessage("reset", 0);
    }


}
