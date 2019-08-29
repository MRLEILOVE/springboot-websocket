package com.core.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.common.DTO.ReturnDTO;

@RestController
public class FallbackController {

	@GetMapping("/fallback")
	public ReturnDTO<String> fallback() {
		ReturnDTO<String> response = ReturnDTO.ok(HttpStatus.CONTINUE.value(), "服务暂时不可用");
		return response;
	}
}