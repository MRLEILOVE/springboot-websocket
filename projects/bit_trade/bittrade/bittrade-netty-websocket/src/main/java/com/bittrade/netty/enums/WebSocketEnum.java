package com.bittrade.netty.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ClassName_KLeSubEnum <br/>
 * Description_ K线订阅枚举. <br/>
 * DateTime_ 2019年7月15日 下午6_13_04 <br />
 * 
 * @author Admistrator
 * @version
 * @sce JDK 1.8
 * @see
 */
public class WebSocketEnum {

	@AllArgsConstructor
	@Getter
	public enum KlineEnum {

		SUBSCRIBE("subscribe", "订阅"), UNSUBSCRIBE("unsubscribe", "取消订阅"), //

		BTC_USDT_1M("BTC_USDT_1M", "BTC_USDT_1M"), //
		BTC_USDT_3M("BTC_USDT_3M", "BTC_USDT_3M"), //
		BTC_USDT_5M("BTC_USDT_5M", "BTC_USDT_5M"), //
		BTC_USDT_15M("BTC_USDT_15M", "BTC_USDT_15M"), //
		BTC_USDT_30M("BTC_USDT_30M", "BTC_USDT_30M"), //
		BTC_USDT_60M("BTC_USDT_60M", "BTC_USDT_60M"), //
		BTC_USDT_120M("BTC_USDT_120M", "BTC_USDT_120M"), //
		BTC_USDT_240M("BTC_USDT_240M", "BTC_USDT_240M"), //
		BTC_USDT_360M("BTC_USDT_360M", "BTC_USDT_360M"), //
		BTC_USDT_720M("BTC_USDT_720M", "BTC_USDT_720M"), //
		BTC_USDT_1400M("BTC_USDT_1400M", "BTC_USDT_1400M"),//
		;

		private String	key;	// 参数键
		private String	value;	// 备注

		public static String parse(String key) {
			for (KlineEnum val : KlineEnum.values()) {
				if (val.key.equals( key )) {
					return val.getKey();
				}
			}
			return null;
		}

	}

}
