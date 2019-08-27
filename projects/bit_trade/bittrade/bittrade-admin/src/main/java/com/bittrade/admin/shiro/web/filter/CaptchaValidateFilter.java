package com.bittrade.admin.shiro.web.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

import com.bittrade.admin.shiro.constant.ShiroConstant;
import com.bittrade.admin.util.ShiroUtil;
import com.google.code.kaptcha.Constants;

import lombok.Setter;

/**
 * code filters
 * 
 * @author who ?
 *
 */
public class CaptchaValidateFilter extends AccessControlFilter {

	@Setter
	private boolean	captchaEnabled	= true;
	@Setter
	private String	captchaType		= "math";

	@Override
	public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		request.setAttribute( ShiroConstant.CURRENT_ENABLED, captchaEnabled );
		request.setAttribute( ShiroConstant.CURRENT_TYPE, captchaType );
		return super.onPreHandle( request, response, mappedValue );
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		// 验证码禁用 或不是表单提交 允许访问
		if (captchaEnabled == false || !"post".equals( httpServletRequest.getMethod().toLowerCase() )) {
			return true;
		}
		return validateResponse( httpServletRequest, httpServletRequest.getParameter( ShiroConstant.CURRENT_VALIDATECODE ) );
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		request.setAttribute( ShiroConstant.CURRENT_CAPTCHA, ShiroConstant.CAPTCHA_ERROR );
		return true;
	}

	public boolean validateResponse(HttpServletRequest request, String validateCode) {
		Object obj = ShiroUtil.getSession().getAttribute( Constants.KAPTCHA_SESSION_KEY );
		String code = String.valueOf( obj != null ? obj : "" );
		if (StringUtils.isEmpty( validateCode ) || !validateCode.equalsIgnoreCase( code )) {
			return false;
		}
		return true;
	}

}
