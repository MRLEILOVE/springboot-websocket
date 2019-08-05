package com.bittrade.currency.feign;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

@Component
public class BizFallBack implements IBizService {

	// 实现的方法是服务调用的降级方法
	@Override
	public Object list(Integer type) {
		return "error -- com.bittrade.currency.feign.BizFallBack.list(Integer) type=" + type;
	}

	@Override
	public Object list(@Valid Object map_val) {
		return "error -- com.bittrade.currency.feign.BizFallBack.list(Map<String, Object>) map_val=" + map_val;
	}

	@Override
	public String list1() {
		System.out.println("error list1");
		return null;
	}

}
