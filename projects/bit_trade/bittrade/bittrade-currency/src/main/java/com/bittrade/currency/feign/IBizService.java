package com.bittrade.currency.feign;

import javax.validation.Valid;

import com.bittrade.currency.feign.fallBack.BizFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "jdcloud-provider-biz", fallback = BizFallBack.class)
public interface IBizService {

	// 服务中方法的映射路径
	@RequestMapping("/auth/orderEntrust/list/{type}")
	Object list(@PathVariable Integer type);
	
	@RequestMapping("/contractMicro/list")
	Object list(@RequestBody @Valid Object map_val);
	
	@RequestMapping(value="/contractMicro/list1", method = RequestMethod.POST)
	String list1();

}
