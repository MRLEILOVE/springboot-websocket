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

	public static final String	BIT_TRADE_			= "BIT_TRADE_";

	// BIT-TRADE-SYMBOL
	public static String getBitTradeSymbol() {
		return BIT_TRADE_ + "ALL_SYMBOL_KEY";
	}

	// OKEX-时间粒度[1min,5min,15min,30min,60min,4hour,1day,1mon,1week,1year]
	public static String getOkexGranularity() {
		return OKEX + "GRANULARITY";
	}

	// symbol最新价
	public static String getOkexSymbolLast(String symbol) {
		return OKEX + symbol + "_LAST_KEY";
	}

	public static final String OKEX_PING_KEY = "PING_KEY";

	public static String getOkexPingKey() {
		return OKEX + OKEX_PING_KEY;
	}
}