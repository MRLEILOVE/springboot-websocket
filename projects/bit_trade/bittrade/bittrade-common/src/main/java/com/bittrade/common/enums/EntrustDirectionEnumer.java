package com.bittrade.common.enums;

import com.core.common.enums.IBaseEnumer;

/**
 * 委托方向:0买入,1卖出
 * @author Administrator
 * @datetime Jul 5, 2019 10:08:35 AM
 *
 */
public enum EntrustDirectionEnumer implements IBaseEnumer {
	
	/**
	 * 买入
	 */
	BUY(0, "买入"), 
	
	/**
	 * 卖出
	 */
	SELL(1, "卖出"), 
	;
	
	private int code;
	private String name;
	
	EntrustDirectionEnumer(int code, String name) {
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
