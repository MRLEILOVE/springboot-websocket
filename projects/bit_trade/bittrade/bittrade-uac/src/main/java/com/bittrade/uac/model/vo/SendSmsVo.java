package com.bittrade.uac.model.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import java.io.Serializable;
import javax.validation.constraints.NotNull;

/**
 * register 用户 
 * 
 * @author ourblue
 *
 */

@Data
public class SendSmsVo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 手机区域码
	 */
	private String areaCode = "86";
	
	/**
	 * 手机号码
	 */
	@NotBlank
	private String phoneNumber;
	
	/**
	 * 发送短信类型
	 */
	@NotNull
	private Integer sendType;
	
}
