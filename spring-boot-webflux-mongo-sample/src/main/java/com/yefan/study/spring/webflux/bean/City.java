package com.yefan.study.spring.webflux.bean;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class City {

    @Id
    private Long id;

    private Long provinceId;

    private String cityName;

    private String description;


}
