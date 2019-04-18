package com.ctl.springboottest.service.impl;

import com.ctl.springboottest.service.DubboLogTraceId2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * com.ctl.springboottest.service.impl
 * DubboLogTraceIdServiceImpl
 * ctl 2019/4/18 21:50
 */
@Service
public class DubboLogTraceId2ServiceImpl implements DubboLogTraceId2Service {
    Logger logger = LoggerFactory.getLogger(DubboLogTraceId2ServiceImpl.class);

    @Override
    public Object getTrace2Id(String id) {
        logger.info("日志链路追踪2测试"+id);
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("id",id);
        return returnMap ;
    }
}
