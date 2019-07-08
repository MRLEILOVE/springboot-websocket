package com.bittrade.common.enums;

import com.core.common.enums.IBaseEnumer;

/**
 * 是否主动:1-主买主卖，0-被买被卖
 * @author Administrator
 * @datetime Jul 5, 2019 10:08:12 AM
 *
 */
public enum IsActiveEnumer implements IBaseEnumer<Byte> {
	
	/**
	 * 被买被卖
	 */
	UNACTIVE((byte) 0, "被买被卖"), 
	
	/**
	 * 主买主卖
	 */
	ACTIVE((byte) 1, "主买主卖"), 
	;
	
	private byte code;
	private String name;
	
	IsActiveEnumer(byte code, String name) {
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
