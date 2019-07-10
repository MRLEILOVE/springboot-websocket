package com.bittrade.entrust;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.core.framework.BaseApplication;

@SpringBootApplication
@EnableDubbo
public class EntrustApplication extends BaseApplication {

	public static void main(String[] args) {
		run(EntrustApplication.class, args, "entrust");
	}

}
