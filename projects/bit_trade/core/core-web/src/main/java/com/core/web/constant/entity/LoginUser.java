package com.core.web.constant.entity;

import java.util.Date;
import java.util.Objects;

import lombok.Data;

/**
 * 登录用户。
 * @author Administrator
 *
 */
@Data
public class LoginUser {

	private Long user_id = 1L;
	private String user_name;
	// 其他的可以加。
	// TODO 需添加支付密码
	private String payPassWord = "e10adc3949ba59abbe56e057f20f883e";

	// TODO 需添加用户认证等级
	private Integer certificationLevel = 1;

	// TODO 需添加注册时间
	private Date registeredTime;

	public Boolean checkPayPassWord(String payPassWord) {
		return Objects.equals(this.payPassWord, payPassWord);
	}

}
