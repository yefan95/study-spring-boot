package com.yefan.study.spring.rabbitmq.customer.receiver;

import com.yefan.study.spring.rabbitmq.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "beanQueue")
public class BeanReceiver {

    private final static Logger logger = LoggerFactory.getLogger(BeanReceiver.class);

    @RabbitHandler
    public void process(User user) {
        logger.info("Receiver Message: {}", user);
    }

}
