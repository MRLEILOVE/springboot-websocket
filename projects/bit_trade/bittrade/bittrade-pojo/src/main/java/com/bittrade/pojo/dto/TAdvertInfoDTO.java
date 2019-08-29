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

}
