package com.bittrade.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.core.framework.BaseApplication;

@SpringBootApplication
@MapperScan("com.bittrade.admin.dao")
@EnableAspectJAutoProxy
public class AdminApplication extends BaseApplication {

	public static void main(String[] args) {
		run(AdminApplication.class, args);
	}

}
