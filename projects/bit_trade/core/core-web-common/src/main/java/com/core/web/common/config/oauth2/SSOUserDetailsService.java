package com.core.web.common.config.oauth2;
//package com.bittrade.currency.conf.oauth2;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SSOUserDetailsService implements UserDetailsService {
//
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		return new User(username, passwordEncoder.encode("o"),
//				AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_USER"));
//	}
//
//}