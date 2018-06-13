package com.yefan.study.spring.elastic.service.impl;

import com.yefan.study.spring.elastic.bean.User;
import com.yefan.study.spring.elastic.repository.UserRepository;
import com.yefan.study.spring.elastic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Future<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public CompletableFuture<List<User>> findBySex(int sex) {
        return userRepository.findBySex(sex);
    }

    @Override
    public ListenableFuture<List<User>> findByAgeBetween(int start, int end) {
        return userRepository.findByAgeBetween(start, end);
    }


}
