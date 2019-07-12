package com.td.config;

import com.td.filter.MyDisableUrlSessionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 安全配置类
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired(required = false)
	private MyDisableUrlSessionFilter myDisableUrlSessionFilter;

	@Autowired
	private UserDetailsService customUserService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		super.configure(auth);
//        auth.inMemoryAuthentication().withUser("zhangsan").password("$2a$10$qsJ/Oy1RmUxFA.YtDT8RJ.Y2kU3U4z0jvd35YmiMOAPpD.nZUIRMC").roles("USER");
		auth.userDetailsService(customUserService).passwordEncoder(passwordEncoder);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);

//		// 用于客户端第一次访问时去掉URL中的jsessionid
//		http.addFilterBefore(myDisableUrlSessionFilter, UsernamePasswordAuthenticationFilter.class);
//
//		http.authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated().and().csrf().disable();
//
//		http.headers().frameOptions().sameOrigin();
	}

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		User.UserBuilder builder = User.builder();
		UserDetails user = builder.username("zhangsan")
				.password("$2a$10$GStfEJEyoSHiSxnoP3SbD.R8XRowP1QKOdi.N6/iFEwEJWTQqlSba").roles("USER").build();
		UserDetails admin = builder.username("lisi")
				.password("$2a$10$GStfEJEyoSHiSxnoP3SbD.R8XRowP1QKOdi.N6/iFEwEJWTQqlSba").roles("USER", "ADMIN")
				.build();
		return new InMemoryUserDetailsManager(user, admin);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		System.out.println(bCryptPasswordEncoder.encode("123456"));
		System.out.println(bCryptPasswordEncoder.encode("12345678"));
	}

}
