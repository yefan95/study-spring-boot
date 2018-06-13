package com.yefan.study.spring.elastic.service;

import com.yefan.study.spring.elastic.bean.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.stream.Stream;

public interface BookService {
    void save(Book book);

    Stream<Book> findByAuthor(String author);

    Page<Book> findAll(Pageable pageable);

    Page<Book> findByAuthorPageable(String author,Pageable pageable);
}
