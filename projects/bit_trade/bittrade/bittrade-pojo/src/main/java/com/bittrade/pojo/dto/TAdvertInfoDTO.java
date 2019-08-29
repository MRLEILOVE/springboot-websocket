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

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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

	private static final long serialVersionUID = 8964978986850395893L;

	private Long id;
	private Long userId;
	private Long coinId;
	private Byte type;
	private Byte pricingMode;
	private java.math.BigDecimal floatingRatio;
	private java.math.BigDecimal price;
	private java.math.BigDecimal hidePrice;
	private java.math.BigDecimal minLimit;
	private java.math.BigDecimal maxLimit;
	private java.math.BigDecimal alreadyTransactionAmount;
	private java.math.BigDecimal balanceAmount;
	private java.math.BigDecimal freezeAmount;
	private Long paymentMethodId;
	private Byte status;
	private Byte openOpponentLimit;
	private Byte certificationLevel;
	private java.time.LocalDateTime registeredTime;
	private String message;
	private Long version;
	private java.time.LocalDateTime createTime;
	private java.time.LocalDateTime updateTime;


	/*↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓额外字段，不属于数据库↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓*/

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
		revoked( 3, "已撤销"),
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
		return Objects.equals(AdvertTypeEnum.BUY.getCode(), this.type);
	}

	/**
	 * 广告类型是否为出售
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/20 13:01
	 */
	public Boolean isSellType() {
		return Objects.equals(AdvertTypeEnum.SELL.getCode(), this.type);
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
