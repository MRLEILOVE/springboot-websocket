package com.bittrade.batch.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.core.framework.BaseApplication;

@ComponentScan("com.bittrade.batch")
@EnableScheduling
@SpringBootApplication
@EnableDubbo
public class BatchApplication extends BaseApplication {

	public static void main(String[] args) {
		run( BatchApplication.class, args, "batch" );
	}
}
