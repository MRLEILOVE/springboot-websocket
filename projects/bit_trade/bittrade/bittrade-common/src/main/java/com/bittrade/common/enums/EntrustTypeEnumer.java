package com.bittrade.common.enums;

import com.core.common.enums.base.IBaseEnumer;

/**
 * 委托类型:0市价交易,1限价交易
 * 
 * @author Administrator
 * @datetime Jul 5, 2019 10:08:12 AM
 *
 */
public enum EntrustTypeEnumer implements IBaseEnumer<Integer> {

	/**
	 * 市价交易
	 */
	MARKET(0, "市价交易"),

	/**
	 * 限价交易
	 */
	LIMIT(1, "限价交易"),;

	private int		code;
	private String	name;

	EntrustTypeEnumer(int code, String name) {
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

	public static IBaseEnumer<Integer> getEnumer(Integer code) {
		IBaseEnumer<Integer> enumer = null;

		for (EntrustTypeEnumer entrustTypeEnumer : EntrustTypeEnumer.values()) {
			if (entrustTypeEnumer.getCode() == code) {
				enumer = entrustTypeEnumer;

				break;
			}
		}

		return enumer;
	}

}
