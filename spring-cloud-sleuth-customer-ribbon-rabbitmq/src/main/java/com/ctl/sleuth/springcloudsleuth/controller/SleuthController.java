package com.ctl.sleuth.springcloudsleuth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <p>Title: SleuthController</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-04-10 11:14
 */
@RestController
public class SleuthController {
    private Logger logger = LoggerFactory.getLogger(SleuthController.class);
    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return  new RestTemplate();
    }
    @Autowired
    RestTemplate restTemplate;
    @RequestMapping(value = "trace1")
    public String trace(){
        return restTemplate.getForEntity("http://SPRING-CLOUD-SLEUTH-PROVIDER-RIBBON-RABBITMQ/trace2",String.class).getBody();
    }
}
