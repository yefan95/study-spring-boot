package com.yefan.study.spring.redis.service;

import com.yefan.study.spring.redis.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisServiceTest {

    @Autowired
    private RedisService redisService;

    @Test
    public void setList() {
        List<User> userList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            User user = new User(i, "www" + i, i % 2 == 0 ? 1 : 0, 20 + i, "测试");
            userList.add(user);
        }
        redisService.setList("ttt", userList);
    }

    @Test
    public void getList() {
        List<User> userList = redisService.getList("ttt");
        if (userList != null) {
            System.out.println(userList.toString());
        }else {
            System.out.println("========================");
        }
    }

    @Test
    public void remove(){
        redisService.remove("www");
    }
}