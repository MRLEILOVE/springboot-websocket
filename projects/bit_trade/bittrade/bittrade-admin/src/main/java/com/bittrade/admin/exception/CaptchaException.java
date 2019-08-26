package com.bittrade.admin.exception;

import com.bittrade.admin.util.MessageUtil;

/**
 * .验证码错误异常
 * 
 * @author ourblue
 *
 */
public class CaptchaException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public CaptchaException() {
		super( MessageUtil.message( "user.jcaptcha.error" ) );
	}

}
