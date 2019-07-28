package com.td.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User implements Serializable {
	private static final long serialVersionUID = -8478114427891717226L;

	/**
	 * 用户账号
	 */
	private String account;

	/**
	 * 用户名
	 */
	private String name;

	/**
	 * 用户密码
	 */
	private String password;

}
