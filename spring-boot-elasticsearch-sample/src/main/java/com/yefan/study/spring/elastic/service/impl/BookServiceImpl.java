package com.yefan.study.spring.elastic.service.impl;

import com.yefan.study.spring.elastic.bean.Book;
import com.yefan.study.spring.elastic.repository.BookRepository;
import com.yefan.study.spring.elastic.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Stream<Book> findByAuthor(String author) {
        return bookRepository.streamAllByAuthor(author);
    }

    @Override
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.selectAll(pageable);
    }

    @Override
    public Page<Book> findByAuthorPageable(String author, Pageable pageable) {
        return bookRepository.findByAuthor(author, pageable);
    }
}
