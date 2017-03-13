package com.cc.schedule;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Component
public class ScheduledTasks {

    private Logger logger = Logger.getLogger(getClass());

//    @Scheduled(fixedRate = 5000)
//    public void reportCurrentTime() {
//        logger.info("The time is now " + LocalDateTime.now());
//    }
}