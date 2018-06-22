package com.yefan.study.spring.scheduling.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class NewJob implements BaseJob {

    private static Logger logger = LoggerFactory.getLogger(NewJob.class);

    public NewJob() {

    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.info("New Job执行时间: " + new Date());
    }
}
