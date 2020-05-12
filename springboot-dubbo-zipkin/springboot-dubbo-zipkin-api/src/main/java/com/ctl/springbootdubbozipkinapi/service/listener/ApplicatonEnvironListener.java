package com.ctl.springbootdubbozipkinapi.service.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

/**
 * <p>Title: ApplicatonEnvironListener</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *  springboot默认加载application.properties 修改成 加载config.properties
 *  META-INF/spring.factories
 *  org.springframework.boot.SpringApplicationRunListener=\
 *  ApplicatonEnvironListener
 * @author guolin
 * @version 1.0
 * @date 2019-08-16 17:53
 */
public class ApplicatonEnvironListener implements SpringApplicationRunListener, PriorityOrdered {
    private static Logger logger = LoggerFactory.getLogger(ApplicatonEnvironListener.class);

    private SpringApplication application;

    private String[] args;
    /**
     * 通过反射创建该实例对象的，构造方法中的参数要加上如下参数
     */
    public ApplicatonEnvironListener(SpringApplication application, String[] args){
        this.application = application;
        this.args = args;
    }

    /**
     * 在准备环境之间调用
     * SpringApplication#run -> listeners.starting();
     */
    @Override
    public void starting() {
        logger.info("#############ApplicatonEnvironListener starting,application={},args={}#############",application,args);
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        Properties properties = new Properties();
        try {
            //demo.properties就是我们自定义的配置文件，extension是自定义目录
            properties.load(this.getClass().getClassLoader().getResourceAsStream("config-resource.properties"));
            properties.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
            PropertySource propertySource =new  PropertiesPropertySource("config",properties);
            //PropertySource是资源加载的核心
            MutablePropertySources propertySources = environment.getPropertySources();
            //这里添加最后
            propertySources.addLast(propertySource);
            //spring.redis.cluster.nodes=${REDIS_CLUSTER}
            //spring.redis.host=${REDIS_HOST}
            if("1".equals(properties.get("REDIS_TYPE"))){ //redis 1单机 2集群
                properties.remove("spring.redis.cluster.nodes");
            }else {
                properties.remove("spring.redis.host");
            }
        } catch (IOException e) {
           logger.error("load config.properties error",e);
        }
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        logger.info("contextPrepared={}",context.getApplicationName());
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        logger.info("contextLoaded={}",context.getApplicationName());
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        logger.info("started={}",context.getApplicationName());
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        String[] beans = context.getBeanDefinitionNames();
        Arrays.stream(beans).filter(bean -> !context.getBean(bean).getClass().toString().contains("spring"))
                .forEach(bean -> logger.info("system provider beans={}, of Type ::{} ", bean, context.getBean(bean).getClass().toString()));
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        logger.info("context={},failed",context,exception);
    }

    /**
     * 这里可以设置该配置文件加载的顺序，在application.yml之前还是之后
     * EventPublishingRunListener#getOrder方法返回 “0”，按照需求这里我们这是比0大，
     * 即在application.yml之后加载，这样在application.yml配置时，可以“覆盖”my.yml
     */
    @Override
    public int getOrder() {
        return 1;
    }
}