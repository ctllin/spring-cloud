package com.ctl.springboottest;

import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin.util.UIUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.UUID;

/**
 * ELK+logback+kafka 搭建分布式日志分析平台
 */
@SpringBootApplication
@RestController
public class SpringBootTestApplication {
    private Logger logger = LoggerFactory.getLogger(SpringBootTestApplication.class);
    @Autowired(required = false)
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/")
    public void index() {
        String uuid = UUID.randomUUID().toString();
        logger.trace("日志输出{} ,{},trace", uuid, System.currentTimeMillis());
        logger.debug("日志输出{} ,{}, debug", uuid, System.currentTimeMillis());
        logger.info("日志输出{}  ,{}, info", uuid, System.currentTimeMillis());
        logger.warn("日志输出{}  ,{}, warn", uuid, System.currentTimeMillis());
        logger.error("日志输出{} ,{}, error", uuid, System.currentTimeMillis());
    }

    @RequestMapping("/sendData")
    public String sendData() {
        File file = new File("e://data.json");
        Reader reader = null;
        try {
            List<String> list = FileUtils.readLines(file, "utf-8");
            if (list != null) {
                list.stream().forEach(line -> {
                    kafkaTemplate.send("goods", JSONObject.toJSONString(line));
                    logger.info(line);
                });
            }
        } catch (Exception e) {
            logger.error("sendData", e);
        }

        return "success";
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootTestApplication.class, args);
    }

}
//********************注意 logback配置kafka主题,logstash配置kafka主题,创建的主题要保持一致***********************
//1、启动zookeeper
//2、启动kafka(kafka-server-start.bat "E:\elk\kafka_2.12-1.1.1\config\server.properties")
//3、创建主题 logback-spring.xml中配置的主题为下面生成的主题
//./kafka-topics.bat --zookeeper localhost:2181 --create --topic appkafkalog --partitions 1  --replication-factor 1
//4、启动elasticsearch
//5、启动logstash(./logstash.bat -f ../config/logstash.conf)
//6、启动kibana
//7、启动服务 在浏览器中执行http://localhost:10000(端口配置的是10000)
//8、http://localhost:5601/app/kibana#/management/kibana/index?_g=() 创建index
//9、http://localhost:5601/app/kibana#/discove(discove就可以搜索了)
//10、http://localhost:9200/_cat/indices(在es上查看所有的index)


//logstash.conf
//# Sample Logstash configuration for creating a simple
//		# Beats -> Logstash -> Elasticsearch pipeline.
//
//input {
//kafka {
//bootstrap_servers => ["http://localhost:9092"]
//codec => "json"
//topics => "appkafkalog"
//type => "kafka"
//group_id => "es"
//client_id => "kafka_client"
//consumer_threads => 1
//decorate_events => true
//}
//}
//
//output {
//#  stdout {  codec => rubydebug }
//elasticsearch {
//hosts => ["http://localhost:9200"]
//index => "kafka-log-%{+YYYY.MM.dd}"
//#action => "kafka"
//#user => "elastic"
//#password => "changeme"
//}
//stdout {}
//}


//执行下面
//http://localhost:5601/app/kibana#/dev_tools/console?_g=()
//GET kafka-log-2019.04.13/_search
//	{
//	"query": {
//	"match_all": {}
//	}
//	}


