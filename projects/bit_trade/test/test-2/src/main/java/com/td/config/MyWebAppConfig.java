//package com.td.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
///**
// * 这个会导致OAuth2的Token校验不对？
// */
//@Configuration
//public class MyWebAppConfig extends WebMvcConfigurationSupport {
//
//	@Bean
//	LoginInterceptor localInterceptor() {
//		return new LoginInterceptor();
//	}
//
////	@Override
////	public void addInterceptors(InterceptorRegistry registry) {
////		registry.addInterceptor( localInterceptor() )
////			.addPathPatterns( "/**" ).excludePathPatterns( "/user/login" )
////			.excludePathPatterns( "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**" );
////	}
//
//	@Override
//	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler( "/html/**" ).addResourceLocations( "classpath:/html/" );
//		registry.addResourceHandler( "swagger-ui.html" ).addResourceLocations( "classpath:/META-INF/resources/" );
//		registry.addResourceHandler( "/webjars/**" ).addResourceLocations( "classpath:/META-INF/resources/webjars/" );
//	}
//
//}
