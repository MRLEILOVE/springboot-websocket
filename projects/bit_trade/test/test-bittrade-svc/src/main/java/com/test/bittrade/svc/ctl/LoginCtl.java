package com.test.bittrade.svc.ctl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping(value = "")
public class LoginCtl {

	@RequestMapping("/login2")
	public String userLogin() {
//		return "demo_sign";
		return "index2";
	}

	@RequestMapping("/login-error")
	public String loginError() {
		return "login-error";
	}

}
