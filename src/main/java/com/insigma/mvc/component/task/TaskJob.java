package com.insigma.mvc.component.task;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("taskJob") 
public class TaskJob { 
    @Scheduled(cron = "0 */2 * * * ?") 
    public void job1() { 
        //System.out.println(new Date().toLocaleString()); 
    } 
}
