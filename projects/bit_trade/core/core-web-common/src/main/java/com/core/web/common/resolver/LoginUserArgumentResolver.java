package com.core.web.common.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.core.common.annotation.ALoginUser;
import com.core.tool.JWTUtil;
import com.core.web.common.entity.LoginUser;

//@lombok.extern.slf4j.Slf4j
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
//		final Method method = parameter.getMethod();
//		final Class<?> clazz = parameter.getMethod().getDeclaringClass();
//
//		boolean isHasObjectAnn = clazz.isAnnotationPresent(LoginUser.class) || method.isAnnotationPresent(LoginUser.class);
//		boolean isHasLoginUserParameter = parameter.getParameterType().isAssignableFrom(TUser.class);
//
//		return isHasObjectAnn && isHasLoginUserParameter;
		
		// 条件判断可以用其中任意组合。
		return parameter.hasParameterAnnotation(ALoginUser.class) && LoginUser.class.isAssignableFrom(parameter.getParameterType());
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//		LoginUser obj_loginUser = parameter.getParameterAnnotation(ALoginUser.class);
		Authentication obj_authentication = SecurityContextHolder.getContext().getAuthentication();
//		Object obj_principal = obj_authentication.getPrincipal();
//		if (obj_principal == null) {
//			//从  accessToken获得用户信息
//			String token = webRequest.getHeader(HttpHeaders.AUTHORIZATION);
//			if (token == null) {
//				token = webRequest.getParameter("accessToken");
//			}
//			// token to user .
//			obj_principal = token; // tmp .
//		}
//		if (obj_authentication instanceof Principal) {
//			System.out.println(((Principal) obj_authentication).getName());
//		}
//		if (obj_authentication instanceof UserDetails) {
//
//		}
		
		Object obj_detail = obj_authentication.getDetails();
		LoginUser loginUser = JWTUtil.getJWTPayload(((OAuth2AuthenticationDetails) obj_detail).getTokenValue(), LoginUser.class);
		
		return loginUser;
	}

}
