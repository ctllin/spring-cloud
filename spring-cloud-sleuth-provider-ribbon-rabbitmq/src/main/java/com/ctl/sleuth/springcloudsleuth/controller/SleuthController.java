package com.ctl.sleuth.springcloudsleuth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping(value = "trace2")
    public String trace2(HttpServletRequest request, HttpServletResponse response){
        return "trace2-" + request.getRemoteAddr() + "\tremotePort:" + request.getRemotePort() + "\tserverPort:" + request.getServerPort() + "\tlocalPort" + request.getLocalPort()+"\t"+System.currentTimeMillis();
    }
}
