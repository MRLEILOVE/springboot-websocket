package com.bittrade.entrust;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.core.framework.BaseApplication;

@MapperScan(value = { "com.bittrade.entrust.dao" })
//@EnableDiscoveryClient
@SpringBootApplication
public class EntrustApplication extends BaseApplication {

	public static void main(String[] args) {
		run(EntrustApplication.class, args, "entrust");
	}

}
