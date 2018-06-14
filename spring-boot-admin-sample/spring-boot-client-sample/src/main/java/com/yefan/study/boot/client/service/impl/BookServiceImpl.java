package com.yefan.study.boot.client.service.impl;

import com.yefan.study.boot.client.bean.Book;
import com.yefan.study.boot.client.resposity.BookRepository;
import com.yefan.study.boot.client.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Page<Book> queryByPage(int page, int size) {
        PageRequest request = new PageRequest(page, size);
        Page<Book> bookPage = bookRepository.findAll(request);
        return bookPage;
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }


    @Override
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }


}
