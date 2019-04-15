package com.ctl.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration .class})
@MapperScan(value = "com.ctl.test.mapper")
public class SpringCloudServcieImplControllerTestApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudServcieImplControllerTestApplication.class, args);
	}
//通过tomcat启动
}
