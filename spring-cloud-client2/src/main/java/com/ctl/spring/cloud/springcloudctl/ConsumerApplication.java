package com.ctl.spring.cloud.springcloudctl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * <p>Title: ConsumerApplication</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *  附：SpringBoot注解扫描范围约定
 　　SpringBoot项目的注解扫描默认规则是根据Application类所在的包位置从上往下扫描！
 　　“Application类”是指SpringBoot项目入口类。这个类的位置很关键。如果Application类所在的包为：com.iteye.wallimn，则只会扫描com.iteye.wallimn包及其所有子包，如果service或dao所在包不在com.iteye.wallimn及其子包下，则不会被扫描！
 　　如果Application类放在com.iteye.wallimn.app包中，那么与app的同级包、叔叔包是不会被扫描的。
 * @author guolin
 * @version 1.0
 * @date 2018-12-17 18:11
 */
@EnableCircuitBreaker //开启断路器功能
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
//@SpringBootApplication(scanBasePackages={"com.ctl.service","com.ctl.spring.cloud.springcloudctl"}) //不生效
//@ComponentScan(basePackages = ("com.ctl.service,com.ctl.spring.cloud.springcloudctl")) //不生效
public class ConsumerApplication {
    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}
//@EnableDiscoveryClient :启用服务注册与发现
//@EnableFeignClients：启用feign进行远程调用
//http://localhost:8080/greet
//http://localhost:8080/health
//http://localhost:8080/say
//使用war包部署http://ip:port/project_name/say
//使用war包部署http://ip:port/project_name/greet