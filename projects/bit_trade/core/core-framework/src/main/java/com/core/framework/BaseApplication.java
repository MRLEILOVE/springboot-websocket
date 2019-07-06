package com.core.framework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

//@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@MapperScan(value = { "com.**.api.dao" })
@ComponentScan(basePackages = { "com.core.framework.props", "com.core.framework.config" })
public abstract class BaseApplication {

	/**
	 * 
	 * @param cls
	 * @param args
	 * @param name
	 */
	protected static final void run(Class<?> cls, String[] args, String name) {
		System.setProperty("svc_name", name);
//		System.setProperty("spring.devtools.restart.enabled", "false");

		SpringApplication.run(cls, args);
	}

}
