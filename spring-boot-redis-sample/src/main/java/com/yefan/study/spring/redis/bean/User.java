package com.yefan.study.spring.redis.bean;

import lombok.*;

import java.io.Serializable;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private int userId;

    private String userName;

    private int sex;

    private int age;

    private String department;

}
