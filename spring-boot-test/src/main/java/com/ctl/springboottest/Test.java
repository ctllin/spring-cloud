package com.ctl.springboottest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * com.ctl.springboottest
 * Test
 * ctl 2019/4/12 21:57
 */
public class Test {
    //./kafka-topics.bat --zookeeper localhost:2181 --create --topic applog --partitions 1  --replication-factor 1
    //private static Logger logger = LoggerFactory.getLogger(Test.class);
    private static Logger logger = LoggerFactory.getLogger("Application_ERROR");
    public static void main(String[] args) {
        logger.info("kafka test");
    }
}
