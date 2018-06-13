package com.yefan.study.spring.rabbitmq.customer.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "helloQueue")
public class MessageReceiver {

    private final static Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    @RabbitHandler
    public void process(String message) {
        logger.info("Receiver Message: {}", message);
    }


}
