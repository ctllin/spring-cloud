package com.ctl.springclouddubbohystrix.service.impl;

import com.ctl.springclouddubbohystrix.service.PersonService;
import com.ctl.springclouddubbohystrix.service.PersonService2;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonService2Impl implements PersonService2 {
    @Autowired(required = false)
    private PersonService personService;

    @Override
    @HystrixCommand(fallbackMethod = "hiError")
    public String callSayHi() {
        return personService.sayHi();
    }
    public String hiError() {
        return "Hystrix fallback from service";
    }
}