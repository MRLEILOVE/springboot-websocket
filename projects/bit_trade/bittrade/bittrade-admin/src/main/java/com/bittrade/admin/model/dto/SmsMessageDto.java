package com.bittrade.admin.model.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SmsMessageDto implements Serializable {

	private static final long serialVersionUID = 1L;

	// 手机号码,多个用逗号隔开
	private String				moblie;

	// 区号
	private String				areaCode;

	// 发送模板的变量值{code:123456}
	private Map<String, Object>	param = new HashMap<>();

	// 添加单个模板参数,应为经常模板变量都是一个
	public void setParamSingle(String key, String value) {
		this.param.put( key, value );
	}
}
