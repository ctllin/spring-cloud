package com.ctl.springboottest;

import com.ctl.springboottest.constants.DubboLogConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * com.ctl.springboottest
 * Test
 * ctl 2019/4/18 22:24
 */
public class Test {
    private static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        MDC.put(DubboLogConstants.TRACE_LOG_ID, uuid);
        logger.info("123");
    }
}
