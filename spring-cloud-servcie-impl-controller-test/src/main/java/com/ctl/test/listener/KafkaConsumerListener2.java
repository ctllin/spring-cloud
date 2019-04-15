package com.ctl.test.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * <p>Title: KafkaConsumerListener</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2018-11-30 16:01
 */
@Component
public class KafkaConsumerListener2 {
    Logger logger = LoggerFactory.getLogger(KafkaConsumerListener2.class);
    //@KafkaListener(groupId = "group_01", topics = "spring_cloud_topic")
    @KafkaListener(groupId = "${spring.kafka.consumer.group-id}}", topics = "${kafka.defaultTopic}")
    public void listen(ConsumerRecord<String, String> record) {
        logger.info("kafka的key:{},value={} ", record.key(), record.value());
    }
}
