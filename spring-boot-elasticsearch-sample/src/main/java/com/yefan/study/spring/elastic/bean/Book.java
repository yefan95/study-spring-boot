package com.yefan.study.spring.elastic.bean;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "book", type = "book")
public class Book {
    @Id
    private Long id;

    private String bookName;

    private String author;

    private String publish;

    private Date publishDate;
}
