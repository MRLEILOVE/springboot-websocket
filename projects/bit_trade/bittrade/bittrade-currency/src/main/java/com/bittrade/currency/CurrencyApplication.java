package com.bittrade.currency;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.core.framework.BaseApplication;

//@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
@EnableDubbo
public class CurrencyApplication extends BaseApplication {

	public static void main(String[] args) {
		run(CurrencyApplication.class, args);
	}

}
