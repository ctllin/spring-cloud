package com.ctl.springclouddubbohystrix.service.impl;

import com.ctl.springclouddubbohystrix.service.PersonService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class PersonServiceImpl implements PersonService {
    @Value("${dubbo.protocol.port:30000}")
    private String port;
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    @Override
    public String sayHi()  {
        try {
            Thread.sleep(new Random().nextInt(4000));
        } catch (Exception e) {
        }
        return "Hello Dubbo, i am from port:" + port;
    }

}