package com.core.web.common.entity;

import lombok.Data;

import java.util.Objects;

/**
 * 登录用户。
 * @author Administrator
 *
 */
@Data
public class LoginUser {

	private Long user_id;
	private String user_name;
	// 其他的可以加。
	// TODO 需添加支付密码
	private String payPassWord = "e10adc3949ba59abbe56e057f20f883e";

	public Boolean checkPayPassWord(String payPassWord) {
		return Objects.equals(this.payPassWord, payPassWord);
	}

}
