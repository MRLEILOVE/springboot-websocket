package com.bittrade.pojo.vo;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 绑定支付宝 VO
 */
@Data
@Builder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class BindingAliPayVO extends BindingVO implements Serializable {

	private static final long serialVersionUID = -8212946556600745395L;

	/**
	 * 真实姓名
	 */
	@NotNull(message = "真实姓名必填")
	private String trueName;

	/**
	 * 支付宝帐号
	 */
	@NotNull(message = "支付宝帐号必填")
	private String card;

	/**
	 * 支付宝收款码
	 */
	@NotNull(message = "支付宝收款码必填")
	private String qrCode;
}
