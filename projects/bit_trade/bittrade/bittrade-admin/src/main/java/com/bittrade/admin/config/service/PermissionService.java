package com.bittrade.admin.config.service;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * js调用thymeleaf实现按钮权限可见
 * 
 * @author ourblue
 *
 */
@Service("permission")
public class PermissionService {

	public String hasPermi(String permission) {
		return isPermittedOperator( permission ) ? "" : "hidden";
	}

	private boolean isPermittedOperator(String permission) {
		return SecurityUtils.getSubject().isPermitted( permission );
	}

}
