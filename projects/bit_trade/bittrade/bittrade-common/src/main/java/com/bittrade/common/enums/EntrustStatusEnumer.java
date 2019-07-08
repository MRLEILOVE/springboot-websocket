package com.bittrade.common.enums;

import com.core.common.enums.IBaseEnumer;

/**
 * 状态:1未完成,2部分成交,3完全成交,4用户撤销
 * @author Administrator
 * @datetime Jul 5, 2019 10:08:12 AM
 *
 */
public enum EntrustStatusEnumer implements IBaseEnumer<Integer> {
	
	/**
	 * 未完成
	 */
	UNFINISH(1, "未完成"), 
	
	/**
	 * 部分成交
	 */
	PART_FINISH(2, "部分成交"), 
	
	/**
	 * 完全成交
	 */
	FINISH(3, "完全成交"), 
	
	/**
	 * 用户撤销
	 */
	CANCEL(4, "用户撤销"), 
	;
	
	private int code;
	private String name;
	
	EntrustStatusEnumer(int code, String name) {
		this.code = code;
		this.name = name;
	}

	@Override
	public Integer getCode() {
		return code;
	}

	@Override
	public String getName() {
		return name;
	}

}
