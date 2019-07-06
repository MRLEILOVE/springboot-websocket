package com.core.framework.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公共返回信息。
 * @author Administrator
 * @datetime Jul 4, 2019 3:29:50 PM
 *
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnDTO<T> {

	/**
	 * 成功
	 */
	private static final int SUCCESS = 0;

	/**
	 * 失败
	 */
	private static final int FAILURE = -1;

	private int code;
	private String msg;
	private T data;
	
	/**
	 * ok
	 * @param <T>
	 * @param data
	 * @return
	 */
	public static final <T> ReturnDTO<T> ok(T data) {
		ReturnDTO<T> ret = new ReturnDTO<T>();
		
		ret.setCode(SUCCESS);
		ret.setData(data);
		
		return ret;
	}

	/**
	 * error
	 * @param <T>
	 * @param msg
	 * @return
	 */
	public static final <T> ReturnDTO<T> error(String msg) {
		ReturnDTO<T> ret = new ReturnDTO<T>();
		
		ret.setCode(FAILURE);
		ret.setMsg(msg);
		
		return ret;
	}
	
}
