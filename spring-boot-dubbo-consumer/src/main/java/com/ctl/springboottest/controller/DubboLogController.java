package com.ctl.springboottest.controller;

import com.ctl.springboottest.constants.DubboLogConstants;
import com.ctl.springboottest.service.DubboLogTraceIdService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * com.ctl.test.controller
 * AsyncController  使用该类需要先创建index
 * curl -XPOST 'localhost:9200/goods/store_goods/_bulk?pretty' -H 'Content-Type: application/json' --data-binary @goodsToES.json
 * ctl 2019/3/31 21:35
 */
@RestController
@RequestMapping("/log")
public class DubboLogController {
    Logger logger = LoggerFactory.getLogger(DubboLogController.class);
    @Autowired(required = false)
    private DubboLogTraceIdService dubboLogTraceIdService;
    @GetMapping("get/{id}")
    public Object get(@PathVariable("id") String id){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        MDC.put(DubboLogConstants.TRACE_LOG_ID, uuid);
        logger.info("controller->param:{}", id);
        return dubboLogTraceIdService.getTraceId(id);
    }

}
