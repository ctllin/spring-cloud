package com.ctl.spring.cloud.springcloudctl.service;

import com.ctl.spring.cloud.springcloudctl.bean.Person;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * <p>Title: FeignClientService</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-01-14 16:53
 */
//@FeignClient(value="spring-cloud-eureka-server")
@FeignClient(value="${application.server.name}") //使用注解获取配置文件中的Application的name(server)
public interface FeignClientService {
    @RequestMapping("/getJson")
    Object getJson();
    @RequestMapping("/getPersonValue")
    Person getPersonValue();

    @RequestMapping("/getMap1")
    Map<String, Object> getMap1(@RequestBody final Person person);
    //测试condition
    @RequestMapping("/testCondition")
    Object testCondition(@RequestBody final Person person);
//    @RequestMapping("/getMap2") //客户端启动会失败 不能有request和response
//    Map<String, Object> getMap2(HttpServletRequest request, HttpServletResponse response, @RequestBody final Person person);
}
