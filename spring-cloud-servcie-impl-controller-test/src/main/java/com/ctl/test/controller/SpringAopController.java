package com.ctl.test.controller;

import com.ctl.test.sprinz.service.Encoreable;
import com.ctl.test.sprinz.service.Performance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * com.ctl.test.controller
 * SpringAopTest
 * ctl 2019/3/28 23:35
 */
@RestController
@RequestMapping("aop")
public class SpringAopController {
    //该接口没有任何方法
    @Autowired(required = false)
    @Qualifier("gangqin")
    private Performance performanceGangQin;
    @Autowired(required = false)
    @Qualifier("jita")
    private Performance performanceJiTa;

    @RequestMapping(value = "/test1")
    @ResponseBody
    public Object test1() {
        System.out.println("+++++++++++++++++无中生有开始++++++++++++++++++");
//        类型强转
        Encoreable encoreable = (Encoreable) performanceJiTa;
        encoreable.performEncore("jita");
        encoreable = (Encoreable) performanceGangQin;
        encoreable.performEncore("gangqin");
        System.out.println("+++++++++++++++++无中生有结束++++++++++++++++++");
        return "{\"result\":\"success\"}";
    }
}
