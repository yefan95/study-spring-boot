package com.yefan.study.spring.elastic.bean;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "article", type = "news")
public class Article implements Serializable{
    @Id
    private Long id;

    private String title;

    private String abstracts;

    private String content;

    private Date postTime;

    private Long clickCount;

    private Author author;

    private Tutorial tutorial;

}
