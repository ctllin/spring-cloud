package com.ctl.springclouddubbohystrix.controller;

import com.ctl.springclouddubbohystrix.service.PersonService;
import com.ctl.springclouddubbohystrix.service.PersonService2;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired(required = false)
    private PersonService personService;
    @Autowired(required = false)
    private PersonService2 personService2;

    @HystrixCommand(fallbackMethod = "hiError")
    @RequestMapping(value = "hi")
    public String sayHi() {
        return personService.sayHi();
    }

    public String hiError() {
        return "Hystrix fallback";
    }
    @RequestMapping(value = "hi2")
    public String sayHi2(String id) {
        return personService2.callSayHi(id);
    }
    @RequestMapping(value = "hi3")
    public String sayHi3(String id) {
        return personService2.callSayHi3(id);
    }
}
//http://localhost:9090/hi
//http://localhost:9090/hi2