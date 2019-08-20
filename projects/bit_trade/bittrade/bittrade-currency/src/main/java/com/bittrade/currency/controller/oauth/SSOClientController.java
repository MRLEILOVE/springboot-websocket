package com.bittrade.currency.controller.oauth;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.core.common.annotation.ALoginUser;
import com.core.web.constant.entity.LoginUser;

@RestController
// @EnableOAuth2Sso
public class SSOClientController {

	@GetMapping("/principal")
	public Principal Principal(Principal user) {
		return user;
	}

	@GetMapping("/user")
	public Authentication user(Authentication user) {
		return user;
	}

	@GetMapping("/getLoginUser")
	public Object getUser(@ALoginUser LoginUser obj) {
		return obj;
	}

	@Autowired
	private ConsumerTokenServices consumerTokenServices;

	@DeleteMapping(value = "/exit")
	public String revokeToken(String access_token) {
		if (consumerTokenServices.revokeToken( access_token )) {
			return "注销成功";
		} else {
			return "注销失败";
		}
	}

//	@org.springframework.context.annotation.Bean
//	public org.springframework.security.oauth2.client.OAuth2RestTemplate oAuth2RestTemplate(
//		org.springframework.security.oauth2.client.OAuth2ClientContext oAuth2ClientContext, 
//		org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails details) {
//		return new org.springframework.security.oauth2.client.OAuth2RestTemplate( details, oAuth2ClientContext );
//	}

}
