package com.yefan.study.spring.elastic.service;

import com.yefan.study.spring.elastic.bean.News;
import org.elasticsearch.index.query.QueryBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface NewsService {
    void save(News news) throws IOException;

    void update(News news) throws IOException;

    void delete(Long id);

    void deleteIndex(String index);

    List<Map<String, Object>> search(QueryBuilder queryBuilder);

}
