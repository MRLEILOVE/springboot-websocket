package com.bittrade.uac.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;



@Data
public class UserSmsDto implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 手机区域码
	 */
	private String areaCode;
	/**
	 * 用户id
	 */
	@NotBlank
	private Long userId;
	/**
	 * 手机号码
	 */
	@NotBlank
	private String phone;

	/**
	 * 发送短信类型
	 */
	@NotNull
	private Integer type;

	/**
	 * 短信模板
	 */
	private String smsTempletEnum;

}
