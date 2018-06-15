package com.yefan.study.spring.redis.util;


import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil {
    public String toJson(Object object) {
        return JSON.toJSONString(object);
    }

    public Object toObject(String json) {
        return JSON.parse(json);
    }
}
