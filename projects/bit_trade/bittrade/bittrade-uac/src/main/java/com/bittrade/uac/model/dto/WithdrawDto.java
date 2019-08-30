package com.bittrade.uac.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * <p>
 * 提现参数Dto
 * <p>
 *
 * @author yongheng
 * @since 2018/10/27
 */
@Data
public class WithdrawDto {
	@NotNull
	private Long		userId;			// 用户ID
	@NotBlank
	private String		loginPassword;	// 登录密码
	@NotNull
	private BigDecimal	amount;			// 金额
}