package com.bittrade.admin.config;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import com.bittrade.admin.shiro.realm.UserRealm;
import com.bittrade.admin.shiro.session.OnlineSessionDAO;
import com.bittrade.admin.shiro.session.OnlineSessionFactory;
import com.bittrade.admin.shiro.web.filter.CaptchaValidateFilter;
import com.bittrade.admin.shiro.web.filter.LogoutFilter;
import com.bittrade.admin.shiro.web.filter.OnlineSessionFilter;
import com.bittrade.admin.shiro.web.filter.SyncOnlineSessionFilter;
import com.bittrade.admin.shiro.web.session.OnlineWebSessionManager;
import com.bittrade.admin.shiro.web.session.SpringSessionValidationScheduler;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import net.sf.ehcache.CacheManager;

/**
 * shiro 配置
 * 
 * @author who ?
 *
 */
@Configuration
public class ShiroConfig {

	public static final String	PREMISSION_STRING	= "perms[\"{0}\"]";

	// Session超时时间，单位为毫秒（默认30分钟）
	@Value("${shiro.session.expireTime}")
	private int					expireTime;

	// 相隔多久检查一次session的有效性，单位毫秒，默认就是10分钟
	@Value("${shiro.session.validationInterval}")
	private int					validationInterval;

	// 验证码开关
	@Value("${shiro.user.captchaEnabled}")
	private boolean				captchaEnabled;

	// 验证码类型
	@Value("${shiro.user.captchaType}")
	private String				captchaType;

	// 设置Cookie的域名
	@Value("${shiro.cookie.domain}")
	private String				domain;

	// 设置cookie的有效访问路径
	@Value("${shiro.cookie.path}")
	private String				path;

	// 设置HttpOnly属性
	@Value("${shiro.cookie.HttpOnlyer}")
	private boolean				HttpOnlyer;

	// 设置Cookie的过期时间，秒为单位
	@Value("${shiro.cookie.maxAge}")
	private int					maxAge;

	// 登录地址
	@Value("${shiro.user.loginUrl}")
	private String				loginUrl;

	// 权限认证失败地址
	@Value("${shiro.user.unauthorizedUrl}")
	private String				unauthorizedUrl;

	/**
	 * cache默认使用Ehcache
	 * 
	 * @return
	 */
	@Bean
	public EhCacheManager getEhCacheManager() {
		CacheManager cacheManager = CacheManager.getCacheManager( "ruoyi" );
		EhCacheManager em = new EhCacheManager();
		if (null == cacheManager) {

			em.setCacheManagerConfigFile( ResourceUtils.CLASSPATH_URL_PREFIX + "ehcache/ehcache-shiro.xml" );
		} else {
			em.setCacheManager( cacheManager );
		}
		return em;
	}

	/**
	 * relem自定义
	 * 
	 * @param cacheManager
	 * @return
	 */
	@Bean
	public UserRealm userRealm(EhCacheManager cacheManager) {
		UserRealm userRealm = new UserRealm();
		userRealm.setCacheManager( cacheManager );
		return userRealm;
	}

	/**
	 * shiro核心过滤器
	 * 
	 * @param securityManager
	 * @return
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean sb = new ShiroFilterFactoryBean();
		sb.setSecurityManager( securityManager );
		sb.setLoginUrl( loginUrl );
		sb.setUnauthorizedUrl( unauthorizedUrl );
		// 过滤链
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		// 不做控制
		filterChainDefinitionMap.put( "/favicon.ico**", "anon" );
		filterChainDefinitionMap.put( "/css/**", "anon" );
		filterChainDefinitionMap.put( "/docs/**", "anon" );
		filterChainDefinitionMap.put( "/fonts/**", "anon" );
		filterChainDefinitionMap.put( "/img/**", "anon" );
		filterChainDefinitionMap.put( "/ajax/**", "anon" );
		filterChainDefinitionMap.put( "/js/**", "anon" );
		filterChainDefinitionMap.put( "/ruoyi/**", "anon" );
		filterChainDefinitionMap.put( "/druid/**", "anon" );
		filterChainDefinitionMap.put( "/captcha/captchaImage**", "anon" );
		// 退出 logout地址，shiro去清除session
		filterChainDefinitionMap.put( "/logout", "logout" );
		filterChainDefinitionMap.put( "/login", "anon,captchaValidate" );
		// 拦截链
		Map<String, Filter> filters = new LinkedHashMap<>();
		filters.put( "captchaValidate", captchaValidateFilter() );
		filters.put( "onlineSession", onlineSessionFilter() );
		filters.put( "syncOnlineSession", syncOnlineSessionFilter() );
		filters.put( "logout", logoutFilter() );
		sb.setFilters( filters );
		// 所有请求需要认证
		filterChainDefinitionMap.put("/**", "user,onlineSession,syncOnlineSession");
		sb.setFilterChainDefinitionMap( filterChainDefinitionMap );
		return sb;
	}

	/**
	 * security安全管理器
	 * 
	 * @param userRealm
	 * @return
	 */
	@Bean
	public SecurityManager securityManager(UserRealm userRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm( userRealm );
		securityManager.setRememberMeManager( rememberMeManager() );
		securityManager.setCacheManager( getEhCacheManager() );
		securityManager.setSessionManager( sessionManager() );
		return securityManager;
	}

