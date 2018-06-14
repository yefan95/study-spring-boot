package com.yefan.study.boot.client.service;

import com.yefan.study.boot.client.bean.Book;
import org.springframework.data.domain.Page;

public interface BookService {

    Page<Book> queryByPage(int page, int size);

    Book addBook(Book book);

    void deleteBook(Long bookId);

}
