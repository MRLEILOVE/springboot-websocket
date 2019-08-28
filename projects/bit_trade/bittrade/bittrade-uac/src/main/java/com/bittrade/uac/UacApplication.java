package com.bittrade.uac;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.core.framework.BaseApplication;
import org.mybatis.spring.annotation.MapperScan;
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
public class UacApplication extends BaseApplication {
    public static void main(String[] args) {
        run(UacApplication.class, args);
    }
}
