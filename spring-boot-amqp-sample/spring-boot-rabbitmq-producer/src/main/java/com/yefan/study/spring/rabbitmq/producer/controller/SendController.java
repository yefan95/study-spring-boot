package com.yefan.study.spring.rabbitmq.producer.controller;

import com.yefan.study.spring.rabbitmq.bean.User;
import com.yefan.study.spring.rabbitmq.producer.sender.BeanSender;
import com.yefan.study.spring.rabbitmq.producer.sender.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/send")
public class SendController {


    private final static Logger logger = LoggerFactory.getLogger(SendController.class);

    @Autowired
    private MessageSender messageSender;
    @Autowired
    private BeanSender beanSender;

    @GetMapping(value = "/message")
    @ResponseBody
    public String sendMessage(@RequestParam("message") String message) {
        messageSender.send(message);
        return "ok";
    }

    @GetMapping(value = "/bean")
    @ResponseBody
    public String sendBean() {
        User user = new User();
        user.setUserName("admin");
        user.setPassWord("admin");
        user.setAge(22);
        beanSender.send(user);
        return "ok";
    }

}
