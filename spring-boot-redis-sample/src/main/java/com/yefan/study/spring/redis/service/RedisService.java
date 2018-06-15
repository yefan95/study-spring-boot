package com.yefan.study.spring.redis.service;

import com.yefan.study.spring.redis.bean.User;

import java.util.List;

public interface RedisService {

    boolean set(String key, String value);

    void setList(String key, List<User> userList);

    String get(String key);

    List<User> getList(String key);

    boolean expire(String key, long expire);

    boolean remove(String key);

}
