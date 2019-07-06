package com.core.framework.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring.redis.jedis.pool", ignoreUnknownFields = true)
public class RedisProperties {

	private String[] nodes;
	public int maxTotal;
	public int maxIdle;
	public int minIdle;
	public long maxWait;
	public String password;
	public int commandTimeout;

}
