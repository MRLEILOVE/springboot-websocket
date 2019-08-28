package com.bittrade.uac;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author
 * @date
 * @deprecated 用户模块
 */
@EnableDubbo
@EnableEurekaClient
@EnableFeignClients
@MapperScan("com.bittrade.uac.mapper")
@SpringBootApplication
public class UacApplication {
    public static void main(String[] args) {
        SpringApplication.run(UacApplication.class, args);
    }
}