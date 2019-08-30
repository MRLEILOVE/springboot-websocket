package com.bittrade.admin.shiro.service;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import com.bittrade.admin.exception.UserPasswordNotMatchException;
import com.bittrade.admin.exception.UserPasswordRetryLimitExceedException;
import com.bittrade.admin.util.MessageUtil;
import com.bittrade.admin.util.ServletUtil;
import com.bittrade.admin.util.ShiroUtil;
import com.bittrade.pojo.model.SysUser;
import com.core.common.constant.GlobalConstant.Sys;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * password
 * 
 * @author who ?
 */
@Component
public class PasswordService {

	@Autowired
	private CacheManager					cacheManager;

	private Cache<String, AtomicInteger>	loginRecordCache;

	@Value(value = "${user.password.maxRetryCount}")
	private String							maxRetryCount;

	@Autowired
	private TaskExecutor					taskExecutor;

	@PostConstruct
	public void init() {
		loginRecordCache = cacheManager.getCache( "loginRecordCache" );
	}

	/**
	 * .校验密码是否正确
	 * 
	 * @param user
	 * @param password
	 */
	public void validate(SysUser user, String password) {
		
		final UserAgent userAgent = UserAgent.parseUserAgentString( ServletUtil.getRequest().getHeader( "User-Agent" ) );
		final String ip = ShiroUtil.getIp();
		String loginName = user.getLoginName();
		// 如果没有配置错误次数,默认5次
		Integer maxCount = StringUtils.isEmpty( maxRetryCount ) ? 5 : Integer.valueOf( maxRetryCount );
		AtomicInteger retryCount = loginRecordCache.get( loginName );
		if (retryCount == null) {
			retryCount = new AtomicInteger( 0 );
			loginRecordCache.put( loginName, retryCount );
		}
		if (retryCount.incrementAndGet() > maxCount) {
			throw new UserPasswordRetryLimitExceedException();
		}
		if (!matches( user, password )) {
			taskExecutor.execute( () -> {AsyncFactory.recordLoginInfo( ip, userAgent, loginName, Sys.LOGIN_FAIL, MessageUtil.message( "user.password.retry.limit.exceed", maxRetryCount) );} );
			loginRecordCache.put( loginName, retryCount );
			throw new UserPasswordNotMatchException();
		} else {
			//clearLoginRecordCache( loginName );
		}

	}

	public boolean matches(SysUser user, String newPassword) {
		return user.getPassword().equals( encryptPassword( user.getLoginName(), newPassword, user.getSalt() ) );
	}

	public void clearLoginRecordCache(String username) {
		loginRecordCache.remove( username );
	}

	public String encryptPassword(String username, String password, String salt) {
		return new Md5Hash( username + password + salt ).toHex().toString();
	}

	public static void main(String[] args) {
		System.out.println( new PasswordService().encryptPassword( "admin", "admin123", "111111" ) );
		System.out.println( new PasswordService().encryptPassword( "ry", "admin123", "222222" ) );
	}

}