package com.ctl.springclouddubbohystrix;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrix
@SpringBootApplication
@MapperScan("com.ctl.springclouddubbohystrix.mapper")
public class SpringCloudDubboHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudDubboHystrixApplication.class, args);
    }

}
