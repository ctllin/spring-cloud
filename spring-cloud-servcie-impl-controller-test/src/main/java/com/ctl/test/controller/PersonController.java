package com.ctl.test.controller;

import com.ctl.test.model.Person;
import com.ctl.test.service.PersonService;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.params.SetParams;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title: PersonController</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-03-09 15:09
 */
@RestController
@RequestMapping(value = "/person")
public class PersonController {
    static final Logger logger = LoggerFactory.getLogger(PersonController.class);
    @Autowired
    private PersonService personService;
    @Autowired(required = false)
    private JedisCluster jedisCluster;

    @RequestMapping(value = "getPerson/{pid}")
    @ResponseBody
    public Person getPerson(@PathVariable(value = "pid") Integer pid){
        Person person = personService.getPersonByPid(pid);
        if (person != null) {
            SetParams params = new SetParams();
            params.ex(1000);
            params.nx();
            //redisSetResult成功'OK'失败返回'null'
            try {
                String redisSetResult = jedisCluster.set("pid-" + person.getId(), JSONObject.fromObject(person).toString(), params);
                logger.info("存放person,pid={},redisSetResult={}", pid, redisSetResult);
            } catch (Exception e) {
                logger.error("放入缓存失败", e);
            }
            return person;
        }else{
            return new Person();
        }
    }

    @RequestMapping(value = "addPerson")
    @ResponseBody
    public Person addPerson() {
        Person person = new Person();
        personService.addPerson(person);
        if (person != null) {
            SetParams params = new SetParams();
            params.ex(1000);
            params.nx();
            //redisSetResult成功'OK'失败返回'null'
            try {
                String redisSetResult = jedisCluster.set("pid-" + person.getId(), JSONObject.fromObject(person).toString(), params);
                logger.info("存放person,pid={},redisSetResult={}", person.getId(), redisSetResult);
            } catch (Exception e) {
               logger.error("放入缓存失败", e);
            }
            return person;
        } else {
            return new Person();
        }
    }
    @RequestMapping(value = "delPerson/{pid}")
    @ResponseBody
    public Person delPerson(@PathVariable(value = "pid") Integer pid) {
        return personService.delPerson(pid);
    }
    public static void main(String[] args) {
        System.out.println(new Random().nextInt(2));
    }
}
