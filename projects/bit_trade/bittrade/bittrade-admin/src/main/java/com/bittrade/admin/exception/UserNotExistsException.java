package com.bittrade.admin.exception;

import com.bittrade.admin.util.MessageUtil;

/**
 * user 不存在异常
 * 
 * @author ourblue
 *
 */
public class UserNotExistsException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public UserNotExistsException() {
		super( MessageUtil.message( "user.not.exists" ) );
	}

}
