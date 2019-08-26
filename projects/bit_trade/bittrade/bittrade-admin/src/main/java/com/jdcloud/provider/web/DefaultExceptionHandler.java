package com.jdcloud.provider.web;

import com.jdcloud.base.exception.BusinessException;
import com.jdcloud.provider.utils.PermissionUtils;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 自定义异常处理器
 * 
 * @author ruoyi
 */
@RestControllerAdvice
public class DefaultExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger( DefaultExceptionHandler.class );

	/**
	 * 权限校验失败
	 */
	@ExceptionHandler(AuthorizationException.class)
	public Wrapper<String> handleAuthorizationException(AuthorizationException e) {
		log.info( "<== AdminException," + e.getMessage(), e );
		return WrapMapper.error( PermissionUtils.getMsg( e.getMessage() ) );
	}

	/**
	 * 请求方式不支持
	 */
	@ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
	public Wrapper<String> handleException(HttpRequestMethodNotSupportedException e) {
		log.error( "<== AdminException," + e.getMessage(), e );
		return WrapMapper.error( "不支持' " + e.getMethod() + "'请求" );
	}
	
	/**
	 * 业务异常.
	 *
	 * @param e the e
	 *
	 * @return the wrapper
	 */
	@ExceptionHandler(BusinessException.class)
	public Wrapper businessException(BusinessException e) {
		log.info("业务异常={}", e.getMessage(), e);
		return WrapMapper.error(e.getMessage());
	}

	/**
	 * 系统异常
	 */
	@ExceptionHandler(Exception.class)
	public Wrapper<String> handleException(Exception e) {
		log.error( "<== AdminException," + e.getMessage(), e );
		String errorMsg = e.getMessage().length()<15?e.getMessage():"服务器错误，请联系管理员";
		return WrapMapper.error( errorMsg );
	}

}
