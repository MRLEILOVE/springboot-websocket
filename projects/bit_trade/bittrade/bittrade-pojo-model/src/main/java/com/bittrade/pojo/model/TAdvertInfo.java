package com.bittrade.pojo.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.core.common.annotation.CheckEnumValue;
import com.core.framework.base.model.BaseModel;

import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * 
 * 
 * @author Administrator
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName(value="t_advert_info")
public class TAdvertInfo extends BaseModel<TAdvertInfo> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @author Administrator
	 * @datetime Jul 4, 2019 4:40:32 PM
	 *
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
		
	};
	
	/**
	 * 主键
	 */
	@com.baomidou.mybatisplus.annotation.TableId(value = "id", type = com.baomidou.mybatisplus.annotation.IdType.AUTO)
	private Long id;

	/**
	 * 币种id
	 */
	@NotNull(message = "coinId cannot be null")
	private Long coinId;

	/**
	 * 类型 1:出售 2:购买
	 */
	@NotNull(message = "type cannot be null")
	@CheckEnumValue(enumClass = AdvertTypeEnum.class, enumMethod = "isValidAdvertType")
	private Integer type;

	/**
	 * 定价方式 1：固定价格 2：浮动价格
	 */
	@NotNull(message = "pricingMode cannot be null")
	@CheckEnumValue(enumClass = PricingModeEnum.class, enumMethod = "isValidPricingMode")
	private Integer pricingMode;

	/**
	 * 浮动比例
	 */
	@Min(value = 80, message = "浮動比例最小80")
	@Max(value = 120, message = "浮動比例最大120")
	private BigDecimal floatingRatio;

	/**
	 * 交易价格
	 */
	@DecimalMin(value = "0", inclusive = false, message = "交易價格需大於0")
	private BigDecimal price;

	/**
	 * 交易数量
	 */
	@NotNull(message = "交易數量必填")
	@DecimalMin(value = "0", inclusive = false, message = "交易數量需大於0")
	private BigDecimal amount;

	/**
	 * 单笔最小限额
	 */
	@NotNull(message = "單筆最小限額必填")
	@DecimalMin(value = "0", inclusive = false, message = "單筆最小限額需大於0")
	private BigDecimal minLimit;

	/**
	 * 单笔最大限额
	 */
	@NotNull(message = "單筆最大限額必填")
	@DecimalMin(value = "0", inclusive = false, message = "單筆最大限額需大於0")
	private BigDecimal maxLimit;

	/**
	 * 隐藏价格
	 */
	@DecimalMin(value = "0", inclusive = false, message = "隱藏價格需大於0")
	private BigDecimal hidePrice;

	/**
	 * 对手限制-付款时间
	 */
	@CheckEnumValue(enumClass = PaymentTime.class, enumMethod = "isValidPaymentTime")
	private Integer paymentTime;

	/**
	 * 对手限制-认证等级
	 */
	@NotNull(message = "認證等級必填")
	@CheckEnumValue(enumClass = CertificationLevel.class, enumMethod = "isValidCertificationLevel")
	private Integer certificationLevel;

	/**
	 * 对手限制-注册时间
	 */
	@NotNull(message = "註冊時間必填")
	@Past(message = "註冊時間需小於當前時間")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime registeredTime;

	/**
	 * 交易说明
	 */
	@Length(max = 140, message = "交易說明最多{max}字")
	private String message;

	/**
	 * 购买为付款方式，出售为收款方式
	 * <br/>
	 * 目前付款方式是每个用户一条记录，为了以后改为每种付款方式都对应一条记录，这里用集合接收
	 */
	@NotNull(message = "付款方式必填")
	@Size(min = 1, message = "付款方式必需選擇一種")
	private List<Long> paymentMethodId;

	/**
	 * 支付密码
	 */
	@NotNull(message = "支付密碼必填")
	private String payPassword;
	
	/**
	 * 用户id
	 */
	private Long userId;

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
	 * 状态：1，进行中；2，已下架(暂停)；3，已撤销；
	 */
	private Integer status;
	
	/**
	 * 是否开启对手限制 (0 禁用 1 启用)
	 */
	private Byte openOpponentLimit;

	/**
	 * 版本号
	 */
	private Long version;
	
	/**
	 * 创建时间
	 */
	private java.time.LocalDateTime createTime;
	
	/**
	 * 修改时间
	 */
	private java.time.LocalDateTime updateTime;


	/**
	 * 币名称
	 */
	private String coinName;

	/**
	 * c2c已成交数量
	 */
	private Integer c2cAlreadyDealCount;

	/**
	 * c2c总成交数量
	 */
	private Integer c2cTotalCount;

	/**
	 * c2c成交率
	 */
	private BigDecimal c2cTurnoverRate;

	/**
	 * 付款失效或放币时效
	 */
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
	
}
