package com.core.web.common.exception;

/**
 * 业务异常，其它具体业务异常可继承此异常
 * <br/>
 *
 * @author ：leigq
 * @date ：2019/8/19 15:36
 */
public class BusinessException extends RuntimeException {

	public BusinessException() {
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
}
