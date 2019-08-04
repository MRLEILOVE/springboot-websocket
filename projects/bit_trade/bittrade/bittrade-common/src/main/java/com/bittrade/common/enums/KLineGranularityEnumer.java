package com.bittrade.common.enums;

import com.core.common.enums.base.IBaseEnumer;

/**
 * K线图 - 时间粒度，以秒为单位
 * @author Administrator
 * @datetime Jul 5, 2019 10:08:12 AM
 *
 */
public enum KLineGranularityEnumer implements IBaseEnumer<Integer> {
	
	/**
	 * 一分钟
	 */
	ONE_MINUTE(60, "一分钟"), 
	
	/**
	 * 三分钟
	 */
	THREE_MINUTE(180, "三分钟"), 
	
	/**
	 * 五分钟
	 */
	FIVE_MINUTE(300, "五分钟"), 
	
	/**
	 * 十五分钟
	 */
	FIFTEEN_MINUTE(900, "十五分钟"), 
	
	/**
	 * 三十分钟
	 */
	THIRTY_MINUTE(1800, "三十分钟"), 
	
	/**
	 * 一小时
	 */
	ONE_HOUR(3600, "一小时"), 
	
	/**
	 * 两小时
	 */
	TWO_MINUTE(7200, "两小时"), 
	
	/**
	 * 四小时
	 */
	FOUR_HOUR(14400, "四小时"), 
	
	/**
	 * 六小时
	 */
	SIX_HOUR(21600, "六小时"), 
	
	/**
	 * 十二小时
	 */
	TWELVE_HOUR(43200, "十二小时"), 
	
	/**
	 * 一天
	 */
	ONE_DAY(86400, "一天"), 
	
	/**
	 * 一周
	 */
	ONE_WEEK(604800, "一周"), 
	;
	
	private Integer code;
	private String name;
	
	KLineGranularityEnumer(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

}
