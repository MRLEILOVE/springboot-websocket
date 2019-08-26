package com.bittrade.admin.shiro.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.jdcloud.base.constant.GlobalConstant;
import com.jdcloud.base.constant.GlobalConstant.Sys;
import com.jdcloud.base.enums.UserEnum.UserState;
import com.jdcloud.core.expection.CaptchaException;
import com.jdcloud.core.expection.UserBlockedException;
import com.jdcloud.core.expection.UserDeleteException;
import com.jdcloud.core.expection.UserNotExistsException;
import com.jdcloud.core.expection.UserPasswordNotMatchException;
import com.jdcloud.core.utils.MessageUtils;
import com.jdcloud.provider.pojo.SysUser;
import com.jdcloud.provider.service.SysUserService;
import com.jdcloud.provider.shiro.constant.ShiroConstant;
import com.jdcloud.provider.utils.ShiroUtils;
import com.jdcloud.util.http.ServletUtils;
import eu.bitwalker.useragentutils.UserAgent;

/**
 * login
 * 
 * @author ourblue
 *
 */
@Component
public class LoginService {

	@Autowired
	private PasswordService	passwordService;

	@Autowired
	private SysUserService	sysUserService;

	@Autowired
	private TaskExecutor	taskExecutor;
	
	/**
	 * user 登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public SysUser login(String username, String password) {
		
		final UserAgent userAgent = UserAgent.parseUserAgentString( ServletUtil.getRequest().getHeader( "User-Agent" ) );
		final String ip = ShiroUtils.getIp();
		// 用户名或者密码为NULL
		if (StringUtils.isEmpty( username ) || StringUtils.isEmpty( password )) {
			taskExecutor.execute( () -> {AsyncFactory.recordLoginInfo( ip, userAgent,username, Sys.LOGIN_FAIL, MessageUtils.message( "not.null" ) );} );
			throw new UserNotExistsException();
		}
		// 验证码校验
		if (!StringUtils.isEmpty( ServletUtil.getRequest().getAttribute( ShiroConstant.CURRENT_CAPTCHA ) )) {
			taskExecutor.execute( () -> {AsyncFactory.recordLoginInfo( ip,userAgent, username, Sys.LOGIN_FAIL, MessageUtils.message( "user.jcaptcha.error" ) );} );
			throw new CaptchaException();
		}
		// 密码如果不在指定范围内
		if (password.length() < GlobalConstant.PASSWORD_MIN_LENGTH || password.length() > GlobalConstant.PASSWORD_MAX_LENGTH) {
			taskExecutor.execute( () -> {AsyncFactory.recordLoginInfo( ip,userAgent, username, Sys.LOGIN_FAIL, MessageUtils.message( "user.password.not.match" ) );} );
			throw new UserPasswordNotMatchException();
		}
		// 用户名不在指定范围
		if (username.length() < GlobalConstant.USERNAME_MIN_LENGTH || username.length() > GlobalConstant.USERNAME_MAX_LENGTH) {
			taskExecutor.execute( () -> {AsyncFactory.recordLoginInfo( ip,userAgent, username, Sys.LOGIN_FAIL, MessageUtils.message( "user.password.not.match" ) );} );
			throw new UserPasswordNotMatchException();
		}
		//开始校验
		SysUser sysUser = sysUserService.selectUserByLoginName( username );
		if (sysUser == null) {
			taskExecutor.execute( () -> {AsyncFactory.recordLoginInfo( ip, userAgent, username, Sys.LOGIN_FAIL, MessageUtils.message( "user.not.exists" ) );} );
			throw new UserNotExistsException();
		}
		if (UserState.LOCK_USER.getCode().toString().equals( sysUser.getDelFlag() ) ) {
			taskExecutor.execute( () -> {AsyncFactory.recordLoginInfo( ip, userAgent, username, Sys.LOGIN_FAIL, MessageUtils.message( "user.password.delete" ) );} );
			throw new UserDeleteException();
		}
		if (UserState.LOCK_USER.getCode().toString().equals( sysUser.getStatus() )) {
			taskExecutor.execute( () -> {AsyncFactory.recordLoginInfo( ip, userAgent, username, Sys.LOGIN_FAIL, MessageUtils.message( "user.blocked" ) );} );
			throw new UserBlockedException();
		}
		passwordService.validate( sysUser, password );
		taskExecutor.execute( () -> {AsyncFactory.recordLoginInfo( ip, userAgent, username, Sys.LOGIN_SUCCESS, MessageUtils.message( "user.login.success" ) );} );
		recordLoginInfo( sysUser );
		return sysUser;
	}

	/**
	 * 记录登录信息
	 */
	public void recordLoginInfo(SysUser user) {
		user.setLoginIp( ShiroUtils.getIp() );
		user.setLoginDate( new Date() );
		sysUserService.updateById( user );
	}

}