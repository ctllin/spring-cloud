package com.ctl.springboot.atomikos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;

@SpringBootApplication(scanBasePackages = {"com.ctl"})
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer implements CommandLineRunner {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);

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
//暂时没有调通mybatis_plus