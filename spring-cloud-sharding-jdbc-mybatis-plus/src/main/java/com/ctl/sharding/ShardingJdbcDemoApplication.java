package com.ctl.sharding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication(scanBasePackages = {"com.ctl"})
public class ShardingJdbcDemoApplication extends SpringBootServletInitializer implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ShardingJdbcDemoApplication.class, args);
	}
	@Autowired
	private ApplicationContext appContext;
	//获取所有的bean
	@Override
	public void run(String... args) throws Exception
	{
		String[] beans = appContext.getBeanDefinitionNames();
		Arrays.sort(beans);
		for (String bean : beans)
		{
			System.out.println(bean + " of Type :: " + appContext.getBean(bean).getClass());
		}
	}
}
/**
 * mybatis-plus  3.1.0
 */
