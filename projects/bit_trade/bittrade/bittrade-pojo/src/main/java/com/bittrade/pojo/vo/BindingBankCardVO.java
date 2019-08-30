package com.bittrade.pojo.vo;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 绑定银行卡 VO
 */
@Data
@Builder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class BindingBankCardVO extends BindingVO implements Serializable {

	private static final long serialVersionUID = -8212946556600745395L;

	/**
	 * 真实姓名
	 */
	@NotNull(message = "真实姓名必填")
	private String trueName;

	/**
	 * 银行卡号
	 */
	@NotNull(message = "银行卡号必填")
	private String card;

	/**
	 * 开户银行
	 */
	@NotNull(message = "开户银行必填")
	private String bankName;

	/**
	 * 开户支行
	 */
	@NotNull(message = "开户支行必填")
	private String bankBranch;
}
