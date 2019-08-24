package com.dev.demo.config;

/**
 * <p>Title: RedisClusterConfig</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-08-24 13:56
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
@ConfigurationProperties(prefix = "spring.redis.cluster")
public class RedisClusterConfig {
    private static final Logger logger = LoggerFactory.getLogger(RedisClusterConfig.class);
    String address;
    String password;
    JedisPoolConfig poolConfig;
    JedisCluster jedisCluster;
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JedisPoolConfig getPoolConfig() {
        return poolConfig;
    }

    public void setPoolConfig(JedisPoolConfig poolConfig) {
        this.poolConfig = poolConfig;
    }

    public JedisCluster getJedisCluster() {

        return jedisCluster;
    }

    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

//    @Bean
//    RedisClusterConfiguration redisClusterConfguration() {
//        RedisClusterConfiguration configuration = null;
//        try {
//            configuration = new RedisClusterConfiguration();
//            List<RedisNode> nodes = new ArrayList<>();
//            Arrays.asList(address.split(",")).parallelStream().forEach(node -> nodes.add(new RedisNode(node.split(":")[0], Integer.parseInt(node.split(":")[1]))));
//            configuration.setPassword(RedisPassword.of(password));
//            configuration.setClusterNodes(nodes);
//        } catch (Exception e) {
//            logger.error("",e);
//        }
//        return configuration;
//    }
//
//    @Bean
//    JedisConnectionFactory jedisConnectionFactory() {
//        JedisConnectionFactory factory = new JedisConnectionFactory(redisClusterConfguration(), poolConfig);
//        return factory;
//    }
//
//    @Bean
//    RedisTemplate redisTemplate() {
//        RedisTemplate redisTemplate = new RedisTemplate();
//        redisTemplate.setConnectionFactory(jedisConnectionFactory());
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
//        return redisTemplate;
//    }
//
//    @Bean
//    StringRedisTemplate stringRedisTemplate() {
//        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(jedisConnectionFactory());
//        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
//        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
//        return stringRedisTemplate;
//    }
}