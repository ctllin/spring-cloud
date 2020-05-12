package com.ctl.springbootdubbozipkinapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.Resource;

@SpringBootApplication
@ImportResource(locations = {"classpath:spring-dubbo.xml"})
//@PropertySource(value = "classpath:config.properties")
//@PropertySource(value = "classpath:config-resource.properties")

@PropertySource(
        name = "props",
        value = { "classpath:config.properties", "classpath:config-resource.properties" , "classpath:brave.properties"})
public class SpringbootDubboZipkinConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDubboZipkinConsumerApplication.class, args);
    }

}
