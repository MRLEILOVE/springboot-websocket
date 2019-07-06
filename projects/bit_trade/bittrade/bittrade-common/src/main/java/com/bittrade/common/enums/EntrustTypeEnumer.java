package com.bittrade.common.enums;

import com.core.common.enums.IBaseEnumer;

/**
 * 委托类型:0市价交易,1限价交易
 * @author Administrator
 * @datetime Jul 5, 2019 10:08:12 AM
 *
 */
public enum EntrustTypeEnumer implements IBaseEnumer {
	
	/**
	 * 市价交易
	 */
	MARKET(0, "市价交易"), 
	
	/**
	 * 限价交易
	 */
	LIMIT(1, "限价交易"), 
	;
	
	private int code;
	private String name;
	
	EntrustTypeEnumer(int code, String name) {
		this.code = code;
		this.name = name;
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getName() {
		return name;
	}

}
