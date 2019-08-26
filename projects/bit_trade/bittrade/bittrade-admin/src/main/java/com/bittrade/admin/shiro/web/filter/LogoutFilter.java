package com.bittrade.admin.shiro.web.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.subject.Subject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * logout 过滤器
 * 
 * @author ourblue
 *
 */
@Slf4j
public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter {

	@Setter
	@Getter
	private String loginUrl;

	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

		try {
			Subject subject = getSubject( request, response );
			String redirectUrl = getRedirectUrl( request, response, subject );
			subject.logout();
			issueRedirect( request, response, redirectUrl );
		} catch (Exception e) {
			log.error( " <==Encountered session exception during logout.  This can generally safely be ignored.", e );
		}
		return false;
	}

	/**
	 * .退出跳转URL
	 */
	@Override
	protected String getRedirectUrl(ServletRequest request, ServletResponse response, Subject subject) {
		String url = getLoginUrl();
		if (StringUtils.isNotEmpty( url )) {
			return url;
		}
		return super.getRedirectUrl( request, response, subject );
	}

}
