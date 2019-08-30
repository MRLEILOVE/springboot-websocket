package com.core.gateway.limiter;

import java.util.Map;

import lombok.Data;

@Data
public class LimitConfig {

	private String routeId;
	private Map<String, com.core.gateway.limiter.CustomRedisRateLimiter.Config> tokenConfig;

}
