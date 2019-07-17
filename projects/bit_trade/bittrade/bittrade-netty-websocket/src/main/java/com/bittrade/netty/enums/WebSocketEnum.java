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
		
		ETH_USDT_1M("ETH_USDT_1M", "ETH_USDT_1M"), //
		ETH_USDT_3M("ETH_USDT_3M", "ETH_USDT_3M"), //
		ETH_USDT_5M("ETH_USDT_5M", "ETH_USDT_5M"), //
		ETH_USDT_15M("ETH_USDT_15M", "ETH_USDT_15M"), //
		ETH_USDT_30M("ETH_USDT_30M", "ETH_USDT_30M"), //
		ETH_USDT_60M("ETH_USDT_60M", "ETH_USDT_60M"), //
		ETH_USDT_120M("ETH_USDT_120M", "ETH_USDT_120M"), //
		ETH_USDT_240M("ETH_USDT_240M", "ETH_USDT_240M"), //
		ETH_USDT_360M("ETH_USDT_360M", "ETH_USDT_360M"), //
		ETH_USDT_720M("ETH_USDT_720M", "ETH_USDT_720M"), //
		ETH_USDT_1400M("ETH_USDT_1400M", "ETH_USDT_1400M"),//
		
		LTC_USDT_1M("LTC_USDT_1M", "LTC_USDT_1M"), //
		LTC_USDT_3M("LTC_USDT_3M", "LTC_USDT_3M"), //
		LTC_USDT_5M("LTC_USDT_5M", "LTC_USDT_5M"), //
		LTC_USDT_15M("LTC_USDT_15M", "LTC_USDT_15M"), //
		LTC_USDT_30M("LTC_USDT_30M", "LTC_USDT_30M"), //
		LTC_USDT_60M("LTC_USDT_60M", "LTC_USDT_60M"), //
		LTC_USDT_120M("LTC_USDT_120M", "LTC_USDT_120M"), //
		LTC_USDT_240M("LTC_USDT_240M", "LTC_USDT_240M"), //
		LTC_USDT_360M("LTC_USDT_360M", "LTC_USDT_360M"), //
		LTC_USDT_720M("LTC_USDT_720M", "LTC_USDT_720M"), //
		LTC_USDT_1400M("LTC_USDT_1400M", "LTC_USDT_1400M"),//
		
		EOS_USDT_1M("EOS_USDT_1M", "EOS_USDT_1M"), //
		EOS_USDT_3M("EOS_USDT_3M", "EOS_USDT_3M"), //
		EOS_USDT_5M("EOS_USDT_5M", "EOS_USDT_5M"), //
		EOS_USDT_15M("EOS_USDT_15M", "EOS_USDT_15M"), //
		EOS_USDT_30M("EOS_USDT_30M", "EOS_USDT_30M"), //
		EOS_USDT_60M("EOS_USDT_60M", "EOS_USDT_60M"), //
		EOS_USDT_120M("EOS_USDT_120M", "EOS_USDT_120M"), //
		EOS_USDT_240M("EOS_USDT_240M", "EOS_USDT_240M"), //
		EOS_USDT_360M("EOS_USDT_360M", "EOS_USDT_360M"), //
		EOS_USDT_720M("EOS_USDT_720M", "EOS_USDT_720M"), //
		EOS_USDT_1400M("EOS_USDT_1400M", "EOS_USDT_1400M"),//
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
