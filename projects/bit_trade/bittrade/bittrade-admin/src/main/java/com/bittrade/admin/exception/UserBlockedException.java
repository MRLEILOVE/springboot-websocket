package com.bittrade.admin.exception;

import com.bittrade.admin.util.MessageUtil;

/**
 * .用户锁定异常类
 * 
 * @author who ?
 *
 */
public class UserBlockedException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public UserBlockedException() {
		super( MessageUtil.message( "user.blocked" ) );
	}

}
