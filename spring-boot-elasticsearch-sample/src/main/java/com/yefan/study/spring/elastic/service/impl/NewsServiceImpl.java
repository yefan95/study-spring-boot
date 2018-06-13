package com.yefan.study.spring.elastic.service.impl;

import com.yefan.study.spring.elastic.bean.News;
import com.yefan.study.spring.elastic.service.NewsService;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private Client client;

    @Override
    public void save(News news) throws IOException {
        XContentBuilder xContentBuilder = XContentFactory.jsonBuilder().startObject()
                .field("id", news.getId())
                .field("content", news.getContent())
                .field("title", news.getTitle())
                .field("date", news.getDate())
                .endObject();
        IndexResponse response = client.prepareIndex("news", "message", String.valueOf(news.getId()))
                .setSource(xContentBuilder)
                .execute()
                .actionGet();
    }

    @Override
    public void update(News news) throws IOException {
        XContentBuilder xContentBuilder = XContentFactory.jsonBuilder().startObject()
                .field("title", news.getTitle())
                .endObject();
        UpdateResponse response = client.prepareUpdate("news", "message", String.valueOf(news.getId()))
                .setDoc(xContentBuilder)
                .execute()
                .actionGet();
    }

    @Override
    public void delete(Long id) {
        client.prepareDelete("news", "message", String.valueOf(id))
                .execute()
                .actionGet();
    }


    @Override
    public void deleteIndex(String index) {
        DeleteIndexResponse deleteIndexResponse = client.admin().indices().prepareDelete("news").execute().actionGet();
    }

    @Override
    public List<Map<String, Object>> search(QueryBuilder queryBuilder) {
        SearchResponse response = client.prepareSearch("news")
                .setTypes("message")
                .setQuery(queryBuilder)
                .get();
        SearchHits searchHits = response.getHits();
        List<Map<String, Object>> result = new ArrayList<>();
        for (SearchHit searchHit : searchHits) {
            result.add(searchHit.getSource());
        }
        return result;
    }


}
