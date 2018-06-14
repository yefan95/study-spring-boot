package com.yefan.study.boot.client.resposity;

import com.yefan.study.boot.client.bean.Book;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
}
