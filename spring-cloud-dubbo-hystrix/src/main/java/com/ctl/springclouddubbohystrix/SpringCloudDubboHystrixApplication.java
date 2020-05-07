package com.ctl.springclouddubbohystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableHystrix
@SpringBootApplication
public class SpringCloudDubboHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudDubboHystrixApplication.class, args);
    }

}
