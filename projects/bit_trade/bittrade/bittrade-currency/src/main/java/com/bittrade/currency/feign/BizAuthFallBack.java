package com.bittrade.currency.feign;

import org.springframework.stereotype.Component;

@Component
public class BizAuthFallBack implements IBizAuthService {

	// 实现的方法是服务调用的降级方法
	@Override
	public Object list(Integer type) {
		return "error -- com.bittrade.currency.feign.BizAuthFallBack.list(Integer) type=" + type;
	}

}
