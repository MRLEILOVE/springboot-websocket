package com.bittrade.common.enums;

import com.core.common.enums.IBaseEnumer;

/**
 * K线图 - 时间粒度，以秒为单位
 * @author Administrator
 * @datetime Jul 5, 2019 10:08:12 AM
 *
 */
public enum KLineGranularityEnumer implements IBaseEnumer<Byte> {
	
	/**
	 * 一分钟
	 */
	ONE_MINUTE((byte) 60, "一分钟"), 
	
	/**
	 * 三分钟
	 */
	THREE_MINUTE((byte) 180, "三分钟"), 
	
	/**
	 * 五分钟
	 */
	FIVE_MINUTE((byte) 300, "五分钟"), 
	
	/**
	 * 十五分钟
	 */
	FIFTEEN_MINUTE((byte) 900, "十五分钟"), 
	
	/**
	 * 三十分钟
	 */
	THIRTY_MINUTE((byte) 1800, "三十分钟"), 
	
	/**
	 * 一小时
	 */
	ONE_HOUR((byte) 3600, "一小时"), 
	
	/**
	 * 两小时
	 */
	TWO_MINUTE((byte) 7200, "两小时"), 
	
	/**
	 * 四小时
	 */
	FOUR_HOUR((byte) 14400, "四小时"), 
	
	/**
	 * 六小时
	 */
	SIX_HOUR((byte) 21600, "六小时"), 
	
	/**
	 * 十二小时
	 */
	TWELVE_HOUR((byte) 43200, "十二小时"), 
	
	/**
	 * 一天
	 */
	ONE_DAY((byte) 86400, "一天"), 
	
	/**
	 * 一周
	 */
	ONE_WEEK((byte) 604800, "一周"), 
	;
	
	private byte code;
	private String name;
	
	KLineGranularityEnumer(byte code, String name) {
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
