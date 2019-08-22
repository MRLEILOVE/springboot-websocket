package com.bittrade.pojo.vo;

import java.io.Serializable;
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

import com.bittrade.pojo.model.TAdvertInfo;
import com.core.common.annotation.CheckEnumValue;

import lombok.Builder;
import lombok.Data;

/**
 * 广告VO
 * <br/>
 *
 * @author ：leigq
 * @date ：2019/8/19 14:49
 */
@Data
@Builder
public class AdvertInfoVO implements Serializable {

	private static final long serialVersionUID = -5820626891608577661L;

	/**
	 * 币种id
	 */
	@NotNull(message = "coinId cannot be null")
	private Long coinId;

	/**
	 * 类型 1:出售 2:购买
	 */
	@NotNull(message = "type cannot be null")
	@CheckEnumValue(enumClass = TAdvertInfo.AdvertTypeEnum.class, enumMethod = "isValidAdvertType")
	private Integer type;

	/**
	 * 定价方式 1：固定价格 2：浮动价格
	 */
	@NotNull(message = "pricingMode cannot be null")
	@CheckEnumValue(enumClass = TAdvertInfo.PricingModeEnum.class, enumMethod = "isValidPricingMode")
	private Integer pricingMode;

	/**
	 * 浮动比例
	 */
	@Min(value = 80, message = "浮動比例最小80")
	@Max(value = 120, message = "浮動比例最大120")
	private Integer floatingRatio;

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
	@CheckEnumValue(enumClass = TAdvertInfo.PaymentTime.class, enumMethod = "isValidPaymentTime")
	private Integer paymentTime;

	/**
	 * 对手限制-认证等级
	 */
	@NotNull(message = "認證等級必填")
	@CheckEnumValue(enumClass = TAdvertInfo.CertificationLevel.class, enumMethod = "isValidCertificationLevel")
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
		return Objects.equals(TAdvertInfo.PricingModeEnum.FLOAT.getCode(), this.pricingMode);
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

}
