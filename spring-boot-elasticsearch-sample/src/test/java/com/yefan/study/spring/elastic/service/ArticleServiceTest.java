package com.yefan.study.spring.elastic.service;

import com.yefan.study.spring.elastic.bean.Article;
import com.yefan.study.spring.elastic.bean.Author;
import com.yefan.study.spring.elastic.bean.Tutorial;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceTest {


    @Autowired
    private ArticleService articleService;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void save() {
        for (long i = 10; i < 20; i++) {
            Author author = new Author(null, "jack" + i, "remark");
            Tutorial tutorial = new Tutorial(i, "test" + String.valueOf(i));
            Article article = new Article(i, "title" + i, "abs", "content", new Date(), i, author, tutorial);
            articleService.save(article);
        }
    }

    @Test
    public void delete() {
        Article article = new Article();
        article.setId(0L);
        articleService.delete(article);
    }

    @Test
    public void findOne() {
        Article article = articleService.findOne(12L);
        System.out.println(article);
    }

    @Test
    public void findAll() {
        Iterable<Article> iterable = articleService.findAll();
        Iterator<Article> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void findByAuthor() {
        Author author = new Author(null, "jack", "remark");
        List<Article> articleList = articleService.findByAuthor(author);
        System.out.println(articleList.toString());
    }

    @Test
    public void findByAuthorPageable() {
        Author author = new Author(null, "jack", "remark");
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<Article> articlePage = articleService.findByAuthor(author, pageRequest);
        System.out.println(articlePage.getContent());
    }

    @Test
    public void findByTitle() {
        List<Article> articleList = articleService.findByTitle("title");
        System.out.println(articleList.toString());
    }

    @Test
    public void findByTitlePageable() {
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<Article> articlePage = articleService.findByTitle("title", pageRequest);
        System.out.println("pageNo: " + articlePage.getTotalPages());
        System.out.println("total: " + articlePage.getTotalElements());
        System.out.println(articlePage.getContent().toString());
    }

    @Test
    public void search() {

    }
}