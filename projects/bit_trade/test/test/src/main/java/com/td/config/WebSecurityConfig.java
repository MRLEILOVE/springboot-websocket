package com.td.config;

import com.td.filter.MyDisableUrlSessionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 安全配置类
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired(required=false)
    private MyDisableUrlSessionFilter myDisableUrlSessionFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //用于客户端第一次访问时去掉URL中的jsessionid
        http.addFilterBefore(myDisableUrlSessionFilter,UsernamePasswordAuthenticationFilter.class);

        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();

        http.headers().frameOptions().sameOrigin();
    }
}
