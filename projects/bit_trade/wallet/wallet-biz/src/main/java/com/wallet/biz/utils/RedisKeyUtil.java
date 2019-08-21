package com.wallet.biz.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RedisKeyUtil {
	//行情价的redis前缀
	public static final String REDIS_PREFIX_LINE_PRICE = "line_price:";

	// usd对cny汇率key
	public static final String	USD_TO_CNY_RATE_KEY		= "USD_TO_CNY_RATE_KEY";

}