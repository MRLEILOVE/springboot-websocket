package com.td.controller;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.td.model.User;

/**
 */
@Validated
@RestController
@RequestMapping("/auth/")
public class AuthController {

//	@Autowired
//	private RedisTokenStore redisTokenStore;

	@GetMapping("t1")
	public String t1(@Valid @RequestBody User user) {
		return "t-1";
	}

	@GetMapping("t2")
	public String t2(@Valid @RequestBody User user) {
		return "t-2";
	}

}
