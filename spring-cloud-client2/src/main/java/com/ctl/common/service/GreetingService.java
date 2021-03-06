package com.ctl.common.service;

import org.springframework.cloud.netflix.feign.FeignClient;
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
@FeignClient(name="spring-cloud-eureka-server")
public interface GreetingService {
    @RequestMapping("/greeting")
    String greeting();
}
