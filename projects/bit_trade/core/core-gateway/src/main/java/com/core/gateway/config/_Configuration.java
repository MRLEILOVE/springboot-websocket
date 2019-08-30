package com.core.gateway.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.validation.Validator;

import com.core.gateway.limiter.CustomRedisRateLimiter;
import com.core.gateway.limiter.RateLimiterGatewayFilterFactory;

@Configuration
public class _Configuration {

	@Bean
	@Primary
	public CustomRedisRateLimiter customRedisRateLimiter(ReactiveRedisTemplate<String, String> redisTemplate,
			@Qualifier(CustomRedisRateLimiter.REDIS_SCRIPT_NAME) RedisScript<List<Long>> redisScript, Validator validator) {
		return new CustomRedisRateLimiter(redisTemplate, redisScript, validator);
	}

	@Bean
	public RateLimiterGatewayFilterFactory rateLimiterGatewayFilterFactory(
			CustomRedisRateLimiter customRedisRateLimiter, CustomKeyResolver customKeyResolver) {
		return new RateLimiterGatewayFilterFactory(customRedisRateLimiter, customKeyResolver);
	}

}
