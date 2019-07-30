package com.td.vo;

import com.td.domain.Base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description 用户返回参数对象
 * @author 
 * @date 
 */
@Setter
@Getter
@ToString
public class UserVO extends Base {

	/**
	 * 用户账号
	 */
	private String	account;

	/**
	 * 用户名
	 */
	private String	name;

	/**
	 * 用户密码
	 */
	private String	password;

	/**
	 * 用户角色
	 */
	private RoleVO	role;

}
