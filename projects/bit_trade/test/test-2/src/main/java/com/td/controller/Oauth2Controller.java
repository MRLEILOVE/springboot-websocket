package com.td.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Oauth2Controller {

	@GetMapping("oauth/confirm_access")
	public String authorizeGet() {
		return "oauth/confirm_access";
	}
}
