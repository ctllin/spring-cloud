package com.ctl.springclouddubbohystrix.service.impl;

import com.ctl.springclouddubbohystrix.mapper.UserMapper;
import com.ctl.springclouddubbohystrix.model.User;
import com.ctl.springclouddubbohystrix.service.PersonService;
import com.ctl.springclouddubbohystrix.service.PersonService2;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class PersonService2Impl implements PersonService2 {
    @Autowired(required = false)
    private PersonService personService;
    @Autowired(required = false)
    private UserMapper userMapper;
    @Override
    @HystrixCommand(fallbackMethod = "hiError")
    @Transactional
    public String callSayHi2(String id) {
        log.info("当前线程id={},name={}",Thread.currentThread().getId(),Thread.currentThread().getName());
        User user = new User();
        user.setAge(1);
        userMapper.insert(user);
        return personService.sayHi();
    }

    @Override
    public String callSayHi3(String id) {
        return personService.sayHi();
    }

    public String hiError(String id) {
        log.info("熔断当前线程id={},name={}",Thread.currentThread().getId(),Thread.currentThread().getName());
        return "Hystrix fallback from service "+id+" "+Thread.currentThread().getId();
    }
}