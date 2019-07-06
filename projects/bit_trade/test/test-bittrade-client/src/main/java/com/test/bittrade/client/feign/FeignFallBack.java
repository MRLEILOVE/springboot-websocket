package com.test.bittrade.client.feign;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Component;

@Component
public class FeignFallBack implements IFeignService{
	//实现的方法是服务调用的降级方法
	@Override
	public String svc(String str_) {
		return "error -- com.bittrade.client.feign.FeignFallBack.svc(String)";
	}

	@Override
	public String hello(String name) {
		return "error";
	}

	@Override
	public User hello(String name, Integer age) {
		return new User();
	}

	@Override
	public String hello(User user) {
		return "error";
	}
}