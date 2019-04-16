package com.ctl.spring.cloud.springcloudctl.controller;

import com.ctl.common.bean.PersonNotInApplication;
import com.ctl.spring.cloud.springcloudctl.bean.Person;
import com.ctl.spring.cloud.springcloudctl.service.FeignClientService;
import com.ctl.spring.cloud.springcloudctl.service.GreetingService;
import com.ctl.spring.cloud.springcloudctl.service.HelloService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title: FeignClientController</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 * spring-cloud调用服务有两种方式，一种是Ribbon+RestTemplate, 另外一种是Feign。
 * Ribbon是一个基于HTTP和TCP客户端的负载均衡器，其实feign也使用了ribbon, 只要使用@FeignClient时，ribbon就会自动使用。
 * @author guolin
 * @version 1.0
 * @date 2018-12-17 15:06
 */
@RestController
@ConfigurationProperties(prefix = "person") //资源配置文件有person开的的配置
public class FeignClientController {
    private String uuid;//配置文件中有person.uuid
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    @Autowired(required = false)
    private RestTemplate restTemplate;
    @Autowired
    private GreetingService greetingService;
    @Autowired
    private HelloService helloService;
    @Autowired(required = false) //注入失败因为该service和Application类所在的包不是同一层级或子目录
    private com.ctl.service.HelloService  helloServiceOfOtherPath;
    @Autowired
    private FeignClientService feignClientService;
    @Autowired
    private Person person;
    //personNotInApplication默认情况下springboot不能识别到,
    // 但是通过com.ctl.spring.cloud.springcloudctl.bean.BeanConfig定义,可以识别不在springboot Application 同级目录或子目录的组件
    @Autowired
    private PersonNotInApplication personNotInApplication;
    @RequestMapping("/greet")
    public String greeting(Model model) {
        String body = restTemplate.getForEntity("http://SPRING-CLOUD-EUREKA-SERVER/greeting",String.class).getBody();
        model.addAttribute("greetingValue", body);
        System.out.println("request="+body);
        String greeting = greetingService.greeting();
        System.out.println("request="+greeting);
        return body;
    }
    @RequestMapping("/say")
    public String sayhello(Model model) { //text/plain;charset=UTF-8
        String body = restTemplate.getForEntity("http://SPRING-CLOUD-EUREKA-SERVER/greeting",String.class).getBody();
        System.out.println(helloService.sayhello());
        return body;
    }
    @RequestMapping("/say2")
    public String sayhello2(Model model) { //text/plain;charset=UTF-8
        String body = restTemplate.getForEntity("http://spring-cloud-eureka-server/greeting",String.class).getBody();
        System.out.println(helloServiceOfOtherPath.sayhello());
        return body;
    }
    @RequestMapping("/json")
    @ResponseBody
    public Object getJson() { //返回json
        Object json = feignClientService.getJson();
        System.out.println(json);
        System.out.println(person);
        return json;
    }
    @RequestMapping("/json2")
    public Object getJson2() { //返回json
        System.out.println(person);
        return person;
    }
    @RequestMapping("/getPerson")
    public Object getPerson() { //返回json
        System.out.println(personNotInApplication);
        return personNotInApplication;
    }

    @RequestMapping("/uuid")
    public Object getUUID() { //返回json
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("uuid", uuid);
        return returnMap;
    }
    //加入熔断机制当请求超出hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds事会进入getPersonValueError方法
    @HystrixCommand(fallbackMethod = "getPersonValueError")
    @RequestMapping("/getPersonValue")
    public Object getPersonValue() { //返回json
        Person personValue = feignClientService.getPersonValue();
        return personValue;
    }

    public String getPersonValueError(){
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("timestamp",System.currentTimeMillis());
        return JSONObject.fromObject(returnMap).toString();
    }

    @RequestMapping("/getMap")
    public Map<String, Object> getMap(HttpServletRequest request, HttpServletResponse response) { //返回json
       // Map<String, Object> map2 = feignClientService.getMap2(request, response, person);
        Map<String, Object> map1 = feignClientService.getMap1( person);
        //服务提供者@RequestBody 需要,否则传值失败
        //@RequestMapping("/getMap1")
        //public Map<String, Object> getMap1(@RequestBody Person person)
        return map1;
    }

    @RequestMapping("/testCondition")
    public Object testCondition(Person person) { //返回json
        return feignClientService.testCondition(person);
    }
}
