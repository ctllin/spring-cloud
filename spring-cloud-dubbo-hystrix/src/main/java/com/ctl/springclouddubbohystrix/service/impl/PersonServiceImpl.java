package com.ctl.springclouddubbohystrix.service.impl;

import com.ctl.springclouddubbohystrix.service.PersonService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
public class PersonServiceImpl implements PersonService {
    @Value("${dubbo.protocol.port:30000}")
    private String port;

    //此方法有熔断对应的方法,当调用函数没有熔断方法时使用该方法的熔断方法,当调用方法有自己的熔断方法时使用的是调用方法的熔断方法
    @HystrixCommand(fallbackMethod = "hiError", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            //@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),//THREAD|SEMAPHORE
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    @Override
    public String sayHi() {
        try {
            Thread.sleep(new Random().nextInt(2000));
        } catch (Exception e) {
        }
        log.info("sayHi run over");
        return "Hello Dubbo, i am from port:" + port;
    }

    public String hiError() {
        return "Hystrix fallback";
    }
}