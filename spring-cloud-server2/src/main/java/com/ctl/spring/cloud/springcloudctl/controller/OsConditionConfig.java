package com.ctl.spring.cloud.springcloudctl.controller;

import com.ctl.common.bean.Person3;
import com.ctl.spring.cloud.springcloudctl.bean.Person;
import com.ctl.spring.cloud.springcloudctl.condition.LinuxCondition;
import com.ctl.spring.cloud.springcloudctl.condition.WindowsCondition;
import com.ctl.spring.cloud.springcloudctl.service.ListDirService;
import com.ctl.spring.cloud.springcloudctl.service.impl.LinuxListDirServiceImpl;
import com.ctl.spring.cloud.springcloudctl.service.impl.WindowsListDirServiceImpl;
import net.sf.json.JSONObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Title: OsConditionConfig</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-02-25 14:49
 */

@Configuration
public class OsConditionConfig {

    @Bean
    @Conditional(WindowsCondition.class)
    public ListDirService windowsListService() {
        return new WindowsListDirServiceImpl();
    }

    @Bean
    @Conditional(LinuxCondition.class)
    public ListDirService linuxListService() {
        return new LinuxListDirServiceImpl();
    }

    @Bean
    //如果person.condition在配置文件中并且值为ctllin,当使用@Autowired(required = false) private Person3 person3;如果配置文件中符合条件则有值否则为空
    @ConditionalOnProperty(name = "person.condition", havingValue = "ctllin")
    public Person3 setPersonByCondition(){
        Person3 person = new Person3();
        person.setName("ConditionalOnClass");
        System.out.println("ConditionalOnProperty生效,server2 "+ JSONObject.fromObject(person));
        return person;
    }
}