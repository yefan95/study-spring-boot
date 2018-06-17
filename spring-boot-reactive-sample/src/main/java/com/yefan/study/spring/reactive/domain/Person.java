package com.yefan.study.spring.reactive.domain;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private String id;
    private String firstName;
    private String familyName;
    private int age;
}
