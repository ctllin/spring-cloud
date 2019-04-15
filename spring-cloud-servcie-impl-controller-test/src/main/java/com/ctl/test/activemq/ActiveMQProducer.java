package com.ctl.test.activemq;

import javax.jms.Queue;
import javax.jms.Topic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "activemq")
public class ActiveMQProducer {
	protected final Logger logger = LoggerFactory.getLogger(ActiveMQProducer.class);
	//新版本的jsmTemplate同时支持queue和topic发送
	@Autowired(required = false)
	private JmsMessagingTemplate jmsMessagingTemplate;

	@Autowired(required = false)
	private Queue queue;

	@Autowired(required = false)
	private Topic topic;

	@RequestMapping("sendMsg")
	public void send(String msg) {
		logger.info("activemq发送消息queue, msg={}",msg);
		this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
	}
	
	@RequestMapping("sendTopic")
	public void sendTopic(String msg) {
		logger.info("activemq发送消息topic, msg={}",msg);
		this.jmsMessagingTemplate.convertAndSend(this.topic, msg);
	}
	
}
