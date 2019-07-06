package com.bittrade.batch.enumer;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ParamConfigEnum {

	@AllArgsConstructor
	@Getter
	public enum ParamKeyEnum {

		OKEX_USD_TO_CNY_RATE_KEY("okexUsdToCnyRateKey", "获取okex法币汇率URL"), //
		OKEX_KLINE_HISTORY_SWITCH_KEY("okexKlineHistorySwitchKey", "获取K线历史数据开关"), //
		OKEX_KLINE_URL_KEY("okexKlineUrlKey", "okex-K线地址"), //
		OKEX_SYMBOL_KLINE_HISTORY_DATA_KEY("okexSymbolKlineHistoryDataKey", "拉取指定okex交易对历史数据"), //
		OKEX_GRANULARITYS_KEY("okexGranularitysKey", "公共-获取K线数据"),//
		;

		private String	key;	// 参数键
		private String	remark;	// 备注

	}

	@AllArgsConstructor
	@Getter
	public enum ParamStatus {

		ENABLE("1", "开启"), DISABLED("0", "禁用");

		private String	key;	//
		private String	remark;	//

	}

	@AllArgsConstructor
	@Getter
	public enum ParamValue {

		ON("on", "打开"), OFF("off", "关闭");

		private String	key;	//
		private String	remark;	//

	}

}
