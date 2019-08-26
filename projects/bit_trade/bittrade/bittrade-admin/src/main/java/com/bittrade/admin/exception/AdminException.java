package com.bittrade.admin.exception;

import com.bittrade.admin.enums.ErrorCodeEnum;
import com.bittrade.admin.wrapper.Wrapper;

import lombok.extern.slf4j.Slf4j;

/**
 * The class Biz biz exception.
 *
 */
@Slf4j
public class AdminException extends BusinessException {

	private static final long serialVersionUID = -6552248511084911254L;

	/**
	 * Instantiates a new Biz rpc exception.
	 */
	public AdminException() {
	}

	/**
	 * Instantiates a new Biz rpc exception.
	 *
	 * @param code the code
	 * @param msg  the msg
	 */
	public AdminException(String msg) {
		super(Wrapper.DISPLAY_CODE, msg);
		log.info("<== AdminException, code:" + Wrapper.DISPLAY_CODE + ", message:" + super.getMessage());
	}

	/**
	 * Instantiates a new Biz rpc exception.
	 *
	 * @param code      the code
	 * @param msgFormat the msg format
	 * @param args      the args
	 */
	public AdminException(int code, String msgFormat, Object... args) {
		super(code, msgFormat, args);
		log.info("<== AdminException, code:" + this.code + ", message:" + super.getMessage());

	}

	/**
	 * Instantiates a new Biz rpc exception.
	 *
	 * @param code the code
	 * @param msg  the msg
	 */
	public AdminException(int code, String msg) {
		super(code, msg);
		log.info("<== AdminException, code:" + this.code + ", message:" + super.getMessage());
	}

	/**
	 * Instantiates a new Biz rpc exception.
	 *
	 * @param codeEnum the code enum
	 */
	public AdminException(ErrorCodeEnum codeEnum) {
		super(codeEnum.code(), codeEnum.msg());
		log.info("<== AdminException, code:" + this.code + ", message:" + super.getMessage());
	}

	/**
	 * Instantiates a new Biz rpc exception.
	 *
	 * @param codeEnum the code enum
	 * @param args     the args
	 */
	public AdminException(ErrorCodeEnum codeEnum, Object... args) {
		super(codeEnum, args);
		log.info("<== AdminException, code:" + this.code + ", message:" + super.getMessage());
	}
}