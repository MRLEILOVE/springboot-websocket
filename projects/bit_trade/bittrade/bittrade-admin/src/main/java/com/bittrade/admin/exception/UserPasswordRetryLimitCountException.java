package com.bittrade.admin.exception;

import com.bittrade.admin.util.MessageUtil;

/**
 * user 用户错误最大次数异常类
 * 
 * @author who ?
 *
 */
public class UserPasswordRetryLimitCountException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public UserPasswordRetryLimitCountException() {
		super( MessageUtil.message( "user.password.retry.limit.exceed" ) );
	}

}
