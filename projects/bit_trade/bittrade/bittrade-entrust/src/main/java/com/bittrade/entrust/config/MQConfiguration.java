package com.bittrade.entrust.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bittrade.common.constant.IQueueConstants;
import com.bittrade.common.enums.KLineGranularityEnumer;

@Configuration
public class MQConfiguration implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware {

	private ApplicationContext applicationContext;
	
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
//	@Bean(name = IQueueConstants.QUEUE__KLINE)
//	public Queue messageQueue__KLINE() {
//		return QueueBuilder.durable(IQueueConstants.QUEUE__KLINE).build();
//	}

	/**
	 * 交换配置
	 *
	 * @return
	 */
//	@Bean
//	public DirectExchange messageDirectExchange() {
//		return (DirectExchange) ExchangeBuilder.directExchange(IQueueConstants.EXCHANGE_DIRECT).durable(true).build();
//	}

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
//	@Bean
//	public Binding messageBinding__KLINE() {
//		return BindingBuilder.bind(messageQueue__KLINE()).to(messageTopicExchange()).with(IQueueConstants.ROUTE_KEY__KLINE);
//	}
	
	private void initialize(BeanDefinitionRegistry registry) {
		KLineGranularityEnumer objArr_enum[] = KLineGranularityEnumer.values();
		if (objArr_enum != null && objArr_enum.length > 0) {
			for (int i = 0; i < objArr_enum.length; i++) {
				Integer i_code = objArr_enum[i].getCode();
				String str_queueName = IQueueConstants.QUEUE__KLINE + i_code;
				
				GenericBeanDefinition beanDefinition_queue = new GenericBeanDefinition();
				beanDefinition_queue.setBeanClass(Queue.class);
				beanDefinition_queue.getConstructorArgumentValues().addGenericArgumentValue( str_queueName );
	//			beanDefinition.getPropertyValues().add("name", "张三");
				registry.registerBeanDefinition(str_queueName, beanDefinition_queue);
				
				Queue queue = applicationContext.getBean(str_queueName, Queue.class);
//				Binding binding = BindingBuilder.bind(queue).to(messageTopicExchange()).with(IQueueConstants.ROUTE_KEY__KLINE + i_code);
//				GenericBeanDefinition beanDefinition_binding = new GenericBeanDefinition();
//				beanDefinition_binding.setBeanClass(Binding.class);
//				beanDefinition_binding.getConstructorArgumentValues().addGenericArgumentValue( str_queueName );
//				beanDefinition_binding.getConstructorArgumentValues().addGenericArgumentValue( Binding.DestinationType.QUEUE );
//				beanDefinition_binding.getConstructorArgumentValues().addGenericArgumentValue( IQueueConstants.EXCHANGE_TOPIC );
//				beanDefinition_binding.getConstructorArgumentValues().addGenericArgumentValue( IQueueConstants.ROUTE_KEY__KLINE + i_code );
//				registry.registerBeanDefinition("beanDefinition_binding." + i_code, beanDefinition_binding);
//				BeanDefinitionBuilder beanDefinitionBuilder_binding = BeanDefinitionBuilder.genericBeanDefinition(Binding.class);
//				beanDefinitionBuilder_binding.addConstructorArgValue(str_queueName);
//				beanDefinitionBuilder_binding.addConstructorArgValue(Binding.DestinationType.QUEUE);
//				beanDefinitionBuilder_binding.addConstructorArgValue(IQueueConstants.EXCHANGE_TOPIC);
//				beanDefinitionBuilder_binding.addConstructorArgValue(IQueueConstants.ROUTE_KEY__KLINE + i_code);
//				beanDefinitionBuilder_binding.setAutowireMode(AUTOWIRE_CONSTRUCTOR);
//				registry.registerBeanDefinition("beanDefinition_binding." + i_code, beanDefinitionBuilder_binding.getBeanDefinition());
				
				registry.registerBeanDefinition("beanDefinition_binding." + i_code, 
						BeanDefinitionBuilder.genericBeanDefinition(
								Binding.class, 
								() -> BindingBuilder
								.bind(queue)
								.to(messageTopicExchange())
								.with(IQueueConstants.ROUTE_KEY__KLINE + i_code)
								)
						.getBeanDefinition()
						);
			}
		}
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//		((DefaultListableBeanFactory) beanFactory).registerBeanDefinition( beanName, beanDefinition );
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		initialize(registry);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		MQConfiguration.this.applicationContext = applicationContext;
	}

}
