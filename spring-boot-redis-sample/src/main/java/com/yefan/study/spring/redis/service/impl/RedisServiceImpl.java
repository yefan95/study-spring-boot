package com.yefan.study.spring.redis.service.impl;

import com.yefan.study.spring.redis.bean.User;
import com.yefan.study.spring.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Value("${user.prefix}")
    private String PREFIX;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean set(String key, String value) {
        stringRedisTemplate.opsForValue().set(PREFIX + key, value);
        return false;
    }

    @Override
    public void setList(String key, List<User> userList) {
        redisTemplate.opsForValue().set(PREFIX + key, userList);
//        redisTemplate.opsForList().leftPushAll(PREFIX + key, userList);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(PREFIX + key);
    }

    @Override
    public List<User> getList(String key) {
        return (List<User>) redisTemplate.opsForValue().get(PREFIX + key);
//        long size = redisTemplate.opsForList().size(PREFIX + key);
//        return (List<User>) redisTemplate.opsForList().range(PREFIX + key, 0, size);
    }

    @Override
    public boolean expire(String key, long expire) {
        stringRedisTemplate.expire(PREFIX + key, expire, TimeUnit.SECONDS);
        return false;
    }

    @Override
    public boolean remove(String key) {
        return stringRedisTemplate.delete(PREFIX + key);
    }
}
