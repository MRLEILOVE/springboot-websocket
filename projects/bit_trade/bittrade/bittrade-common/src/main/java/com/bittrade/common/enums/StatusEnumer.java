package com.bittrade.common.enums;

import com.core.common.enums.IBaseEnumer;

/**
 * 状态：0禁用，1启用
 * @author Administrator
 * @datetime Jul 5, 2019 10:08:12 AM
 *
 */
public enum StatusEnumer implements IBaseEnumer<Byte> {
	
	/**
	 * 禁用
	 */
	DISABLE((byte) 0, "禁用"), 
	
	/**
	 * 启用
	 */
	ENABLE((byte) 1, "启用"), 
	;
	
	private byte code;
	private String name;
	
	StatusEnumer(byte code, String name) {
		this.code = code;
		this.name = name;
	}

	public Byte getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

}
