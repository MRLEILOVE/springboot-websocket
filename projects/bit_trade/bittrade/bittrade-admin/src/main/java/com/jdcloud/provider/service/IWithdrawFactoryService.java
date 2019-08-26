package com.jdcloud.provider.service;


import com.jdcloud.provider.dto.WithDrawParamDto;
import com.jdcloud.security.feign.OAuth2FeignAutoConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
@FeignClient(name = "wallet-service", configuration = OAuth2FeignAutoConfiguration.class, fallback = IWithdrawFactoryService.WithServiceFallback.class)
public interface IWithdrawFactoryService {
    @PostMapping("/withdraw")
    String addOrder(WithDrawParamDto paramDto);

    @Component
    static class WithServiceFallback implements IWithdrawFactoryService {


        @Override
        public String addOrder(WithDrawParamDto paramDto) {
            System.out.println("进入Hystrix熔断处理逻辑...");
            return "";
        }
    }
}
