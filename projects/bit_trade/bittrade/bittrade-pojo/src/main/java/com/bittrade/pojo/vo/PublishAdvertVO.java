package com.bittrade.pojo.vo;


import com.baomidou.mybatisplus.annotation.TableField;
import com.bittrade.pojo.model.TAdvertInfo;
import com.core.common.annotation.CheckEnumValue;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
public class PublishAdvertVO implements Serializable {

	private static final long serialVersionUID = 8274448353928906749L;


	/**
	 * 法币id
	 */
	private Long coinId;

	/**
	 * 类型 1:出售 2:购买
	 */
	@NotNull(message = "type cannot be null")
	@CheckEnumValue(enumClass = TAdvertInfo.AdvertTypeEnum.class, enumMethod = "isValidAdvertType")
	private Integer type;

	/**
	 * 定价方式 1：固定价格 2：浮动价格，一期不做浮动价格
	 */
	@NotNull(message = "pricingMode cannot be null")
	@CheckEnumValue(enumClass = TAdvertInfo.PricingModeEnum.class, enumMethod = "isValidPricingMode")
	@Deprecated
	private Integer pricingMode;

	/**
	 * 浮动比例，一期不做浮动价格
	 */
	@Min(value = 80, message = "浮動比例最小80")
	@Max(value = 120, message = "浮動比例最大120")
	@Deprecated
	private BigDecimal floatingRatio;

	/**
	 * 交易价格
	 */
	@DecimalMin(value = "0", inclusive = false, message = "交易價格需大於0")
	private BigDecimal price;

	/**
	 * 交易数量
	 */
	@TableField(exist = false)
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
	 * 隐藏价格，一期不做浮动价格
	 */
	@DecimalMin(value = "0", inclusive = false, message = "隱藏價格需大於0")
	@Deprecated
	private BigDecimal hidePrice;

	/**
	 * 对手限制-付款时间，仅出售有
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
	 * 收款方式id，出售单为收款方式, 购买单为付款方式,多个以逗号分隔
	 * <br/>
	 */
	@NotNull(message = "付款方式必填")
	@Size(min = 1, message = "付款方式必需選擇一種")
	private List<Long> paymentMethodId;

	/**
	 * 支付密码
	 */
	@TableField(exist = false)
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

}
