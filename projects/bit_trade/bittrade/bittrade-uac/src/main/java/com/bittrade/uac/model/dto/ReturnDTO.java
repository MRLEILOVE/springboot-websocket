package com.bittrade.uac.model.dto;

import com.bittrade.uac.model.IConstant;
import com.bittrade.uac.model.enums.IBaseEnumer;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 公共返回信息。
 * @author Administrator
 * @datetime Jul 4, 2019 3:29:50 PM
 *
 * @param <T>
 */
@Data
//@lombok.NoArgsConstructor
@AllArgsConstructor
public class ReturnDTO<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	 * 时间
	 */
	private LocalDateTime time;

	private ReturnDTO() {
		time = LocalDateTime.now();
	}

	/**
	 * ok
	 * @param <T>
	 * @return
	 */
	public static final <T> ReturnDTO<T> ok() {
		ReturnDTO<T> ret = new ReturnDTO<T>();
		
		ret.setCode(IConstant.SUCCESS);
		ret.setMsg(IConstant.SUCCESS_TXT);
		
		return ret;
	}

	/**
	 * ok
	 * @param <T>
	 * @param msg
	 * @return
	 */
	public static final <T> ReturnDTO<T> ok(String msg) {
		ReturnDTO<T> ret = new ReturnDTO<T>();
		
		ret.setCode(IConstant.SUCCESS);
		ret.setMsg(msg);
		
		return ret;
	}

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
	 * ret
	 * @param <T>
	 * @param baseEnumer
	 * @return
	 */
	public static final <T> ReturnDTO<T> ret(IBaseEnumer<Integer> baseEnumer) {
		ReturnDTO<T> ret = new ReturnDTO<T>();
		
		ret.setCode(baseEnumer.getCode());
		ret.setMsg(baseEnumer.getName());
		
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

	/**
	 * error
	 * @param <T>
	 * @param ex
	 * @return
	 */
	public static final <T> ReturnDTO<T> error(Exception ex) {
		ReturnDTO<T> ret = new ReturnDTO<T>();
		
		ret.setCode(IConstant.FAILURE);
		ret.setMsg(ex.toString());
		
		return ret;
	}

	/**
	 * error
	 * @param <T>
	 * @param code
	 * @param ex
	 * @return
	 */
	public static final <T> ReturnDTO<T> error(int code, Exception ex) {
		ReturnDTO<T> ret = new ReturnDTO<T>();
		
		ret.setCode(code);
		ret.setMsg(ex.toString());
		
		return ret;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
