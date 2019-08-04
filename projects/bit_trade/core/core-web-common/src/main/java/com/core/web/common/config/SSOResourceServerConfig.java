package com.core.web.common.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.core.web.common.config.BootAccessDeniedHandler;

/**
 * 〈资源认证服务器〉
 *
 * @author 
 * @create 
 * @since 1.0.0
 */
@Configuration
@EnableResourceServer
@Order(3)
//@org.springframework.context.annotation.Profile({"test","prod","uat"})
public class SSOResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Autowired
	private AuthenticationEntryPoint point;
	
	@Autowired
	private BootAccessDeniedHandler accessDeniedHandler;
	
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.authenticationEntryPoint(point).accessDeniedHandler(accessDeniedHandler);
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.exceptionHandling().authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED)).and()
//			.requestMatchers().antMatchers( "/**" ).and()
//			.authorizeRequests().antMatchers("/**/gets").permitAll().and()
			.authorizeRequests().antMatchers("/**").authenticated().and()
//			.httpBasic()
			;
//		http
//		.headers().frameOptions().disable()
//		.and()
//		.csrf().disable()
//		.exceptionHandling()
//		.authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//		.and()
//		.authorizeRequests().antMatchers("/auth/**","/druid/**", "/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs", "/api/applications","/doc.html").permitAll()
//		.anyRequest()
//			.access("#oauth2.hasAnyScope('all','select')")
//			.authenticated()
//		;
	}
	
}
