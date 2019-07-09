package com.core.framework.DTO;

import com.core.common.constant.IConstant;

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
	 * 错误码
	 */
	private int code;
	
	/**
	 * 消息
	 */
	private String msg;
	
	/**
	 * 数据
	 */
	private T data;
	
	/**
	 * ok
	 * @param <T>
	 * @param data
	 * @return
	 */
	public static final <T> ReturnDTO<T> ok(T data) {
		ReturnDTO<T> ret = new ReturnDTO<T>();
		
		ret.setCode(IConstant.SUCCESS);
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
		
		ret.setCode(IConstant.FAILURE);
		ret.setMsg(msg);
		
		return ret;
	}
	
}
