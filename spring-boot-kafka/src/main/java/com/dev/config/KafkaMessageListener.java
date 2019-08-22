package com.dev.config;

import com.alibaba.fastjson.JSON;
import com.dev.bean.Message;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * <p>Title: KafkaMessageListener</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 * 3、监听接口，AcknowledgingMessageListener
 *
 * @author guolin
 * @version 1.0
 * @date 2019-08-22 15:07
 */
@Component(value = "kafkaMessageListener")
public class KafkaMessageListener implements AcknowledgingMessageListener<String, String> {
    private final Logger logger = LoggerFactory.getLogger(KafkaMessageListener.class);

    @Override
    public void onMessage(final ConsumerRecord<String, String> message, final Acknowledgment acknowledgment) {
        System.out.println(message);
        Message record = JSON.parseObject(message.value(), Message.class);
        if (new Random().nextInt(100) % 3 == 1) {
            try {
                acknowledgment.acknowledge();
                System.out.println("KafkaReceiver3-->acknowledge");
            } catch (Exception e) {
                logger.error("KafkaReceiver3",e);
            }
        }
    }
}
