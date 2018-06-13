package com.yefan.study.spring.elastic.bean;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "news", type = "message")
public class News {
    @Id
    private Long id;

    private String title;

    private String content;

    private Date date;
}
