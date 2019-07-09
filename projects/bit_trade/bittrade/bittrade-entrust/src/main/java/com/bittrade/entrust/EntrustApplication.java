package com.bittrade.entrust;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.core.framework.BaseApplication;

@SpringBootApplication
public class EntrustApplication extends BaseApplication {

	public static void main(String[] args) {
		run(EntrustApplication.class, args, "entrust");
	}

}
