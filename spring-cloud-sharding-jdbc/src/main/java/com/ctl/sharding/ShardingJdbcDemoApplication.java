package com.ctl.sharding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.ctl"})
public class ShardingJdbcDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardingJdbcDemoApplication.class, args);
	}
}
