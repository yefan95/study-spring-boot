package com.yefan.study.spring.redis.controller;

import com.yefan.study.spring.redis.bean.User;
import com.yefan.study.spring.redis.service.RedisService;
import com.yefan.study.spring.redis.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping
@Controller
public class TestRedisController {

    @Autowired
    private RedisService redisService;
    @Autowired
    private JsonUtil jsonUtil;

    @RequestMapping(value = "/set")
    @ResponseBody
    public String save(@RequestBody User user) {
        System.out.println(user.toString());
        boolean flag = redisService.set(String.valueOf(user.getUserId()), jsonUtil.toJson(user));
        if (flag) {
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping(value = "/setList")
    @ResponseBody
    public String saveList() {
        List<User> userList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            User user = new User(i, "test" + i, i % 2 == 0 ? 1 : 0, 20 + i, "测试");
            userList.add(user);
        }
        redisService.setList("test", userList);
        return "success";
    }

    @RequestMapping(value = "/get")
    @ResponseBody
    public String get(int userId) {
        String json = redisService.get(String.valueOf(userId));
        if (StringUtils.isEmpty(json)) {
            return "fail";
        }
        return json;
    }

    @RequestMapping(value = "/getList")
    @ResponseBody
    public String getList() {
        List<User> userList = redisService.getList("test");
        if (userList != null) {
            return jsonUtil.toJson(userList);
        }
        return "fail";
    }

    @RequestMapping(value = "/remove")
    @ResponseBody
    public String remove(String key) {
        System.out.println("key: " + key);
        boolean flag = redisService.remove(key);
        if (flag) {
            return "success";
        }
        return "fail";
    }

    @RequestMapping(value = "/expire")
    @ResponseBody
    public String expire(@RequestParam("key") String key, @RequestParam("expireTime") long expireTime) {
        boolean flag = redisService.expire(key, expireTime);
        if (flag) {
            return "success";
        }
        return "fail";
    }


}
