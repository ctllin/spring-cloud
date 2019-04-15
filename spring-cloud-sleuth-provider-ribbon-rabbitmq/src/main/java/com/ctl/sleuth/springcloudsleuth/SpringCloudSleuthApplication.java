package com.ctl.sleuth.springcloudsleuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudSleuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudSleuthApplication.class, args);
	}
//mvn spring-boot:run -Dspring-boot.run.profiles=dev
//taskkill /f /pid 进程号
//mvn clean && mvn compile && mvn spring-boot:run
}
