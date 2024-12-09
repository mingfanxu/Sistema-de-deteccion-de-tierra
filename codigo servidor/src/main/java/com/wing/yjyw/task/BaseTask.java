package com.wing.yjyw.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description: 定时任务 tarea de timer
 */
@Slf4j
@Component
public class BaseTask {
    private static final String iotId = "";

    //@Scheduled(cron = "30 * * * * ? ") //每30秒触发一次
    public void run(){


    }
}
