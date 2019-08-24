package com.dev.demo;

import com.alibaba.fastjson.JSON;
import com.dev.demo.bean.Message;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@SpringBootApplication
@RestController
public class RedisClusterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisClusterApplication.class, args);
	}
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RedisTemplate<String ,String> redisTemplate;
	@RequestMapping(value = "/send", method = RequestMethod.POST)
	@ResponseBody
	public Object sendKafka(String key, @RequestBody Message message) {
		try {
			logger.info("获取的消息={}", JSON.toJSONString(message));
			redisTemplate.opsForValue().set(message.getId().toString(), UUID.randomUUID().toString().toUpperCase());
			return "SUCCESS";
		} catch (Exception e) {
			logger.error("失败", e);
			return "FAIL";
		}
	}
}
// curl -H "Content-Type: application/json" -X POST  --data  '{"id": "1"}' 'http://192.168.6.27:8080/send'