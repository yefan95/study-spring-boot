package com.yefan.study.spring.elastic.repository;


import com.yefan.study.spring.elastic.bean.Article;
import com.yefan.study.spring.elastic.bean.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @author yefan
 * @since 2018/06/13
 */
public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {

    List<Article> findByAuthor(Author author);

    Page<Article> findByAuthor(Author author, Pageable pageable);

    List<Article> findByTitle(String title);

    Page<Article> findByTitle(String title, Pageable pageable);


}
