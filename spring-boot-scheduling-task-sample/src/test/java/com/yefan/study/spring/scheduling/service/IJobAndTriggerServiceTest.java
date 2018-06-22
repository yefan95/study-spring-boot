package com.yefan.study.spring.scheduling.service;

import com.github.pagehelper.PageInfo;
import com.yefan.study.spring.scheduling.entity.JobAndTrigger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IJobAndTriggerServiceTest {

    @Autowired
    private IJobAndTriggerService jobAndTriggerService;

    @Test
    public void getJobAndTriggerDetails() {
        PageInfo<JobAndTrigger> list = jobAndTriggerService.getJobAndTriggerDetails(1, 5);
        System.out.println(list.getTotal());
    }
}