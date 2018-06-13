package com.yefan.study.spring.elastic.bean;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Author implements Serializable{

    private Long id;

    private String name;

    private String remark;

}
