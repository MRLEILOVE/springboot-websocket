package com.wallet.biz.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import com.wallet.biz.vo.CreateAddressParamDto;

//@FeignClient(name = "wallet-service", configuration = OAuth2FeignAutoConfiguration.class, fallback = ICreateAddressFactory.CreateAddressServiceFallback.class)
@FeignClient(name = "wallet-service", fallback = ICreateAddressFactory.CreateAddressServiceFallback.class)
public interface ICreateAddressFactory {
	@PostMapping("/create")
	String create(CreateAddressParamDto paramDto);

	@Component
	static class CreateAddressServiceFallback implements ICreateAddressFactory {

		@Override
		public String create(CreateAddressParamDto paramDto) {
			System.out.println( "进入Hystrix熔断处理逻辑..." );
			return new String();
		}
	}
}
