package com.wallet.biz.conf;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

//import com.jdcloud.base.constant.GlobalConstant;

@Configuration
@EnableResourceServer
//@Profile({GlobalConstant.SysEnvironmental.TEST_PROFILE,GlobalConstant.SysEnvironmental.PRO_PROFILE,GlobalConstant.SysEnvironmental.UAT_PROFILE})
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.headers().frameOptions().disable()
				.and()
				.csrf().disable()
				.exceptionHandling()
				.authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
				.and()
				.authorizeRequests().antMatchers("/auth/**","/druid/**", "/swagger-ui.html", "/swagger-resources/**", "/v2/api-docs", "/api/applications","/doc.html").permitAll()
				.anyRequest().authenticated();
	}

}
