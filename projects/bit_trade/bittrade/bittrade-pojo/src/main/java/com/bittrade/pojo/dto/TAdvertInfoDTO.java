package com.bittrade.pojo.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.core.common.annotation.CheckEnumValue;
import com.core.framework.base.DTO.BaseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 
 * @author Administrator
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TAdvertInfoDTO extends BaseDTO<TAdvertInfoDTO> {
	
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
	private Date registeredTime;

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
	 * 定价方式是否为浮动价格
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/20 13:01
	 */
	public Boolean isFloatPricing() {
		return Objects.equals(PricingModeEnum.FLOAT.getCode(), this.pricingMode);
	}

	/**
	 * 广告类型是否为购买
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/20 13:01
	 */
	public Boolean isBuyType() {
		return Objects.equals(TAdvertInfoDTO.AdvertTypeEnum.BUY.getCode(), this.type);
	}

	/**
	 * 广告类型是否为出售
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/20 13:01
	 */
	public Boolean isSellType() {
		return Objects.equals(TAdvertInfoDTO.AdvertTypeEnum.SELL.getCode(), this.type);
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

	private static final long serialVersionUID = 1L;

//	private Long id;
	private Long userId;
//	private Long coinId;
//	private Integer type;
//	private Integer pricingMode;
//	private java.math.BigDecimal floatingRatio;
//	private java.math.BigDecimal price;
//	private java.math.BigDecimal hidePrice;
//	private java.math.BigDecimal minLimit;
//	private java.math.BigDecimal maxLimit;
//	private java.math.BigDecimal alreadyTransactionAmount;
//	private java.math.BigDecimal balanceAmount;
//	private java.math.BigDecimal freezeAmount;
//	private Long paymentMethodId;
//	private Byte status;
//	private Byte openOpponentLimit;
//	private Byte certificationLevel;
//	private java.time.LocalDateTime registeredTime;
//	private String message;
//	private Long version;
//	private java.time.LocalDateTime createTime;
//	private java.time.LocalDateTime updateTime;

}