	/**
	 * 开启Shiro注解通知器
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager( securityManager );
		return authorizationAttributeSourceAdvisor;
	}

	/**
	 * thymeleaf模板引擎和shiro框架的整合
	 * 
	 * @return
	 */
	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}

	/**
	 * 会话管理器
	 */
	@Bean
	public OnlineWebSessionManager sessionManager() {
		OnlineWebSessionManager manager = new OnlineWebSessionManager();
		// 加入缓存管理器
		manager.setCacheManager( getEhCacheManager() );
		// 删除过期的session
		manager.setDeleteInvalidSessions( true );
		// 设置全局session超时时间
		manager.setGlobalSessionTimeout( expireTime * 60 * 1000 );
		// 去掉 JSESSIONID
		manager.setSessionIdUrlRewritingEnabled( false );
		// 定义要使用的无效的Session定时调度器
		manager.setSessionValidationScheduler( sessionValidationScheduler() );
		// 是否定时检查session
		manager.setSessionValidationSchedulerEnabled( true );
		// 自定义SessionDao
		manager.setSessionDAO( sessionDAO() );
		// 自定义sessionFactory
		manager.setSessionFactory( sessionFactory() );
		return manager;
	}

	/**
	 * 自定义sessionDAO会话
	 */
	@Bean
	public OnlineSessionDAO sessionDAO() {
		OnlineSessionDAO sessionDAO = new OnlineSessionDAO();
		return sessionDAO;
	}

	/**
	 * 自定义sessionFactory会话
	 */
	@Bean
	public OnlineSessionFactory sessionFactory() {
		OnlineSessionFactory sessionFactory = new OnlineSessionFactory();
		return sessionFactory;
	}

	/**
	 * 自定义sessionFactory调度器
	 */
	@Bean
	public SpringSessionValidationScheduler sessionValidationScheduler() {
		SpringSessionValidationScheduler sessionValidationScheduler = new SpringSessionValidationScheduler();
		// 相隔多久检查一次session的有效性，单位毫秒，默认就是10分钟
		sessionValidationScheduler.setSessionValidationInterval( validationInterval * 60 * 1000 );
		// 设置会话验证调度器进行会话验证时的会话管理器
		sessionValidationScheduler.setSessionManager( sessionValidationManager() );
		return sessionValidationScheduler;
	}

	/**
	 * 会话管理器
	 */
	@Bean
	public OnlineWebSessionManager sessionValidationManager() {
		OnlineWebSessionManager manager = new OnlineWebSessionManager();
		// 加入缓存管理器
		manager.setCacheManager( getEhCacheManager() );
		// 删除过期的session
		manager.setDeleteInvalidSessions( true );
		// 设置全局session超时时间
		manager.setGlobalSessionTimeout( expireTime * 60 * 1000 );
		// 去掉 JSESSIONID
		manager.setSessionIdUrlRewritingEnabled( false );
		// 是否定时检查session
		manager.setSessionValidationSchedulerEnabled( true );
		// 自定义SessionDao
		manager.setSessionDAO( sessionDAO() );
		// 自定义sessionFactory
		manager.setSessionFactory( sessionFactory() );
		return manager;
	}

	/**
	 * 自定义在线用户处理过滤器
	 */
	@Bean
	public OnlineSessionFilter onlineSessionFilter() {
		OnlineSessionFilter onlineSessionFilter = new OnlineSessionFilter();
		onlineSessionFilter.setLoginUrl( loginUrl );
		return onlineSessionFilter;
	}

	/**
	 * 自定义在线用户同步过滤器
	 */
	@Bean
	public SyncOnlineSessionFilter syncOnlineSessionFilter() {
		SyncOnlineSessionFilter syncOnlineSessionFilter = new SyncOnlineSessionFilter();
		return syncOnlineSessionFilter;
	}

	/**
	 * 自定义验证码过滤器
	 * 
	 * @return
	 */
	@Bean
	public CaptchaValidateFilter captchaValidateFilter() {
		CaptchaValidateFilter captchaValidateFilter = new CaptchaValidateFilter();
		captchaValidateFilter.setCaptchaEnabled( captchaEnabled );
		captchaValidateFilter.setCaptchaType( captchaType );
		return captchaValidateFilter;
	}

	/**
	 * .退出过滤器
	 * 
	 * @return
	 */
	public LogoutFilter logoutFilter() {
		LogoutFilter logoutFilter = new LogoutFilter();
		logoutFilter.setLoginUrl( loginUrl );
		return logoutFilter;
	}

	/**
	 * .记住我
	 * 
	 * @return
	 */
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie( rememberMeCookie() );
		cookieRememberMeManager.setCipherKey( Base64.decode( "fCq+/xW488hMTCD+cmJ3aQ==" ) );
		return cookieRememberMeManager;
	}

	/**
	 * cookie 属性设置
	 * 
	 * @return
	 */
	public SimpleCookie rememberMeCookie() {
		SimpleCookie cookie = new SimpleCookie( "rememberMe" );
		cookie.setDomain( domain );
		cookie.setPath( path );
		cookie.setHttpOnly( HttpOnlyer );
		cookie.setMaxAge( maxAge * 24 * 60 * 60 );
		return cookie;
	}

}
