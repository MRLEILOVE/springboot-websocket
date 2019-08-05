package com.bittrade.currency.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//configuration = xxx.class  这个类配置Hystrix的一些精确属性
@FeignClient(value = "jdcloud-provider-biz", fallback = BizAuthFallBack.class)
public interface IBizAuthService {

	// 服务中方法的映射路径
	@RequestMapping("/auth/orderEntrust/list/{type}")
	Object list(@PathVariable Integer type);

}
