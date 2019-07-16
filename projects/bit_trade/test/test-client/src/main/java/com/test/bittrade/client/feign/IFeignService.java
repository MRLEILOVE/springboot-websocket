package com.test.bittrade.client.feign;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//configuration = xxx.class  这个类配置Hystrix的一些精确属性
//value=“你用到的服务名称”
@FeignClient(value = "bittrade-svc",fallback = FeignFallBack.class)
public interface IFeignService {
	//服务中方法的映射路径
	@RequestMapping("/svc")
	String svc(@RequestParam(value="str_") String str_);

	@RequestMapping(value = "/hellol", method= RequestMethod.GET)
	String hello(@RequestParam("name") String name) ;

	@RequestMapping(value = "/hello2", method= RequestMethod.GET)
	User hello(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

	@RequestMapping(value = "/hello3", method= RequestMethod.POST)
	String hello(@RequestBody User user);
}