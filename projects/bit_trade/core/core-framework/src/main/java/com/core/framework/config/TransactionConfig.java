package com.core.framework.config;

import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.core.framework.props.TransactionProperties;

@Configuration // 事务失效,都移动到一个方法不失效， 不注册为@Bean就可以。
//@Component // 事务可行，不用都移动到一个方法
public class TransactionConfig {

	@Autowired
	private TransactionProperties transactionProperties;
	@Autowired
	private PlatformTransactionManager transactionManager;

	// @Bean
	// @ConditionalOnMissingBean
	// public PlatformTransactionManager transactionManager() {
	// return new
	// org.springframework.jdbc.datasource.DataSourceTransactionManager(dataSource);
	// }

	private TransactionInterceptor transactionInterceptor() {
		//		Properties attributes = new Properties();
		//		attributes.setProperty( "add*", "PROPAGATION_REQUIRED,-Exception" );
		//		attributes.setProperty( "remove*", "PROPAGATION_REQUIRED,-Exception" );
		//		attributes.setProperty( "modify*", "PROPAGATION_REQUIRED,-Exception" );
		//		attributes.setProperty( "get*", "PROPAGATION_REQUIRED,-Exception,readOnly" );
		// TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager(), attributes);
		TransactionInterceptor txAdvice = new TransactionInterceptor( transactionManager, transactionProperties.getAttributes() );
		return txAdvice;
	}

	private AspectJExpressionPointcut aspectJExpressionPointcut() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		//		pointcut.setExpression( "execution (* com..service.*.*(..))" );
		pointcut.setExpression( transactionProperties.getExpression() );
		return pointcut;
	}

	@Bean
	public DefaultPointcutAdvisor defaultPointcutAdvisor() {
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
		advisor.setAdvice( transactionInterceptor() );
		advisor.setPointcut( aspectJExpressionPointcut() );
		return advisor;
	}

}