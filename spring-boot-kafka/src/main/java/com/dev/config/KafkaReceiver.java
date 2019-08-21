package com.dev.config;

import com.alibaba.fastjson.JSON;
import com.dev.bean.Message;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * <p>Title: KafkaReceiver</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-08-21 14:40
 */
@Component
public class KafkaReceiver {
    private static Logger log = LoggerFactory.getLogger(KafkaReceiver.class);
    @Autowired
    private Environment environment;

    private Map<String, Object> consumerProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty("spring.kafka.bootstrap-servers"));
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 15000);
        //一次拉取消息数量
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 10); //对应batchListener(List<String> messages) 一次最多可以拉取10条数据
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    @Bean("batchContainerFactory")
    public ConcurrentKafkaListenerContainerFactory listenerContainer() {
        ConcurrentKafkaListenerContainerFactory container = new ConcurrentKafkaListenerContainerFactory();
        container.setConsumerFactory(new DefaultKafkaConsumerFactory(consumerProps()));
        //设置并发量，小于或等于Topic的分区数
        //ConsumerConfig会初始化5次 onPool-worker-3,onPool-worker-2,onPool-worker-1,hListener-0-C-1,对应batchListener(List<String> messages)
        //最多会有四个线程取10条数据
        container.setConcurrency(4);
        //设置为批量监听
        container.setBatchListener(true);
        return container;
    }


    final static Properties props = new Properties();

    @KafkaListener(id = "batchListener",
            topics = {TopicConst.EXECUTOR_TOPIC},
            containerFactory = "batchContainerFactory",
            groupId = TopicConst.EXECUTOR_GROUPID)
    public void batchListener(List<String> messages) { //MAX_POLL_RECORDS_CONFIG
        long timenow = System.currentTimeMillis();
        try {
            Thread.sleep(new Random().nextInt(100) + 50);
        } catch (InterruptedException e) {
        }
        if (messages != null && messages.size() != 0) {
            int size = messages.size();
            messages.parallelStream().forEach(message -> {
                Message msg = JSON.parseObject(message, Message.class);
                log.info("Receiver--->batchListener size={}, {} 接收消息 message ={}", size, timenow, JSON.toJSONString(msg));
            });
        }
    }

}
