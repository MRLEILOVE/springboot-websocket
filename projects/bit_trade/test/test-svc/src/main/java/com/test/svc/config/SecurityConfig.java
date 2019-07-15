package com.test.svc.config;
//package com.test.bittrade.svc.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启方法权限控制
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
////	/**
////	 * 自定义UserDetailsService，授权
////	 * 
////	 * @return
////	 */
////	@Bean
////	public UserDetailsService userDetailsService() {
////		return new CustomUserDetailsService();
////	}
////
////	/**
////	 * 设置用户密码的加密方式
////	 * 
////	 * @return
////	 */
////	@Bean
////	public Md5PasswordEncoder passwordEncoder() {
////		return new Md5PasswordEncoder();
////
////	}
////
////	@Autowired
////	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
////	}
////
////	/**
////	 * AuthenticationManager
////	 * 
////	 * @return
////	 * @throws Exception
////	 */
////	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
////	@Override
////	public AuthenticationManager authenticationManagerBean() throws Exception {
////		return super.authenticationManagerBean();
////	}
////
////	@Override
////	protected void configure(HttpSecurity http) throws Exception {
////		http.authorizeRequests()
////				// 所有用户均可访问的资源
////				.antMatchers("/favicon.ico", "/css/**", "/common/**", "/js/**", "/images/**", "/captcha.jpg", "/login",
////						"/userLogin", "/login-error")
////				.permitAll()
////				// 任何尚未匹配的URL只需要验证用户即可访问
////				.anyRequest().authenticated().and().formLogin().loginPage("/login").successForwardUrl("/index")
////				.failureForwardUrl("/login?error=1").and()
////				// 权限拒绝的页面
////				.exceptionHandling().accessDeniedPage("/403");
////
////		http.logout().logoutSuccessUrl("/login");
////	}
//
//	
//	@Bean
//	@Override
//	public UserDetailsService userDetailsService() {
//		UserDetails user = User.withDefaultPasswordEncoder().username("o1").password("o2").roles("USER").build();
//		return new InMemoryUserDetailsManager(user);
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/", "/home").permitAll().anyRequest().authenticated().and().formLogin()
//				.loginPage("/login").permitAll().and().logout().permitAll();
//	}
//
//	
////	@Override
////	protected void configure(HttpSecurity http) throws Exception {
////		// super.configure(http);
////		http.formLogin().loginPage("/login2").loginProcessingUrl("/login/form").failureUrl("/login-error").permitAll() // 表单登录，permitAll()表示这个不需要验证
////				// 登录页面，登录失败页面
////				.and().authorizeRequests().anyRequest().authenticated().and().csrf().disable();
////	}
//
//	
////	@Autowired(required = false)
////	private MyDisableUrlSessionFilter myDisableUrlSessionFilter;
////	@Override
////	protected void configure(HttpSecurity http) throws Exception {
////		// 用于客户端第一次访问时去掉URL中的jsessionid
////		http.addFilterBefore(myDisableUrlSessionFilter, UsernamePasswordAuthenticationFilter.class);
////		http.authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated().and().csrf().disable();
////		http.headers().frameOptions().sameOrigin();
////	}
//
//}
