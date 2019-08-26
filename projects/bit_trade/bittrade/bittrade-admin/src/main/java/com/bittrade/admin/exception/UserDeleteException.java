package com.bittrade.admin.exception;

import com.bittrade.admin.util.MessageUtil;

/**
 * .用户账号已被删除
 * 
 * @author ourblue
 *
 */
public class UserDeleteException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public UserDeleteException() {
		super( MessageUtil.message( "user.password.delete" ) );
	}

}
