package com.ctl.sharding.config;

import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.PostConstruct;

import com.ctl.sharding.controller.StaticAutowireController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import com.alibaba.dubbo.common.utils.NetUtils;
import com.alibaba.dubbo.config.ProtocolConfig;
import org.springframework.stereotype.Component;

/**
 * com.ctl.sharding.controller
 * DynamicDubboPortReader
 * 一个机器部署两个dubbo生产者会产生端口占用问题，为了解决这个问题，在加载dubbo配置文件之前，先设置没被占用的端口
 * 启动时获取随机端口
 * ctl 2019/3/23 0:16
 */
@Component
public class DynamicDubboPortReader implements ApplicationContextAware {
    protected static final Logger logger = LoggerFactory.getLogger(StaticAutowireController.class);
    @Autowired
    private ApplicationContext applicationContext;

    private int port = 20880;

    @PostConstruct
    public void init() {
        Map<String, ProtocolConfig> map = applicationContext.getBeansOfType(ProtocolConfig.class);
        for (Entry<String, ProtocolConfig> con : map.entrySet()) {
            port = NetUtils.getAvailablePort();
            logger.info("dubbo服务提供者name={},port={}", con.getValue().getName(), port);
            con.getValue().setPort(port);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
//方法一：此时将端口改为其它端口都不起作用，可以将生产者端的端口改为-1，使用随机端口，可以解决问题
//<dubbo:provider protocol="dubbo" port="-1"/>