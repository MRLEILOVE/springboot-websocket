package com.bittrade.entrust.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bittrade.common.constant.IQueueConstants;

@Configuration
public class MyRabbitMqConfiguration {

	/**
	 * 交换配置
	 *
	 * @return
	 */
	@Bean
	public DirectExchange messageDirectExchange() {
		return (DirectExchange) ExchangeBuilder.directExchange(IQueueConstants.MESSAGE_EXCHANGE).durable(true).build();
	}

	/**
	 * 消息队列声明
	 *
	 * @return
	 */
	@Bean
	public Queue messageQueue() {
		return QueueBuilder.durable(IQueueConstants.MESSAGE_QUEUE_NAME).build();
	}

	/**
	 * 消息绑定
	 *
	 * @return
	 */
	@Bean
	public Binding messageBinding() {
		return BindingBuilder.bind(messageQueue()).to(messageDirectExchange()).with(IQueueConstants.MESSAGE_ROUTE_KEY);
	}

}