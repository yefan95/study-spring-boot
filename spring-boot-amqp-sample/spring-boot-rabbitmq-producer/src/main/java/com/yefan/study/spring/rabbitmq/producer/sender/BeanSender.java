package com.yefan.study.spring.rabbitmq.producer.sender;

import com.yefan.study.spring.rabbitmq.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanSender {

    private final static Logger logger = LoggerFactory.getLogger(BeanSender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(User user) {
        logger.info("Send Message: {}", user);
        this.rabbitTemplate.convertAndSend("beanQueue", user);
    }

}
