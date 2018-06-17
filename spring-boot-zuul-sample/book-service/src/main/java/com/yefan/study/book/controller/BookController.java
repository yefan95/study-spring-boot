package com.yefan.study.book.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @RequestMapping(value = "/available")
    public String available() {
        System.out.println("available");
        return "Spring in Action";
    }

    @RequestMapping(value = "/checked-out")
    public String checkedOut() {
        System.out.println("checked-out");
        return "Spring Boot in Action";
    }

}
