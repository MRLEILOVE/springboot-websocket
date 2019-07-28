package com.td.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.td.entity.CustomUserDetail;
import com.td.model.User;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				log.info("username:{}", username);
//				User user = userRepository.findUserByAccount(username);
				User user;
				if (true) {
					user = new User() {
						private static final long serialVersionUID = 1L;
						{}
					};
				}
				if (user != null) {
					CustomUserDetail customUserDetail = new CustomUserDetail();
					customUserDetail.setUsername(user.getAccount());
					customUserDetail.setPassword("{bcrypt}" + bCryptPasswordEncoder.encode(user.getPassword()));
//					List<GrantedAuthority> list = AuthorityUtils.createAuthorityList(user.getRole().getRole());
					List<GrantedAuthority> list = AuthorityUtils.createAuthorityList("ADMIN");
					customUserDetail.setAuthorities(list);
					return customUserDetail;
				} else {// 返回空
					return null;
				}

			}
		};
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
