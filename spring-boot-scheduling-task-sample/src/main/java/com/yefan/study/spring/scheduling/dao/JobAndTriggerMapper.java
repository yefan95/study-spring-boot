package com.yefan.study.spring.scheduling.dao;

import com.yefan.study.spring.scheduling.entity.JobAndTrigger;

import java.util.List;

public interface JobAndTriggerMapper {
    public List<JobAndTrigger> getJobAndTriggerDetails();
}
