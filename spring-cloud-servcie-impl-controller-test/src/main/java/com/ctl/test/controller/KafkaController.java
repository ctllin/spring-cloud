package com.ctl.test.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired(required = false)
    private KafkaTemplate<String, String> kafkaTemplate;
    @Resource
    private Environment environment;
    @RequestMapping(value = "/send")
    @ResponseBody
    public Object hello() {
        logger.info("kafka/hello is excute");
        Long key = System.currentTimeMillis();
        String value = UUID.randomUUID().toString();
        kafkaTemplate.send(environment.getProperty("kafka.defaultTopic"), key.toString(), value);
        String value2 = UUID.randomUUID().toString();
        kafkaTemplate.send(environment.getProperty("kafka.defaultTopic"), value2);
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("key", key);
        returnMap.put("value", value);
        returnMap.put("value2", value2);
        return returnMap;
    }
}
//bin/zookeeper-server-start.sh config/zookeeper.properties
//bin/kafka-server-start.sh config/server.properties