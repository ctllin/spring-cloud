package com.ctl.spring.cloud.springcloudctl.service;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * <p>Title: RootService</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-01-14 16:57
 */
@FeignClient(value="spring-cloud-eureka-server")
public interface RootService {
}
