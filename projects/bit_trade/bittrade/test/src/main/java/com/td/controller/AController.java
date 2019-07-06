package com.td.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AController {
	@RequestMapping({ "/", "/index" })
	public String index(HttpServletRequest request) {
		return "index";
	}

	@GetMapping({ "/login" })
	public String login() {
		return "login";
	}

}
