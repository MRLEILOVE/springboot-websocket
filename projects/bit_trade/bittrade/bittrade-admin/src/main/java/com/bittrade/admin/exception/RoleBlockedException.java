package com.bittrade.admin.exception;

import com.bittrade.admin.util.MessageUtil;

/**
 * .角色锁定异常
 * 
 * @author ourblue
 *
 */
public class RoleBlockedException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public RoleBlockedException() {
		super( MessageUtil.message( "role.blocked" ) );
	}

}
