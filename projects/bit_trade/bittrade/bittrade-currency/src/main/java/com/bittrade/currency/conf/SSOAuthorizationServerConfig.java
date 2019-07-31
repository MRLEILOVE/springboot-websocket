package com.bittrade.currency.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class SSOAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	public static final String CLIENT_ID = "clientID";
	public static final String CLIENT_ID_1 = "clientID";
	public static final String SECRET_1 = "secret";
	public static final String CLIENT_ID_2 = "clientID";
	public static final String SECRET_2 = "secret";

	@Bean
	public TokenStore _JWTTokenStore() {
		return new JwtTokenStore(_JWTAccessTokenConverter());
	}

	private JwtAccessTokenConverter _JWTAccessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(CLIENT_ID);
		return converter;
	}

	/**
	 * 
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("isAuthenticated");
	}

	/**
	 * 客户端一些配置
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient(CLIENT_ID_1).secret(SECRET_1)
			.authorizedGrantTypes("authorization_code", "refresh_token")
			.scopes("all")
			.and()
			.withClient(CLIENT_ID_2).secret(SECRET_2)
			.authorizedGrantTypes("authorization_code", "refresh_token")
			.scopes("all")
			;
	}

	/**
	 * 配置JWTTokenStore
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(_JWTTokenStore()).accessTokenConverter(_JWTAccessTokenConverter());
	}
	
}
