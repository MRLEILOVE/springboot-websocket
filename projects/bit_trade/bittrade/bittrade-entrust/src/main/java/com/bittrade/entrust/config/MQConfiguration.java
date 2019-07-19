package com.bittrade.entrust.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bittrade.common.constant.IQueueConstants;

@Configuration
public class MQConfiguration {

	/**
	 * 消息队列声明
	 *
	 * @return
	 */
	@Bean(name = IQueueConstants.QUEUE__ENTRUST_RECORD)
	public Queue messageQueue__ENTRUST_RECORD() {
		return QueueBuilder.durable(IQueueConstants.QUEUE__ENTRUST_RECORD).build();
	}

	/**
	 * 消息队列声明
	 *
	 * @return
	 */
	@Bean(name = IQueueConstants.QUEUE__KLINE)
	public Queue messageQueue__KLINE() {
		return QueueBuilder.durable(IQueueConstants.QUEUE__KLINE).build();
	}

	/**
	 * 交换配置
	 *
	 * @return
	 */
	@Bean
	public DirectExchange messageDirectExchange() {
		return (DirectExchange) ExchangeBuilder.directExchange(IQueueConstants.EXCHANGE_DIRECT).durable(true).build();
	}

	/**
	 * 交换配置
	 *
	 * @return
	 */
	@Bean
	public TopicExchange messageTopicExchange() {
		return (TopicExchange) ExchangeBuilder.topicExchange(IQueueConstants.EXCHANGE_TOPIC).durable(true).build();
	}

	/**
	 * 消息绑定
	 * @param queue
	 * @return
	 */
	@Bean
	public Binding messageBinding__ENTRUST_RECORD(@Qualifier(value = IQueueConstants.QUEUE__ENTRUST_RECORD) Queue queue) {
		return BindingBuilder.
				bind(messageQueue__ENTRUST_RECORD()/*queue*/).
//				to(messageDirectExchange()).
				to(messageTopicExchange()).
				with(IQueueConstants.ROUTE_KEY__ENTRUST_RECORD);
	}

	/**
	 * 消息绑定
	 *
	 * @return
	 */
	@Bean
	public Binding messageBinding__KLINE() {
		return BindingBuilder.bind(messageQueue__KLINE()).to(messageTopicExchange()).with(IQueueConstants.ROUTE_KEY__KLINE);
	}

}
