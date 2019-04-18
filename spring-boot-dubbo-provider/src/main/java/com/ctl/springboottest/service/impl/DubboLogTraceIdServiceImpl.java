package com.ctl.springboottest.service.impl;

import com.ctl.springboottest.service.DubboLogTraceId2Service;
import com.ctl.springboottest.service.DubboLogTraceIdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * com.ctl.springboottest.service.impl
 * DubboLogTraceIdServiceImpl
 * ctl 2019/4/18 21:50
 */
@Service
public class DubboLogTraceIdServiceImpl implements DubboLogTraceIdService {
    Logger logger = LoggerFactory.getLogger(DubboLogTraceIdServiceImpl.class);
    @Autowired(required = false)
    private DubboLogTraceId2Service dubboLogTraceId2Service;
    @Override
    public Object getTraceId(String id) {
        logger.info("日志链路追踪测试"+id);
        Map<String,Object> returnMap = new HashMap<>();
        try {
            dubboLogTraceId2Service.getTrace2Id(id);
        }catch (Exception e){
            logger.error("日志链路追踪2测试调用失败",e);
        }
        returnMap.put("id",id);
        return returnMap ;
    }
}
