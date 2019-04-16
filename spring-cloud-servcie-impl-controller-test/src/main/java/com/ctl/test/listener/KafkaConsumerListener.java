package com.ctl.test.listener;

import net.sf.json.JSONObject;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * <p>Title: KafkaConsumerListener</p>
 * <p>Description: springMVC支持,springboot不支持</p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2018-11-30 16:01
 */
@Component
public class KafkaConsumerListener implements MessageListener<Integer, String> {
    Logger logger = LoggerFactory.getLogger(KafkaConsumerListener.class);

    @Override
    public void onMessage(ConsumerRecord<Integer, String> integerStringConsumerRecord) {
        Object o = integerStringConsumerRecord.value();
        logger.info(String.valueOf(o));
    }

    @Override
    public void onMessage(ConsumerRecord<Integer, String> data, Acknowledgment acknowledgment) {

        logger.info(JSONObject.fromObject(data).toString());
    }

    @Override
    public void onMessage(ConsumerRecord<Integer, String> data, Consumer<?, ?> consumer) {
        logger.info(JSONObject.fromObject(data).toString());

    }

    @Override
    public void onMessage(ConsumerRecord<Integer, String> data, Acknowledgment acknowledgment, Consumer<?, ?> consumer) {
        logger.info(JSONObject.fromObject(data).toString());

    }
}
