package com.bittrade.admin.exception;

import com.bittrade.admin.util.MessageUtil;

/**
 * user 用户错误记数异常类
 * 
 * @author ourblue
 *
 */
public class UserPasswordRetryLimitExceedException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public UserPasswordRetryLimitExceedException() {
		super( MessageUtil.message( "user.password.retry.limit.exceed" ) );
	}

}
