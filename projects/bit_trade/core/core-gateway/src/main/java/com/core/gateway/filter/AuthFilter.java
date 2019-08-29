package com.core.gateway.filter;

import java.nio.charset.StandardCharsets;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.alibaba.fastjson.JSON;
import com.core.common.DTO.ReturnDTO;

import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GlobalFilter {
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		String token = exchange.getRequest().getHeaders().getFirst("token");
		if ("token".equals(token) || true) {
			return chain.filter(exchange);
		}
		ServerHttpResponse response = exchange.getResponse();
		ReturnDTO<Object> data = ReturnDTO.ok(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase()); // "未认证"
		byte[] datas = JSON.toJSONString(data).getBytes(StandardCharsets.UTF_8);
		DataBuffer buffer = response.bufferFactory().wrap(datas);
		response.setStatusCode(HttpStatus.UNAUTHORIZED);
		response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
		return response.writeWith(Mono.just(buffer));
	}
}