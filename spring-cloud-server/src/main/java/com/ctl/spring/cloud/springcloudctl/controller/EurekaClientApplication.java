package com.ctl.spring.cloud.springcloudctl.controller;

import com.ctl.common.bean.Person3;
import com.ctl.service.HelloService;
import com.ctl.spring.cloud.springcloudctl.bean.Person;
import com.ctl.spring.cloud.springcloudctl.service.FeignClientService;
import com.ctl.spring.cloud.springcloudctl.service.GreetingService;
import com.ctl.spring.cloud.springcloudctl.service.ListDirService;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
public class EurekaClientApplication implements GreetingService,HelloService,FeignClientService {
	@Qualifier("eurekaClient")
	@Lazy
	@Autowired(required = false)
	private EurekaClient eurekaClient;

	@Value("${spring.application.name}")
	private String appName;
    @Resource
    private Environment environment;

    //测试Condition 实现类有两个分别是linux版本和windows版本
    @Autowired(required = false)
    private ListDirService listDirService;
    //person3也是根据ConditionalOnProperty注入
    @Autowired(required = false)
    private Person3 person3;

	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}
	//在客户端可以调用,http://localhost:8080/greeting主要客户端注入了GreetingService
    @RequestMapping("/greeting")
	public String greeting() {
		System.out.println("eurekaClient:"+eurekaClient);
		return String.format("Hello from '%s'!", eurekaClient.getApplication(appName).getName());
	}
	@RequestMapping("/sayhello")
	public String sayhello() {
		System.out.println("eurekaClient:"+eurekaClient);
		return String.format("你好 from '%s'!", eurekaClient.getApplication(appName).getName());
	}

	@RequestMapping("/getJson")
    @ResponseBody
	public Object getJson() {
		System.out.println("eurekaClient:"+eurekaClient);
		Map<String ,Object> returnMap = new HashMap<>();
        returnMap.put("appName",eurekaClient.getApplication(appName).getName());
        returnMap.put("timestamp",System.currentTimeMillis());
		return returnMap;
	}

	@RequestMapping("/getPersonValue")
	public Person getPersonValue() {
		Person person = new Person();
		person.setUuid(UUID.randomUUID().toString());
		person.setAge(27);
		person.setName("ctl");
		person.setSalay(5000000L);
        person.setPort(Integer.parseInt(environment.getProperty("server.port")));
        Integer sleepTime =( new Random().nextInt(100)+1)*100;
        try {
            long start = System.currentTimeMillis();
            System.out.println(person.getUuid() + " " + start);
            Thread.sleep(sleepTime);
            System.out.println(person.getUuid() + " " + (System.currentTimeMillis() - start) + " getPersonValue.sleep=" + sleepTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return person;
	}

    @RequestMapping("/getMap1")
    public Map<String, Object> getMap1(@RequestBody Person person) {
        if (person == null) {
            person = new Person();
            person.setUuid(UUID.randomUUID().toString());
        }
        person.setAge(new Random().nextInt(100));
        person.setName("ctl");
        person.setSalay(5000000L);
        Map<String, Object> returnMap = new TreeMap<>();
        returnMap.put("person", person);
        returnMap.put("timestamp", System.currentTimeMillis());
        return returnMap;
    }

    //直接调用此地址出错,换成object即可
    //1.发生以上情况，RequestBody序列化出现问题，多出现在后台接受前台序列化数据，或者Rest接口数据上。可以对接受的数据对象进行测试，排除掉可能存在问题的属性。在javabean中将出现问题的属性的get和set方法去掉前面的get和set部分即可。
    //原因，javabean序列化存在局限，当属性内部过于复杂，序列化将会发生异常，而序列化javabean数据默认会通过get和set方法来辨别，所以去掉get和set方法就避免了该属性被序列化。
    @RequestMapping("/getMap2")
    public Map<String, Object> getMap2(HttpServletRequest request, HttpServletResponse response, Person person) {
        if (person == null) {
            person = new Person();
            person.setUuid(UUID.randomUUID().toString());
        }
        person.setAge(new Random().nextInt(100));
        person.setName("ctl");
        person.setSalay(5000000L);
        Map<String, Object> returnMap = new TreeMap<>();
        returnMap.put("person", person);
        returnMap.put("contextPath", request.getContextPath());
        returnMap.put("headerNames", request.getHeaderNames());
        returnMap.put("requestURI", request.getRequestURI());
        return returnMap;
    }

    //测试condition
    @RequestMapping("/testCondition")
    @ResponseBody
    public Object testCondition(Person person) {
        if (person == null) {
            person = new Person();
            person.setUuid(UUID.randomUUID().toString());
        }
        person.setAge(new Random().nextInt(100));
        person.setName("ctl");
        person.setSalay(5000000L);
        Map<String, Object> returnMap = new TreeMap<>();
        returnMap.put("person", person);
        returnMap.put("person3", person3);
        returnMap.put("showListCmd", listDirService.showListCmd());
        returnMap.put("server.port", environment.getProperty("server.port"));
        return returnMap;
    }
//	@RequestMapping("/hello2")
//	@ResponseBody
//	public Map<String,Object> hello2() {
//		System.out.println("eurekaClient:"+eurekaClient);
//		Map<String,Object> returnMap = new HashMap<>();
//		String value = String.format("Hello from '%s'!", eurekaClient.getApplication(appName).getName());
//		returnMap.put("key",value);
//		return returnMap;
//	}
}
