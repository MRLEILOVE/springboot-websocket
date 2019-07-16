package com.core.framework.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.redis.jedis.pool", ignoreUnknownFields = true)
public class RedisProperties {

	private String[] nodes;
	private int maxTotal;
	private int maxIdle;
	private int minIdle;
	private long maxWait;
	private String password;
	private int commandTimeout;

}
