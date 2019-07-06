package com.bittrade.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * redis：Key管理工具类 <br />
 * ClassName: RedisKeyUtil <br/>
 * date: 2019年4月22日 下午6:04:19 <br/>
 * 
 * @author zale
 * @version
 * @since JDK 1.8
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RedisKeyUtil {

	// usd对cny汇率key
	public static final String	USD_TO_CNY_RATE_KEY	= "USD_TO_CNY_RATE_KEY";

	public static final String	OKEX				= "OKEX_";

	// symbol最新价
	public static String getOkexSymbolLast(String symbol) {
		return OKEX + symbol + "_LAST_KEY";
	}

}