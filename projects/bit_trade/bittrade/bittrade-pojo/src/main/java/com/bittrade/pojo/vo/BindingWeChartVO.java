package com.bittrade.pojo.vo;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 绑定微信支付 VO
 */
@Data
@Builder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class BindingWeChartVO extends BindingVO implements Serializable {

	private static final long serialVersionUID = -8212946556600745395L;

	/**
	 * 微信昵称
	 */
	@NotNull(message = "微信昵称必填")
	private String trueName;

	/**
	 * 微信帐号
	 */
	@NotNull(message = "微信帐号必填")
	private String card;

	/**
	 * 微信收款码
	 */
	@NotNull(message = "微信收款码必填")
	private String qrCode;
}
