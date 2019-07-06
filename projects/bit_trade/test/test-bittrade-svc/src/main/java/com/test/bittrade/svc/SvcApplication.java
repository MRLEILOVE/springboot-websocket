package com.test.bittrade.svc;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import com.core.framework.BaseApplication;

@EnableEurekaClient
//@EnableDiscoveryClient
@SpringBootApplication
public class SvcApplication extends BaseApplication {

	public static void main(String[] args) {
		run(SvcApplication.class, args, "svc");
	}

}
