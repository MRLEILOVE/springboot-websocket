package com.core.web.common.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.core.common.annotation.ALoginUser;
import com.core.web.constant.entity.LoginUser;
import com.core.web.tool.WebUtil;

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
		return WebUtil.getLoginUser();
	}

}
