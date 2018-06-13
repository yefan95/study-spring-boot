package com.yefan.study.spring.elastic.service;

import com.yefan.study.spring.elastic.bean.User;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface UserService {

    void save(User user);

    Future<User> findByUserName(String userName);

    CompletableFuture<List<User>> findBySex(int sex);

    ListenableFuture<List<User>> findByAgeBetween(int start, int end);

}
