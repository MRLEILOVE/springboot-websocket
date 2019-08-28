//package com.core.web.common.config.oauth2;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.stereotype.Component;
//
//import com.core.common.DTO.ReturnDTO;
//import com.core.web.common.enums.HttpStatusEnumer;
//import com.core.web.tool.WebUtil;
//
//@Component
//public class BootAccessDeniedHandler implements AccessDeniedHandler {
//	@Override
//	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex)
//			throws IOException, ServletException {
//		WebUtil.writeError(ReturnDTO.ret(HttpStatusEnumer.FORBIDDEN), response);
//	}
//}
