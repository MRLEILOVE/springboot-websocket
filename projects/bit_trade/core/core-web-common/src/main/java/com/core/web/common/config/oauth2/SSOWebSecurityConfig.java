package com.core.web.common.config.oauth2;
//package com.bittrade.currency.conf.oauth2;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
////@org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
//public class SSOWebSecurityConfig extends WebSecurityConfigurerAdapter {
//	
//	@Autowired
//	private SSOUserDetailsService userDetailsService;
//	
//	@Bean
//    public PasswordEncoder passwordEncoder() {
//        //return new BCryptPasswordEncoder();
//        return new NoEncryptPasswordEncoder();
//    }
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
////			.addFilterAt(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)
//		
////			.formLogin().loginPage("/authentication/require").loginProcessingUrl("/authentication/form")
//////			.successHandler( successHandler ).failureHandler( authenticationFailureHandler )
////			.and()
//			
////			.logout().logoutUrl( "/logout" ).permitAll()
////			.and()
//			
////			.authorizeRequests().antMatchers(
////					"/authentication/require", "/authentication/form", 
////					"/**/*.js", "/**/*.css", "/**/*.jpg", "/**/*.png", "/**/*.woff2"
//////					, "/**"
////					).permitAll()
////			.anyRequest().authenticated().and()
////			.csrf().disable().cors().and()
//			
////			.requestMatchers().antMatchers("/oauth/**").and()
////			.authorizeRequests().antMatchers("/oauth/**").authenticated().and()
////			.csrf().disable()
//		
//			.authorizeRequests().anyRequest().authenticated()
//				.and()
//			.formLogin().and()
////			.httpBasic()
//			
//			;
////		http.formLogin().and().authorizeRequests().anyRequest().authenticated();
//	}
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//	}
//
//    /**
//     * 不定义没有password grant_type,密码模式需要AuthenticationManager支持
//     *
//     * @return
//     * @throws Exception
//     */
//    @Override
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//	
//}
