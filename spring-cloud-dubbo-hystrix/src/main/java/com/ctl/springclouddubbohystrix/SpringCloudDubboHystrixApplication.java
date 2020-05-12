package com.ctl.springclouddubbohystrix;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import java.util.Arrays;

@EnableHystrix
@SpringBootApplication
@MapperScan("com.ctl.springclouddubbohystrix.mapper")
//@EnableZipkinServer
//@ImportResource(locations = {"classpath:spring-dubbo.xml"})

public class SpringCloudDubboHystrixApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringCloudDubboHystrixApplication.class, args);
        System.out.println(Arrays.deepToString(run.getBeanDefinitionNames()));
    }

}
