package com.dev.controller ;

import com.alibaba.fastjson.JSON;
import com.dev.bean.Message;
import com.dev.config.KafkaSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Title: KafkaController</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2018</p>
 * <p>Company: www.hanshow.com</p>
 *
 * @author guolin
 * @version 1.0
 * @date 2018-11-30 16:03
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private KafkaSender sender;
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public Object sendKafka(String key, Message message) {
        try {
            logger.info("kafka的消息={}", JSON.toJSONString(message));
            sender.send(message);
            return "SUCCESS";
        } catch (Exception e) {
            logger.error("发送kafka失败", e);
            return "FAIL";
        }
    }
}