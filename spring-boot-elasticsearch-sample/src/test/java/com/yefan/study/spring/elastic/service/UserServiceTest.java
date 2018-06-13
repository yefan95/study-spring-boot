package com.yefan.study.spring.elastic.service;

import com.yefan.study.spring.elastic.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void save() {
        for (long i = 0; i < 10; i++) {
            User user = new User(i, "admin" + i, (int) (20 + i), i % 2 == 0 ? 1 : 0, new Date());
            userService.save(user);
        }
    }

    @Test
    public void findByUserName() {
        Future<User> future = userService.findByUserName("admin1");
        if (future.isDone()) {
            try {
                System.out.println(future.get().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void findBySex() {
        CompletableFuture<List<User>> future = userService.findBySex(0);
        if(future.isDone()){
            try {
                System.out.println(future.get().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void findByAgeBetween() {
        ListenableFuture<List<User>> future = userService.findByAgeBetween(22,23);
        if(future.isDone()){
            try {
                System.out.println(future.get().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}