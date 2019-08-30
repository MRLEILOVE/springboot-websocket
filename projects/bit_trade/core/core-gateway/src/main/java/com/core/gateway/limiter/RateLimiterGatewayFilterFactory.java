package com.core.gateway.limiter;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;

import com.alibaba.fastjson.JSON;
import com.core.common.DTO.ReturnDTO;

import reactor.core.publisher.Mono;

/**
 * User Request Rate Limiter filter. See https://stripe.com/blog/rate-limiters
 * and
 */
public class RateLimiterGatewayFilterFactory
extends AbstractGatewayFilterFactory<RateLimiterGatewayFilterFactory.Config> {

	public static final String KEY_RESOLVER_KEY = "keyResolver";

	private final RateLimiter<CustomRedisRateLimiter.Config> defaultRateLimiter;
	private final KeyResolver defaultKeyResolver;

	public RateLimiterGatewayFilterFactory(RateLimiter<CustomRedisRateLimiter.Config> defaultRateLimiter, KeyResolver defaultKeyResolver) {
		super(Config.class);
		this.defaultRateLimiter = defaultRateLimiter;
		this.defaultKeyResolver = defaultKeyResolver;
	}

	public KeyResolver getDefaultKeyResolver() {
		return defaultKeyResolver;
	}

	public RateLimiter<CustomRedisRateLimiter.Config> getDefaultRateLimiter() {
		return defaultRateLimiter;
	}

	@Override
	public GatewayFilter apply(Config config) {
		KeyResolver resolver = (config.keyResolver == null) ? defaultKeyResolver : config.keyResolver;
		RateLimiter<CustomRedisRateLimiter.Config> limiter = (config.rateLimiter == null) ? defaultRateLimiter : config.rateLimiter;

		return (exchange, chain) -> {
			Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);

			return resolver.resolve(exchange).flatMap(key ->
			// TODO: if key is empty?
			limiter.isAllowed(route.getId(), key).flatMap(response -> {

				for (Map.Entry<String, String> header : response.getHeaders().entrySet()) {
					exchange.getResponse().getHeaders().add(header.getKey(), header.getValue());
				}

				if (response.isAllowed()) {
					return chain.filter(exchange);
				}
				ServerHttpResponse rs = exchange.getResponse();
				ReturnDTO<Object> data = ReturnDTO.ok(HttpStatus.SWITCHING_PROTOCOLS.value(), "访问过快");
				byte[] datas = JSON.toJSONString(data).getBytes(StandardCharsets.UTF_8);
				DataBuffer buffer = rs.bufferFactory().wrap(datas);
				rs.setStatusCode(HttpStatus.UNAUTHORIZED);
				rs.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
				return rs.writeWith(Mono.just(buffer));
			}));
		};
	}

	public static class Config {
		private KeyResolver keyResolver;
		private RateLimiter<CustomRedisRateLimiter.Config> rateLimiter;
		private HttpStatus statusCode = HttpStatus.TOO_MANY_REQUESTS;

		public KeyResolver getKeyResolver() {
			return keyResolver;
		}

		public Config setKeyResolver(KeyResolver keyResolver) {
			this.keyResolver = keyResolver;
			return this;
		}

		public RateLimiter<CustomRedisRateLimiter.Config> getRateLimiter() {
			return rateLimiter;
		}

		public Config setRateLimiter(RateLimiter<CustomRedisRateLimiter.Config> rateLimiter) {
			this.rateLimiter = rateLimiter;
			return this;
		}

		public HttpStatus getStatusCode() {
			return statusCode;
		}

		public Config setStatusCode(HttpStatus statusCode) {
			this.statusCode = statusCode;
			return this;
		}
	}

}