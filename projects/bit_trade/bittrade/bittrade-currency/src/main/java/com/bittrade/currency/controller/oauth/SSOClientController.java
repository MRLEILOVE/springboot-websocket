//package com.bittrade.currency.controller.oauth;
//
//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.oauth2.client.OAuth2ClientContext;
//import org.springframework.security.oauth2.client.OAuth2RestTemplate;
//import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@EnableOAuth2Sso
//public class SSOClientController {
//
//	@GetMapping("/user")
//	public Authentication user(Authentication user) {
//		return user;
//	}
//
//	@Bean
//	public OAuth2RestTemplate oAuth2RestTemplate(OAuth2ClientContext oAuth2ClientContext,
//			OAuth2ProtectedResourceDetails details) {
//		return new OAuth2RestTemplate(details, oAuth2ClientContext);
//	}
//
//}
