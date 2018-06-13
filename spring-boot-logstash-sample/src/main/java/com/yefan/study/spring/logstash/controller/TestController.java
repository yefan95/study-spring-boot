package com.yefan.study.spring.logstash.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping
@Controller
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping(value = "/info")
    @ResponseBody
    public String info(@RequestParam("msg") String msg) {
        logger.info(msg);
        return "ok";
    }

    @GetMapping(value = "/debug")
    @ResponseBody
    public String debug(@RequestParam("msg") String msg) {
        logger.debug(msg);
        return "ok";
    }

    @GetMapping(value = "/fail")
    @ResponseBody
    public String error(@RequestParam("msg") String msg) {
        logger.error(msg);
        return "ok";
    }
}
