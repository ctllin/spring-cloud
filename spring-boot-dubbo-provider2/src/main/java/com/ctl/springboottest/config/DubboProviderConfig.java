package com.ctl.springboottest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * com.ctl.sharding.config
 * DubboConfig
 * ctl 2019/3/23 1:24
 */
@Configuration
//引入dubbo服务提供者
@ImportResource("classpath:providerZookeeper.xml")
public class DubboProviderConfig {
}
