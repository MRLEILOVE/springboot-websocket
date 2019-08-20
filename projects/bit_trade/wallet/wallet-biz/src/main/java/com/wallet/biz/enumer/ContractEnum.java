package com.wallet.biz.enumer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 合约枚举
 * 
 * @author ourblue
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ContractEnum {

	// 合约类型
	public enum ContractType {

		CONTRACT_ORDER(1, "合约订单"), TOKEN_ORDER(2, "体验金订单");
		ContractType(Integer code, String value) {
			this.code = code;
			this.value = value;
		}

		@Getter
		private Integer	code;
		@Getter
		private String		value;
	}


	// 订单状态
	public enum OrderState {

		OPEN_ORDER(1, "建仓"), CLOSE_ORDER(2, "平仓"), BOMB_ORDER(3, "爆仓"), TOKEN_HIDE(10, "体验金订单隐藏") ;
		OrderState(Integer code, String value) {
			this.code = code;
			this.value = value;
		}

		@Getter
		private Integer	code;
		@Getter
		private String	value;

	}

	//白天推送最小间隔时间
	public final static Integer DAY_INTERVAL_TIME_MIN = 3;
	//白天推送最小间隔时间
	public final static Integer DAY_INTERVAL_TIME_MIN_TWO = 2;
	//白天推送最小间隔时间
	public final static Integer DAY_INTERVAL_TIME_MIN_ONE = 1;
	//白天推送最大间隔时间
	public final static Integer DAY_INTERVAL_TIME_MAX = 10;
	//晚上推送最小间隔时间
	public final static Integer NIGHT_INTERVAL_TIME_MIN = 10;
	//晚上推送最大间隔时间
	public final static Integer NIGHT_INTERVAL_TIME_MAX = 60;


	//生成最小下载数
	public final static Integer DOWNLOAD_NUMBER_MIN = 1;
	//生成最大下载数
	public final static Integer DOWNLOAD_NUMBER_MAX = 50;


	//生成最小注册数
	public final static Integer REGISTER_NUMBER_MIN = 1;
	//生成最大注册数
	public final static Integer REGISTER_NUMBER_MAX = 50;

	//生成最小注册数
	public final static Integer ENTRUST_NUMBER_MIN = 10000;
	//生成最大注册数
	public final static Integer ENTRUST_NUMBER_MAX = 89999;

}