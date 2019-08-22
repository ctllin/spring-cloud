package com.dev.config;

import com.alibaba.fastjson.JSON;
import com.dev.bean.Message;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * <p>Title: KafkaReceiver</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 * Spring-Kafka中消息监听大致分为两种类型，一种是单条数据消费，一种是批量消费；
 * @author guolin
 * @version 1.0
 * @date 2019-08-21 14:40
 */
@Component
public class KafkaReceiver2 {
    private static Logger log = LoggerFactory.getLogger(KafkaReceiver2.class);
    @Autowired
    private Environment environment;
    final static Properties props = new Properties();

    private Map<String, Object> consumerProps2() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty("spring.kafka.bootstrap-servers"));
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        //一次拉取消息数量
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 4); //对应batchListener(List<String> messages) 一次最多可以拉取4条数据
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG,1000);
        return props;
    }


    @Bean("batchContainerFactory2")
    public ConcurrentKafkaListenerContainerFactory listenerContainer() {
        ConcurrentKafkaListenerContainerFactory factory  = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory(consumerProps2()));
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        //设置并发量，小于或等于Topic的分区数
        //ConsumerConfig会初始化5次 onPool-worker-3,onPool-worker-2,onPool-worker-1,hListener-0-C-1,对应batchListener(List<String> messages)
        //最多会有四个线程取10条数据
        factory .setConcurrency(4);
        //设置为批量监听
        factory .setBatchListener(true);
        factory.getContainerProperties().setPollTimeout(3000);
        return factory;
    }

    @KafkaListener(id = "batchListener2",
            topics = {TopicConst.EXECUTOR_TOPIC},
            containerFactory = "batchContainerFactory2",
            groupId = TopicConst.EXECUTOR_GROUPID_2)
    public void batchListener3(List<ConsumerRecord<String ,String>> data, Acknowledgment ack, Consumer<String,String> consumer) { //MAX_POLL_RECORDS_CONFIG
        long timenow = System.currentTimeMillis();
        try {
            Thread.sleep(new Random().nextInt(100) + 50);
        } catch (InterruptedException e) {
        }
        if (data != null && data.size() != 0) {
            int size = data.size();
            data.parallelStream().forEach(message -> {
                Message msg = JSON.parseObject( message.value(), Message.class);
                log.info("Receiver2--->batchListener3 size={}, {} 接收消息 message ={},record={}", size, timenow, JSON.toJSONString(msg),JSON.toJSONString(message));
            });
        }
        if (new Random().nextInt(100) % 3 == 1) {
            ack.acknowledge();
            System.out.println("Receiver2-->acknowledge");
        }
    }

}
