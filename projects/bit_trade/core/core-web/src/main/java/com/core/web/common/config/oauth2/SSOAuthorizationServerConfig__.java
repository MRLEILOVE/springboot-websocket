//package com.core.web.common.config.oauth2;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.token.TokenEnhancer;
//import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//
//@Configuration
//@EnableAuthorizationServer
//public class SSOAuthorizationServerConfig__ extends AuthorizationServerConfigurerAdapter {
//
//	public static final String		SIGNING_KEY	= "JWT_sign";
//	public static final String		CLIENT_ID_1	= "clientID1";
//	public static final String		SECRET_1	= "secret";
//	public static final String		CLIENT_ID_2	= "clientID2";
//	public static final String		SECRET_2	= "secret";
//
//	// @Autowired
//	// private javax.sql.DataSource dataSource;
//
//	// @Autowired
//	// private org.springframework.data.redis.connection.RedisConnectionFactory	redisConnectionFactory;
//
//	@Autowired
//	private UserDetailsService		userDetailsService;
//
//	@Autowired
//	private AuthenticationManager	authenticationManager;
//
//	@Bean
//	public JwtAccessTokenConverter accessTokenConverter() {
//		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//		converter.setSigningKey(SIGNING_KEY);
//		return converter;
//	}
//
//	@Bean
//	public TokenStore tokenStore() {
//		return new JwtTokenStore(accessTokenConverter());
////		return new RedisTokenStore( redisConnectionFactory );
//	}
//	
//	@Bean
//	public TokenEnhancer tokenEnhancer(){
//		return new TokenEnhancer() {
//			@Override
//			public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//				Map<String, Object> info = new HashMap<>();
//				info.put( "provider", "zz0-" );
//				// 设置附加信息
//				((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation( info );
//				return accessToken;
//			}
//		};
//	}
//
//	// @Bean
//	// public ClientDetailsService clientDetails() {
//	// return new JdbcClientDetailsService(dataSource);
//	// }
//
//	/**
//	 * <p>
//	 * 注意，自定义TokenServices的时候，需要设置@Primary，否则报错，
//	 * </p>
//	 * 
//	 * @return
//	 */
////	@Primary
////	@Bean
////	public DefaultTokenServices defaultTokenServices() {
////		DefaultTokenServices tokenServices = new DefaultTokenServices();
////		tokenServices.setTokenStore( tokenStore() );
////		tokenServices.setSupportRefreshToken( true );
////		// tokenServices.setClientDetailsService(clientDetails());
////		// token有效期自定义设置，默认12小时
////		tokenServices.setAccessTokenValiditySeconds( 60 * 60 * 12 );
////		// refresh_token默认30天
////		tokenServices.setRefreshTokenValiditySeconds( 60 * 60 * 24 * 7 );
////		return tokenServices;
////	}
//
//	/**
//	 * 
//	 */
//	@Override
//	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
////		super.configure(security);
//		security
//			.allowFormAuthenticationForClients() // "/oauth/token" or other ?
////			.tokenKeyAccess( "isAuthenticated()" ) // isAuthenticated permitAll()
////			.checkTokenAccess( "isAuthenticated()" )
//			;
//	}
//
//	/**
//	 * 客户端一些配置
//	 */
//	@Override
//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		clients.inMemory()
//				.withClient( CLIENT_ID_1 ).secret( SECRET_1 ).authorizedGrantTypes( "authorization_code", "password", "refresh_token" )
//					.scopes( "all" )//.accessTokenValiditySeconds( 60 * 60 * 12 ).refreshTokenValiditySeconds( 60 * 60 * 24 * 7 )
//					.and()
//				.withClient( CLIENT_ID_2 ).secret( SECRET_2 ).authorizedGrantTypes( "authorization_code" ).scopes( "all" )
//		;
////        clients.inMemory()
////        .withClient("android")
////        .secret("android")
////        .authorizedGrantTypes("password", "authorization_code", "refresh_token")
////        .scopes("read")
////        .and()
////        .withClient("webapp")
////        .authorizedGrantTypes("implicit")
////        .scopes("read")
////        .and()
////        .withClient("browser")
////        .authorizedGrantTypes("refresh_token", "password")
////        .scopes("read")
////        ;
//	}
//
//	/**
//	 * 配置JWTTokenStore
//	 */
//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		endpoints
//			.userDetailsService( userDetailsService )
//			.authenticationManager( authenticationManager )
//			.tokenStore( tokenStore() )
//			.accessTokenConverter( accessTokenConverter() )
//			;
////		endpoints.tokenServices( defaultTokenServices() );
//		
//		TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
//		List<TokenEnhancer> enhancerList = new ArrayList<>();
//		enhancerList.add(tokenEnhancer());
//		enhancerList.add(accessTokenConverter());
//		enhancerChain.setTokenEnhancers(enhancerList);
//		endpoints
//			.tokenEnhancer(enhancerChain)
//			;
//		
//		// 认证异常翻译
//		// endpoints.exceptionTranslator(webResponseExceptionTranslator());
//	}
//
//}
