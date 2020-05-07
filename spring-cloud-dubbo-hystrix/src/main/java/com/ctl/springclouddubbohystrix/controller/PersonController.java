package com.ctl.springclouddubbohystrix.controller;

import com.ctl.springclouddubbohystrix.service.PersonService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;

@RestController
public class PersonController {

    @Autowired(required = false)
    //@Reference(version = "v1")
    private PersonService personService;

    @HystrixCommand(fallbackMethod = "hiError")
    @RequestMapping(value = "hi")
    public String sayHi() {
        return personService.sayHi();
    }

    public String hiError() {
        return "Hystrix fallback";
    }
}