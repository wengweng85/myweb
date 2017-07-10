package com.insigma.mvc.component.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
public class ParseModelJob implements Job {


/**
 * 定时任务
 */
@Override
public void execute(JobExecutionContext context) throws JobExecutionException {
      System.out.println("当前时间"+new Date().toLocaleString());
}

}
