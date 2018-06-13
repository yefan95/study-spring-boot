package com.yefan.study.spring.actuator.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class CounterService {

    private final Counter counter;

    public CounterService(MeterRegistry registry) {
        this.counter = registry.counter("received.messages");
    }

    public void handleMessage(String message, int count) {
        System.out.println(message);
        this.counter.increment(count);
    }


}
