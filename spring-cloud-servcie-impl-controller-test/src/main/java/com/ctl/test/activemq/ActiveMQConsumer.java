package com.ctl.test.activemq;

import javax.jms.ConnectionFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQConsumer {
	protected final Logger logger = LoggerFactory.getLogger(ActiveMQConsumer.class);
	@JmsListener(destination = "sample.queue")
	public void receiveQueue(String text) {
		logger.info("activemq接收消息queue, msg={}",text);
	}
	
	@Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(activeMQConnectionFactory);
        return bean;
    }

	
	@JmsListener(destination = "smaple.topic", containerFactory="jmsListenerContainerTopic")
	public void receiveTopic(String text) {
		logger.info("activemq接收消息topic, msg={}",text);
	}
}
