package com.yefan.study.spring.elastic.service;

import com.yefan.study.spring.elastic.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void save() {
        for (long i = 0; i < 10; i++) {
            Book book = new Book(i, "编程之美" + i, "张三", "电子工业出版社", new Date());
            bookService.save(book);
        }
    }

    @Test
    public void findByAuthor() {
        String author = "张三";
        Stream<Book> stream = bookService.findByAuthor(author);
//        System.out.println(stream.count());
        stream.forEach(action -> {
            System.out.println(action.toString());
        });
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = PageRequest.of(0, 4);
        Page<Book> bookPage = bookService.findAll(pageRequest);
        bookPage.forEach(action -> {
            System.out.println(action.toString());
        });
    }

    @Test
    public void findByAuthorPageable() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Book> bookPage = bookService.findByAuthorPageable("张三", pageRequest);
        System.out.println(bookPage.getTotalElements());
        System.out.println(bookPage.getTotalPages());
        System.out.println(bookPage.getContent().toString());
    }
}