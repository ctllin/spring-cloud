package com.ctl.sharding.controller;

import com.ctl.sharding.service.DService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * com.ctl.sharding.controller
 * DubboController
 * ctl 2019/3/23 0:52
 */
@RestController
@RequestMapping(value = "dubbo")
public class DubboController {
    @Autowired(required = false)
    private DService dService;

    @GetMapping("method1")
    public Object method1() {
        return dService.method1();
    }
    @GetMapping("method2")
    public Object method2() {
        return dService.method2();
    }
    @GetMapping("method3")
    public Object method3() {
        return dService.method3();
    }
}
