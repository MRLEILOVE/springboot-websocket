package com.walletbiz.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RedisKeyUtil {

	// 配置表redis_key前缀
	public static final String PARAMETER_CONFIG_PREF = "config:";

	// 字典数据缓存redis_key前缀
	public static final String DICT_DATA_CACHE_PREF = "dict:";


	// usd对cny汇率key
	public static final String USD_TO_CNY_RATE_KEY		= "usdt_cny";

	// btc对usdt汇率key
	public static final String BTC_TO_USDT_RATE_KEY	= "btc_usdt";

	//btc对人民币汇率key
	public static final String BTC_TO_CNY_RATE 			= "btc_cny";

	//BITT对人民币汇率key
	public static final String BITT_TO_CNY_RATE 		= "bi_cny";

	//行情价的redis前缀
	public static final String REDIS_PREFIX_LINE_PRICE = "line_price:";

	// usd对cny汇率key
	public static final String	OK_USD_TO_CNY_RATE_KEY		= "USD_TO_CNY_RATE_KEY";

}