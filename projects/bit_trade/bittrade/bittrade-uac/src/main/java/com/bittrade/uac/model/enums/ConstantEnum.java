package com.bittrade.uac.model.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 常量枚举
 * 
 * @author ourblue
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConstantEnum {

	// 轮播图
	public enum BannerState {

		NORMAL_DOC(1, "正常"), DEL_DOC(0, "无效");
		BannerState(Integer code, String value) {
			this.code = code;
			this.value = value;
		}

		@Getter
		private Integer	code;
		@Getter
		private String	value;
	}

	//身份认证
	@Getter
	@AllArgsConstructor
	public enum AuthenticationConstant{
		IDENTITY_FRONT("identityFront", "身份证正面照"),
		IDENTITY_BACK("identityBack", "身份证反面照"),
		BANK_CARD("bankCard", "银行卡正面照"),

		FILE_UPLOAD_SUCCESS("1", "上传成功"),
		FILE_UPLOAD_FAIL("0", "上传失败"),

		UNSUBMITTED("0", "无效"),
		SUBMITTED("1", "提交"),

		NOT_AUDIT("0", "未审核"),
		AUDITING("2", "审核中"),
		AUDITED("1", "已审核"),
		REFUSE("3", "已拒绝");

		private String code;
		private String value;
	}

	//充值提现
	@Getter
    @AllArgsConstructor
	public enum AmountFlowsConstant{

	    RECHARGE("1", "充值"),
		WITHDRAW("2", "提现"),
		DELIVERY_MICRO("3", "微合约交割"),
		DELIVERY_PERPETUAL("4", "永续合约交割"),
		SERVICE_FEE("5", "手续费"),
		TOKEN_CLEARING("6", "体验金清零"),
        LABOUR_CHANGE_INTO("7", "奖励金转入"),

		;

	    private String code;
	    private String value;
    }

	//蓝鲸平台
	@Getter
	@AllArgsConstructor
	public enum LanjingConstant{
		RECEIVE_URL("lanjingReceiveUrl", "蓝鲸通知回调地址"),
		PICK_UP_URL("lanjingPickUpUrl", "交易完成后跳转URL"),
		EXCHANGE_RATE_URL("lanjingExchangeRateUrl", "交易完成后跳转URL"),

		SUCCESS("success", "成功"),
		FAIL("fail", "失败");

		private String code;
		private String value;
	}

	//充值
	@Getter
	@AllArgsConstructor
	public enum RechargeConstant{
		STATUS_AUDITING("1", "待审核"),
		STATUS_TRANSFERING("2", "待转账"),
		STATUS_AUDIT_PASS("3", "审核通过"),
		STATUS_NOT_PASS("4", "审核不通过"),
		STATUS_CLOSED("5", "已关闭"),

		TYPE_CUSTOMER("1", "用户充值"),
		TYPE_MANUAL("2", "人工充值"),
		TYPE_MANUAL_TOKEN("3", "人工充值（体验金）"),
		LABOUR_CHANGE_INTO("4", "奖励金转入"),

		MIN_RECHARGE_LIMIT("minRechargeLimit", "最小充值金额"),
		MAX_RECHARGE_LIMIT("maxRechargeLimit", "最大充值金额");

		private String code;
		private String value;
	}

	//提现
	@Getter
	@AllArgsConstructor
	public enum WithdrawConstant{
		AUDIT_NOT_PASS("0", "审核不通过"),
		AUDIT_PASS("1", "审核通过"),
		AUDITING("2", "审核中"),

		TYPE_CUSTOMER("1", "用户提现"),
		TYPE_MANUAL("2", "人工提现"),

		MIN_WITHDRAW_LIMIT("minWithdrawLimit", "最小提现金额"),
		MAX_WITHDRAW_LIMIT("maxWithdrawLimit", "最大提现金额");

		private String code;
		private String value;
	}

	// 事件类型
	public enum MessageType {

		USER_REGISTER(1, "用户注册"), CHANGE_LOGINPWD(2, "修改密码"),FIND_PASSWORD(3,"找回登录密码"),UPDATE_RECEIVING(4,"修改收付款二维码");

		private MessageType(Integer code, String value) {
			this.code = code;
			this.value = value;
		}
		@Getter
		private Integer	code;
		@Getter
		private String	value;
	}

	//汇率
	@Getter
	@AllArgsConstructor
	public enum ParamConfigConstant{
		EXCHANGE_RATE_ADJUST("exchangeRateAdjust", "汇率调整"),
		RISK_RATE("riskRate", "爆仓风险率");

		private String code;
		private String value;
	}

	//云存储路径
	@Getter
	@AllArgsConstructor
	public enum AliyunConstant{
		HOUSEPRODUCT_PATH("houseProduct/", "狗窝路径"),
		BANNER_PATH("banner/", "banner路径"),
		AUTHENTICATION_PATH("authentication/", "身份认证路径"),
		RECEIPT_PATH("receipt/", "收款信息"),
		POSTER_PATH("poster/", "收款信息"),
		VOUCHER_PATH("voucher/", "凭证"),
		BETAHEADER_PATH("beta/","beta狗"),
		RECHARGE_PATH("recharge/","充币钱包地址"),
		ACCOUNT_PATH("account/","钱包路径");
		private String code;
		private String value;
	}

	//订单类型
	@Getter
	@AllArgsConstructor
	public enum OrderType{
		COMMON_ORDER(1, "正常订单"),
		TOKEN_ORDER(2, "体验金订单");

		private Integer code;
		private String value;
	}

	//推送枚举类
	@Getter
	@AllArgsConstructor
	public enum PushConstant{
		DIRECTION_UP(1,"看涨"),
		DIRECTION_DOWN(2,"看跌"),

		TYPE_PROFIT(1,"盈利"),
		TYPE_RECHARGE(2,"充值"),
		TYPE_ORDER(3,"下单"),

		PRODUCT_TYPE_MICRO(1,"微合约"),
		PRODUCT_TYPE_PERPETUAL(2,"永续合约"),
		PRODUCT_TYPE_FICTITIOUS(3,"虚拟单"),

		STATUS_NOT_PUSH(0,"未推送"),
		STATUS_PUSHED(1,"已推送");

		private Integer code;
		private String value;
	}

	// 请求来源
	public enum PfType {

		android, ios, web, h5;

		public static PfType getEnum(String pfType) {

			for (PfType pfTypes : PfType.values()) {
				if (pfTypes.toString().equalsIgnoreCase( pfType )) {
					return pfTypes;
				}
			}
			return null;
		}
	}

}