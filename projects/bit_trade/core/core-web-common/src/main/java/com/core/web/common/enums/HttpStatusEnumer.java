package com.core.web.common.enums;

import org.springframework.http.HttpStatus;

import com.core.common.enums.base.IBaseEnumer;

import lombok.Getter;

/**
 * HTTP状态
 * @author Administrator
 * @datetime Jul 5, 2019 10:00:58 AM
 *
 */
@Getter
public enum HttpStatusEnumer implements IBaseEnumer<Integer> {
	
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED.value(), "没有认证"), 
	FORBIDDEN(HttpStatus.FORBIDDEN.value(), "访问被禁止"), 
	NOT_FOUND(HttpStatus.NOT_FOUND.value(), "找不到服务"), 
	
	;
	
	private Integer code;
	private String name;
	
	HttpStatusEnumer(int code, String name) {
		this.code = code;
		this.name = name;
	}

}
