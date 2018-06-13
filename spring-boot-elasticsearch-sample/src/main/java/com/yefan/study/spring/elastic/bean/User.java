package com.yefan.study.spring.elastic.bean;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(indexName = "user", type = "info")
public class User implements Serializable {

    @Id
    private Long userId;

    private String userName;

    private int age;

    private int sex;

    private Date birthDay;


}
