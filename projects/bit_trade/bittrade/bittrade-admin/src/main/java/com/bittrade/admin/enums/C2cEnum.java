package com.bittrade.admin.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 用户状态
 *
 * @author ourblue
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class C2cEnum {

	// 交易对类型（1=CNY/USDT，2=CNY/BI）
	public enum productType {
//		由于admin先更新后更新字段类型
		CNY_USDT(0, "USDT"), CNY_BI(1, "BI"),BTC(11,"BTC");
//		CNY_USDT(1, "CNY/USDT"), CNY_BI(2, "CNY/BI"),BTC(11,"BTC");
		productType(Integer code, String value) {
			this.code = code;
			this.value = value;
		}

		@Getter
		private Integer	code;
		@Getter
		private String	value;

	}

	// 委托单类型（1=CNY/USDT，2=CNY/BI）
	public enum entrustType {

		ENTRUST_SELL(1, "出售委托单"), ENTRUST_BUY(2, "购买委托单");
		entrustType(Integer code, String value) {
			this.code = code;
			this.value = value;
		}

		@Getter
		private Integer	code;
		@Getter
		private String	value;

	}

	// 用户认证状态 （1=认证成功，2=待审核，3=审核不成功,4=黑名单）
	public enum authStauts {

		AUTH_SUCCEED(1, "认证成功"), AUTH_AWAIT(2, "待审核"), AUTH_REJECT(3, "审核不成功"), AUTH_BLACKLIST(4, "黑名单");
		authStauts(Integer code, String value) {
			this.code = code;
			this.value = value;
		}

		@Getter
		private Integer	code;
		@Getter
		private String	value;

	}


	// 订单状态（0=已取消，1=已完成，2=待支付，3=待放行，4=拒绝放行）
	public enum C2CorderStauts {
		CANCELLED(0, "已取消"), COMPLETED(1, "已完成"), TOBEPAID(2, "待支付"), PENDING_RELEASE(4, "待放行"),REFUSE_TO_RELEASE(4, "拒绝放行");
		C2CorderStauts(Integer code, String value) {
			this.code = code;
			this.value = value;
		}

		@Getter
		private Integer	code;
		@Getter
		private String	value;

	}



	// 订单类型（1=出售，2=购买）
	public enum orderUserType {
		ORDERUSEEER_BUY(1, "购买"), ORDERUSEEER_SELL(2, "出售");
		orderUserType(Integer code, String value) {
			this.code = code;
			this.value = value;
		}
		@Getter
		private Integer	code;
		@Getter
		private String	value;

	}

	// 合伙人类型（1=普通节点，2=超级节点）
	public enum partnerType {
		PARTNER_DREAM(1, "普通节点"), PARTNER_NODE(2, "超级节点");
		partnerType(Integer code, String value) {
			this.code = code;
			this.value = value;
		}
		@Getter
		private Integer	code;
		@Getter
		private String	value;

	}

	// 合伙人档位（500=普通节点，3000=超级节点）
	public enum partnerGroup {
		PARTNER_DREAM(new BigDecimal("500"), "普通节点"), PARTNER_NODE(new BigDecimal("3000"), "超级节点");
		partnerGroup(BigDecimal code, String value) {
			this.code = code;
			this.value = value;
		}
		@Getter
		private BigDecimal	code;
		@Getter
		private String	value;

	}


	// 是否可以充币(1=是2=否)
	public enum topUpCoin {
		YES(1, "是"), NO(2, "否");
		topUpCoin(Integer code, String value) {
			this.code = code;
			this.value = value;
		}
		@Getter
		private Integer	code;
		@Getter
		private String	value;
	}


	// 是否可以提币(1=是2=否)
	public enum drawCoin {
		YES(1, "是"), NO(2, "否");
		drawCoin(Integer code, String value) {
			this.code = code;
			this.value = value;
		}
		@Getter
		private Integer	code;
		@Getter
		private String	value;
	}


	/**
	 * 状态  1：正常   2：禁用
	 */
	public enum status {
		NORMAL(1, "正常"), DISABLE(2, "禁用");
		status(Integer code, String value) {
			this.code = code;
			this.value = value;
		}
		@Getter
		private Integer	code;
		@Getter
		private String	value;

	}


}