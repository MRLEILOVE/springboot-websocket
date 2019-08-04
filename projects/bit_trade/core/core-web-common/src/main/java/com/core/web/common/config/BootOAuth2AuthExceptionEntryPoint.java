package com.core.web.common.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.core.common.DTO.ReturnDTO;
import com.core.web.common.enums.HttpStatusEnumer;
import com.core.web.tool.WebUtil;

@Component
public class BootOAuth2AuthExceptionEntryPoint extends OAuth2AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
			throws IOException, ServletException {
		WebUtil.writeError(ReturnDTO.error(HttpStatusEnumer.UNAUTHORIZED.getCode(), e), response);
	}
}
