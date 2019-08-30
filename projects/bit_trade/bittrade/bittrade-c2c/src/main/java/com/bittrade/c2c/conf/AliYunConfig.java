package com.bittrade.c2c.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@PropertySource("classpath:application.yaml")
@Configuration
@ConfigurationProperties(prefix = "bit-trade.aliyun")
public class AliYunConfig {

	private String accessKeyId;
	private String accessKeySecret;
	private String endpoint;
	private String bucketName;
	private String bucketDomain;
	private String region;
	private String roleArn;
}
