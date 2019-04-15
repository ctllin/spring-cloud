package com.ctl.spring.cloud.springcloudctl.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Title: GreetingService</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2018-12-17 14:59
 */
@Component
@FeignClient(name = "spring-cloud-eureka-server")
public interface GreetingService {
    //如果该service放到其他地方该service无法识别(Field helloService in com.ctl.spring.cloud.springcloudctl.controller.FeignClientController required a bean of type 'com.ctl.service.GreetingService' that could not be found.),
    // @SpringBootApplication这个注解其实相当于下面这一堆注解的效果，其中一个注解就是@Component，
    // 在默认情况下只能扫描与控制器在同一个包下以及其子包下的@Component注解，以及能将指定注解的类自动注册为Bean的@Service@Controller和@ Repository，
    // 至此明白问题所在
    @RequestMapping(value = "/greeting")
    String greeting();
}
