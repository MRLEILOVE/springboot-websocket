package com.bittrade.pojo.model;

import com.baomidou.mybatisplus.annotation.*;
import com.core.framework.base.model.BaseModel;

import lombok.*;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Administrator
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value = "t_advert_info")
public class TAdvertInfo extends BaseModel<TAdvertInfo> {

	private static final long serialVersionUID = 1L;

	/**
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 */
	public static final class FieldNames {

		/**
		 * 主键
		 */
		public static final String ID = "id";

		/**
		 * 用户id
		 */
		public static final String USER_ID = "user_id";

		/**
		 * 法币id
		 */
		public static final String COIN_ID = "coin_id";

		/**
		 * 类型(1:出售 2:购买)
		 */
		public static final String TYPE = "type";

		/**
		 * 定价方式 1：固定价格 2：浮动价格
		 */
		public static final String PRICING_MODE = "pricing_mode";

		/**
		 * 浮动比例 (小数 0.01 = 1%)
		 */
		public static final String FLOATING_RATIO = "floating_ratio";

		/**
		 * 单价（CNY）
		 */
		public static final String PRICE = "price";

		/**
		 * 隐藏价格
		 */
		public static final String HIDE_PRICE = "hide_price";

		/**
		 * 最小限额（CNY）
		 */
		public static final String MIN_LIMIT = "min_limit";

		/**
		 * 最大限额（CNY）
		 */
		public static final String MAX_LIMIT = "max_limit";

		/**
		 * 广告已交易数量
		 */
		public static final String ALREADY_TRANSACTION_AMOUNT = "already_transaction_amount";

		/**
		 * 广告剩余数量
		 */
		public static final String BALANCE_AMOUNT = "balance_amount";

		/**
		 * 广告进行中冻结数量
		 */
		public static final String FREEZE_AMOUNT = "freeze_amount";

		/**
		 * 收款方式id，出售单为收款方式, 购买单为付款方式
		 */
		public static final String PAYMENT_METHOD_ID = "payment_method_id";

		/**
		 * 状态：1，进行中；2，已下架(暂停)；3，已撤销；
		 */
		public static final String STATUS = "status";

		/**
		 * 是否开启对手限制 (0 禁用 1 启用)
		 */
		public static final String OPEN_OPPONENT_LIMIT = "open_opponent_limit";

		/**
		 * 对手限制-认证等级
		 */
		public static final String CERTIFICATION_LEVEL = "certification_level";

		/**
		 * 对手限制-注册时间
		 */
		public static final String REGISTERED_TIME = "registered_time";

		/**
		 * 对手限制-付款时间
		 */
		public static final String PAYMENT_TIME = "payment_time";

		/**
		 * 交易说明（留言）
		 */
		public static final String MESSAGE = "message";

		/**
		 * 版本号
		 */
		public static final String VERSION = "version";

		/**
		 * 创建时间
		 */
		public static final String CREATE_TIME = "create_time";

		/**
		 * 修改时间
		 */
		public static final String UPDATE_TIME = "update_time";

	}

	;

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 法币id
	 */
	private Long coinId;

	/**
	 * 类型(1:出售 2:购买)
	 */
	private Integer type;

	/**
	 * 定价方式 1：固定价格 2：浮动价格
	 */
	private Integer pricingMode;

	/**
	 * 浮动比例 (小数 0.01 = 1%)
	 */
	private java.math.BigDecimal floatingRatio;

	/**
	 * 单价（CNY）
	 */
	private java.math.BigDecimal price;

	/**
	 * 隐藏价格
	 */
	private java.math.BigDecimal hidePrice;

	/**
	 * 最小限额（CNY）
	 */
	private java.math.BigDecimal minLimit;

	/**
	 * 最大限额（CNY）
	 */
	private java.math.BigDecimal maxLimit;

	/**
	 * 广告已交易数量
	 */
	private java.math.BigDecimal alreadyTransactionAmount;

	/**
	 * 广告剩余数量
	 */
	private java.math.BigDecimal balanceAmount;

	/**
	 * 广告进行中冻结数量
	 */
	private java.math.BigDecimal freezeAmount;

	/**
	 * 收款方式id，出售单为收款方式, 购买单为付款方式
	 */
	private Long paymentMethodId;

	/**
	 * 状态：1，进行中；2，已下架(暂停)；3，已撤销；
	 */
	private Integer status;

	/**
	 * 是否开启对手限制 (0 禁用 1 启用)
	 */
	private Integer openOpponentLimit;

	/**
	 * 对手限制-认证等级
	 */
	private Integer certificationLevel;

	/**
	 * 对手限制-注册时间
	 */
	private LocalDateTime registeredTime;

	/**
	 * 对手限制-付款时间
	 */
	private Integer paymentTime;

