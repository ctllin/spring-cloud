package com.ctl.spring.cloud.springcloudctl.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Title: GreetingService</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 * 通过@FeignClient注解指定服务名来绑定服务，这里的服务名字不区分大小写
 * 然后再通过@RequestMapping来绑定服务下的rest接口
 * @author guolin
 * @version 1.0
 * @date 2018-12-17 14:59
 */
public interface GreetingService {
    @GetMapping("/greeting")
    String greeting();
}
