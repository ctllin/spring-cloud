package com.ctl.springboottest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController

public class SpringBootTestApplication {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@RequestMapping("/")
	public void  index(){
		logger.trace("日志输出 trace");
		logger.debug("日志输出 debug");
		logger.info("日志输出 info");
		logger.warn("日志输出 warn");
		logger.error("日志输出 error");
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTestApplication.class, args);
	}
}
//elasticsearch版本, elasticsearch-6.7.1
//logstash版本,logstash-6.7.1
//kibana版本, kibana-6.7.1
//elasticsearch.yml cluster.name: elasticsearch