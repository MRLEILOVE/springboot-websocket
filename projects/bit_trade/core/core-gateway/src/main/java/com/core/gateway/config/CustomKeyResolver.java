package com.core.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;

import com.alibaba.fastjson.JSON;
import com.core.gateway.limiter.LimitKey;

import reactor.core.publisher.Mono;

@Configuration
public class CustomKeyResolver implements KeyResolver {

	public static final String BEAN_NAME = "customKeyResolver";

	@Override
	public Mono<String> resolve(ServerWebExchange exchange) {
		return Mono.just(getKey(exchange));
	}

	/**
	 * 
	 * @param exchange
	 * @return
	 */
	private String getKey(ServerWebExchange exchange) {

		LimitKey limitKey = new LimitKey();

		limitKey.setApi(exchange.getRequest().getPath().toString());
		limitKey.setBiz(exchange.getRequest().getQueryParams().getFirst("biz"));

		return JSON.toJSONString(limitKey);
	}
}
