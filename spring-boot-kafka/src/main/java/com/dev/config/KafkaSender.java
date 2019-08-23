package com.dev.config;

import com.alibaba.fastjson.JSON;
import com.dev.bean.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * <p>Title: KafkaSender</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2019-08-21 14:35
 */
@Component
public class KafkaSender<T> {
    private static Logger log = LoggerFactory.getLogger(KafkaSender.class);
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private Environment environment;
    @Autowired
    private RedisTemplate redisTemplate;
    //发送消息方法
    public void send(T obj) {
        Message jsonObj = (Message) obj;
        if (jsonObj.getTimes() == null) {
            jsonObj.setTimes(10L);
        }
        int times= jsonObj.getTimes().intValue();
        //发送消息
        for (int i = 0; i < times; i++) {
            jsonObj = new Message();
            jsonObj.setId(redisTemplate.opsForValue().increment("msg_times"));
            jsonObj.setTimes(System.currentTimeMillis());
            jsonObj.setMsg(UUID.randomUUID().toString().replaceAll("-","").toUpperCase());
            jsonObj.setSendTime(new Date());
            jsonObj.setStartTime(jsonObj.getSendTime());
            ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TopicConst.EXECUTOR_TOPIC, TopicConst.EXECUTOR_TOPIC + "_" + jsonObj.getId() + "_" + System.currentTimeMillis(), JSON.toJSONString(jsonObj));
            int finalI = i;
            future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    log.info("Produce: The message failed to be sent:", throwable);
                }

                @Override
                public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                    log.info("Produce--->The message was sent successfully _+_+_+_+_+_+_+ result<{}>:{} ", finalI, stringObjectSendResult.toString());
                }
            });
        }
    }
}
