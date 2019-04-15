package com.neo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


//@EnableConfigServer
//@SpringBootApplication
@SpringBootApplication  //声明springboot应用启动main
@EnableConfigServer     //声明是configserver
@EnableEurekaClient     //注册config服务到eureka
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
}
