package com.bittrade.pojo.vo;

import com.bittrade.pojo.model.TPaymentMode;
import com.core.common.annotation.CheckEnumValue;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 绑定 VO，其他绑定方式继承此类
 */
@Data
class BindingVO implements Serializable {

	private static final long serialVersionUID = -8212946556600745395L;

	/**
	 * 展示设置 1：用于收款 2:用于付款 3：用于收付款
	 */
	@NotNull(message = "displaySetting cannot be null")
	@CheckEnumValue(enumClass = TPaymentMode.DisplaySettingEnum.class, enumMethod = "isValidDisplaySetting")
	private Integer displaySetting;

	/**
	 * 所属币种 如：人民币CNY
	 */
	@NotNull(message = "所属币种必选")
	private String belongCurrency;

	/**
	 * 短信验证码，6位, 前端自己做验证
	 */
//	@NotNull(message = "短信验证码必填")
//	private String smsValidatedCode;
}
