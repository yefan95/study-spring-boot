package com.yefan.study.spring.elastic.repository;

import com.yefan.study.spring.elastic.bean.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.stream.Stream;

/**
 * Streaming query results
 * <p>
 * The results of query methods can be processed incrementally by using a Java 8 Stream<T> as return type.
 * Instead of simply wrapping the query results in a Stream data store specific methods are used to perform the streaming.
 */
public interface BookRepository extends ElasticsearchRepository<Book, Long> {


    Stream<Book> streamAllByAuthor(String author);

    @Query("{\"bool\" : {\"should\" : {\"match_all\" : {}}}}")
    Page<Book> selectAll(Pageable pageable);

    @Query("{\"bool\" : {\"must\" : {\"match\" : {\"author\" : \"?0\"}}}}")
    Page<Book> findByAuthor(String author, Pageable pageable);
}
