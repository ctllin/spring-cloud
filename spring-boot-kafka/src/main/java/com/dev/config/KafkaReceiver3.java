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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * <p>Title: KafkaReceiver</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 * Spring-Kafka中消息监听大致分为两种类型，一种是单条数据消费，一种是批量消费；
 *
 * @author guolin
 * @version 1.0
 * @date 2019-08-21 14:40
 */
@Component
public class KafkaReceiver3 {
    private static Logger log = LoggerFactory.getLogger(KafkaReceiver3.class);
    @Autowired
    private Environment environment;
    final static Properties props = new Properties();
    @Autowired
    private KafkaMessageListener kafkaMessageListener;
    /**
     *  consumer属性配置，hashMap
     * @return
     */
    private Map<String, Object> consumerProps3() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty("spring.kafka.bootstrap-servers"));
        props.put(ConsumerConfig.GROUP_ID_CONFIG, TopicConst.EXECUTOR_GROUPID_3);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);//非自动提交 需要在com.dev.config.KafkaMessageListener调用  acknowledgment.acknowledge()
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG,1000);
        return props;
    }

    /**
     *  Kafka消费者工厂，DefaultKafkaConsumerFactory
     * @return
     */
    @Bean("consumerFactory3")
    @Primary
    public DefaultKafkaConsumerFactory consumerFactory() {
        DefaultKafkaConsumerFactory container = new DefaultKafkaConsumerFactory(consumerProps3());
        return container;
    }


    @PostConstruct
    public void TestLinstener() {

        KafkaMessageListenerContainer<String, String> container = createContainer();
        container.setBeanName("messageListenerContainer");
        container.start();
    }

    private KafkaMessageListenerContainer<String, String> createContainer() {
        ContainerProperties containerProps2 = new ContainerProperties("topic_001");
        containerProps2.setMessageListener(kafkaMessageListener);
        containerProps2.setAckMode(ContainerProperties.AckMode.MANUAL);

        KafkaMessageListenerContainer<String, String> container = new KafkaMessageListenerContainer<>(consumerFactory(),containerProps2);
        return container;
    }


}
