package com.yefan.study.spring.scheduling.service;

import com.github.pagehelper.PageInfo;
import com.yefan.study.spring.scheduling.entity.JobAndTrigger;

public interface IJobAndTriggerService {
    public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize);
}
