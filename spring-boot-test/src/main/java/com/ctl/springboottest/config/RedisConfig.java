package com.ctl.springboottest.config;

import com.ctl.springboottest.service.ClusterRedisClient;
import com.ctl.springboottest.service.RedisClient;
import com.ctl.springboottest.service.SingleRedisClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * <p>Title: RedisConfig</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-05-09 20:05
 */
@Configuration
@PropertySource("classpath:config.properties")
@ConfigurationProperties(prefix="redis")
public class RedisConfig {
    //redis.type=1
    @Bean
    @ConditionalOnProperty(name = "type", havingValue = "1")
    public RedisClient singleRedisClient() {
        return new SingleRedisClient();
    }
    //redis.type=2
    @Bean
    @ConditionalOnProperty(name = "type", havingValue = "2")
    public RedisClient clusterRedisClient() {
        return new ClusterRedisClient();
    }
}
