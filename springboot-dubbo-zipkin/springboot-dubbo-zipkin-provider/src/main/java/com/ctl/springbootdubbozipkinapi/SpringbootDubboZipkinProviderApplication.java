package com.ctl.springbootdubbozipkinapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ImportResource(locations = {"classpath:spring-dubbo.xml"})

@PropertySource(
        name = "props",
        value = { "classpath:config.properties", "classpath:config-resource.properties", "classpath:brave.properties" })
public class SpringbootDubboZipkinProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDubboZipkinProviderApplication.class, args);
    }

}