	/**
	 * 交易说明（留言）
	 */
	private String message;

	/**
	 * 版本号
	 */
	@Version
	private Long version;

	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;

	/**
	 * 修改时间
	 */
	private LocalDateTime updateTime;

	/**
	 * 币名称
	 */
	@TableField(exist = false)
	private String coinName;

	/**
	 * c2c已成交数量
	 */
	@TableField(exist = false)
	private Integer c2cAlreadyDealCount;

	/**
	 * c2c总成交数量
	 */
	@TableField(exist = false)
	private Integer c2cTotalCount;

	/**
	 * c2c成交率
	 */
	@TableField(exist = false)
	private BigDecimal c2cTurnoverRate;

	/**
	 * 付款失效或放币时效
	 */
	@TableField(exist = false)
	private Long paymentOrPutCoinAging;

	/**
	 * 广告类型
	 * <br/>
	 *
	 * @author ：leigq
	 * @date ：2019/8/19 17:58
	 */
	@AllArgsConstructor
	public enum AdvertTypeEnum {
		SELL(1, "出售"),
		BUY(2, "购买"),
		;

		@Getter
		private Integer code;

		@Getter
		private String describe;

		/**
		 * 验证广告类型
		 * <br/>
		 * create by: leigq
		 * <br/>
		 * create time: 2019/8/19 17:24
		 */
		public static boolean isValidAdvertType(Integer advertType) {
			for (AdvertTypeEnum advertTypeEnum : AdvertTypeEnum.values()) {
				if (advertTypeEnum.code.equals(advertType)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * 定价方式
	 * <br/>
	 * @author     ：leigq
	 * @date       ：2019/8/19 20:24
	 */
	@AllArgsConstructor
	public enum PricingModeEnum {
		FIXED(1, "固定价格"),
		FLOAT(2, "浮动价格"),
		;

		@Getter
		private Integer code;

		@Getter
		private String describe;

		/**
		 * 验证广告类型
		 * <br/>
		 * create by: leigq
		 * <br/>
		 * create time: 2019/8/19 17:24
		 */
		public static boolean isValidPricingMode(Integer pricingMode) {
			for (PricingModeEnum pricingModeEnum : PricingModeEnum.values()) {
				if (pricingModeEnum.code.equals(pricingMode)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * 状态
	 * <br/>
	 * @author     ：leigq
	 * @date       ：2019/8/19 20:24
	 */
	@AllArgsConstructor
	public enum StatusEnum {
		PROCESSING(1, "进行中"),
		PAUSE(2, "已下架(暂停)"),
		revoked(3, "已撤销"),
		;

		@Getter
		private Integer code;
		@Getter
		private String describe;

		public static boolean isValidStatus(Integer status) {
			for (StatusEnum statusEnum : StatusEnum.values()) {
				if (statusEnum.code.equals(status)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * 认证等级
	 * <br/>
	 * @author     ：leigq
	 * @date       ：2019/8/19 20:24
	 */
	@AllArgsConstructor
	public enum CertificationLevel {
		FIRST_LEVEL(1, "一级"),
		SECONDARY(2, "二级"),
		THIRD_LEVEL(3, "三级"),
		;

		@Getter
		private Integer code;
		@Getter
		private String describe;

		public static boolean isValidCertificationLevel(Integer level) {
			for (CertificationLevel certificationLevel : CertificationLevel.values()) {
				if (certificationLevel.code.equals(level)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * 付款时间
	 * <br/>
	 * @author     ：leigq
	 * @date       ：2019/8/19 20:24
	 */
	@AllArgsConstructor
	public enum PaymentTime {
		TEN_MINUTES(10, "十分钟"),
		FIFTEEN_MINUTES(15, "十五分钟"),
		TWENTY_MINUTES(20, "二十分钟"),
		;

		@Getter
		private Integer code;
		@Getter
		private String describe;

		public static boolean isValidPaymentTime(Integer time) {
			for (PaymentTime paymentTime : PaymentTime.values()) {
				if (paymentTime.code.equals(time)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * 广告类型是否为购买
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/20 13:01
	 */
	public Boolean isBuyType() {
		return Objects.equals(TAdvertInfo.AdvertTypeEnum.BUY.getCode(), this.type);
	}

	/**
	 * 广告类型是否为出售
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/20 13:01
	 */
	public Boolean isSellType() {
		return Objects.equals(TAdvertInfo.AdvertTypeEnum.SELL.getCode(), this.type);
	}

	/**
	 * 定价方式是否为浮动价格
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/20 13:01
	 */
	public Boolean isFloatingPrice() {
		return Objects.equals(PricingModeEnum.FLOAT.getCode(), this.pricingMode);
	}
}
