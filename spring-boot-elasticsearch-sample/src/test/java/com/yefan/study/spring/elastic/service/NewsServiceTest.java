package com.yefan.study.spring.elastic.service;

import com.sun.org.apache.xml.internal.utils.ObjectStack;
import com.yefan.study.spring.elastic.bean.News;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsServiceTest {
    @Autowired
    private NewsService newsService;

    @Test
    public void save() {
        for (long i = 0; i < 10l; i++) {
            News news = new News(i, "标题", "内容" + i, new Date());
            try {
                newsService.save(news);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void delete() {
        newsService.delete(22L);
    }

    @Test
    public void update() {
        News news = new News();
        news.setId(22L);
        news.setTitle("测试修改");
        try {
            newsService.update(news);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteIndex() {
        newsService.deleteIndex("news");
    }

    @Test
    public void search() {
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("id").from(5).to(22);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.matchQuery("title", "标题"));
        boolQueryBuilder.filter(rangeQueryBuilder);
        List<Map<String, Object>> mapList = newsService.search(boolQueryBuilder);
        System.out.println(mapList.toString());
    }
}