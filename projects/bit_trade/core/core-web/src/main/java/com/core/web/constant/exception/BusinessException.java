package com.core.web.constant.exception;

/**
 * 业务异常，其它具体业务异常可继承此异常
 * <br/>
 *
 * @author ：
 * @date ：
 */
public class BusinessException extends RuntimeException {

	/**  
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).  
	 * @since JDK 1.8  
	 */
	private static final long serialVersionUID = 1L;

	public BusinessException() {
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
}
