package com.yefan.study.spring.elastic.service;

import com.yefan.study.spring.elastic.bean.Article;
import com.yefan.study.spring.elastic.bean.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ArticleService {
    Article save(Article article);

    void delete(Article article);

    Article findOne(Long id);

    Iterable<Article> findAll();

    List<Article> findByAuthor(Author author);

    Page<Article> findByAuthor(Author author, PageRequest pageRequest);

    Page<Article> findByTitle(String title, PageRequest pageRequest);

    List<Article> findByTitle(String title);

    Iterable<Article> search(String queryString);
}
