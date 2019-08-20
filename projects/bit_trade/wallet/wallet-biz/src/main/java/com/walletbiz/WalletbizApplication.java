package com.walletbiz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.spring4all.swagger.EnableSwagger2Doc;

@SpringBootApplication
@MapperScan("com.walletbiz.mapper")
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@EnableSwagger2Doc
@EnableAsync
@EnableScheduling
public class WalletbizApplication {

    public static void main(String[] args) {
        SpringApplication.run(WalletbizApplication.class, args);
    }

}
