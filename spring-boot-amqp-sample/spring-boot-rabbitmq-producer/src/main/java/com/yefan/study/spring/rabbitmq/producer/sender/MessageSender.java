package com.yefan.study.spring.rabbitmq.producer.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    private final static Logger logger = LoggerFactory.getLogger(MessageSender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String message) {
        logger.info("Send Message: {}", message);
        this.rabbitTemplate.convertAndSend("helloQueue", message);
    }

}
