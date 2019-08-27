//package com.core.web.common.config.oauth2;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
//import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import com.core.common.DTO.ReturnDTO;
//import com.core.web.common.enums.HttpStatusEnumer;
//import com.core.web.tool.WebUtil;
//
//@Component
//public class BootOAuth2AuthenticationEntryPoint extends OAuth2AuthenticationEntryPoint {
//	@Override
//	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex)
//			throws IOException, ServletException {
//		Throwable thr_cause = ex.getCause();
//		if (thr_cause instanceof InvalidTokenException) {
//			WebUtil.writeError(ReturnDTO.error(HttpStatusEnumer.UNAUTHORIZED.getCode(), ex), response);
//		} else /*if () */{
//			WebUtil.writeError(ReturnDTO.ret(HttpStatusEnumer.NO_LOGIN), response);
//		}
//	}
//}
