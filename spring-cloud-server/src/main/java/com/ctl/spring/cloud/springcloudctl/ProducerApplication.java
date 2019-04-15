package com.ctl.spring.cloud.springcloudctl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * <p>Title: ProducerApplication</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2018-12-17 18:10
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProducerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }
}
//没有配置端口在日志里这样查找端口
//2019-01-14 18:52:10.398  INFO 9316 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 62934 (http)
//2019-01-14 18:52:10.399  INFO 9316 --- [           main] .s.c.n.e.s.EurekaAutoServiceRegistration : Updating port to 62934
//可以这样访问
//http://localhost:62934/sayhello
//http://localhost:62934/getJson
//http://localhost:62934/greeting