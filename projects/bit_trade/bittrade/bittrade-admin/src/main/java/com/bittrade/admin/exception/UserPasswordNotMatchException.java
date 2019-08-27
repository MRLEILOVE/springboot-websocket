package com.bittrade.admin.exception;

import com.bittrade.admin.util.MessageUtil;

/**
 * user 用户密码不正确或不符合规范异常类
 * 
 * @author who ?
 *
 */
public class UserPasswordNotMatchException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public UserPasswordNotMatchException() {
		super( MessageUtil.message( "user.password.not.match" ) );
	}

}
