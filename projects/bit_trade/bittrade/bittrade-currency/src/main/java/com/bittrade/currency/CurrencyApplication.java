package com.bittrade.currency;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.core.framework.BaseApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableEurekaClient
//@EnableDiscoveryClient
@SpringBootApplication
@EnableSwagger2
public class CurrencyApplication extends BaseApplication {

	public static void main(String[] args) {
		run(CurrencyApplication.class, args, "currency");
	}

}
