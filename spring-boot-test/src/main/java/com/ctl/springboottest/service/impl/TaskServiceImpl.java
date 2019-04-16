package com.ctl.springboottest.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.ctl.springboottest.service.*;

/**
 * com.ctl.test.controller
 * TaskServiceImpl
 * ctl 2019/3/31 21:47
 */
@Service
public class TaskServiceImpl implements TaskService {
    private final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Override
    public String execute() {
        try {
            Thread.sleep(3000);
            logger.info("Slow task executed");
            return "Task finished";
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }
    }
}