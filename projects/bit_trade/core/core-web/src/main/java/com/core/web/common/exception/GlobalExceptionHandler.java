package com.core.web.common.exception;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.core.common.DTO.ReturnDTO;
import com.core.web.common.enums.HttpStatusEnumer;
import com.core.web.constant.exception.BusinessException;

/**
 * <p>
 *   全局异常处理。
 * </p>
 * ClassName: GlobalExceptionHandler <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason: TODO ADD REASON(可选). <br/>  
 * DateTime: Jul 30, 2019 3:03:20 PM <br />
 *  
 * @author Administrator  
 * @version   
 * @since JDK 1.8
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger( GlobalExceptionHandler.class );
	
	/**
	 * 404
	 */
	@ExceptionHandler(value = NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ReturnDTO<?> notFoundException(HttpServletResponse response) {
		return ReturnDTO.ret(HttpStatusEnumer.NOT_FOUND);
	}

	@ExceptionHandler(value = { Exception.class })
	@ResponseStatus(value = HttpStatus.OK)
	public Object exceptionHandle(HttpServletRequest request, HttpServletResponse response, Exception e) {
		// e.printStackTrace();
		LOG.error( e.toString() );

//		if (com.core.tool.WebUtil.isAJAX( request )) { // with AJAX .
//			return ReturnDTO.error( e );
//		} else { // with page .
//			// ModelAndView mav = new ModelAndView();
//			// mav.addObject( "exception", e );
//			// mav.addObject( "url", request.getRequestURL() );
//			// mav.setViewName( "error" );
//			// return mav;
//		}
		return ReturnDTO.error( e );
	}
	
	/**
	 * 处理业务异常
	 */
	@ExceptionHandler(value = BusinessException.class)
	@ResponseStatus(HttpStatus.OK)
	public ReturnDTO<?> notFoundException(BusinessException e) {
		return ReturnDTO.error(e.getMessage());
	}


	/**
	 * 参数绑定失败
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(BindException.class)
	public ReturnDTO<?> handleBindException(BindException e) {
		String msg = handleBindingResult(e.getBindingResult());
		return ReturnDTO.error(msg);
	}

	/**
	 * 参数验证失败
	 */
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(ValidationException.class)
	public ReturnDTO<?> handleValidationException(ValidationException e) {
		return ReturnDTO.error(e.getMessage());
	}

	/**
	 * 处理参数绑定异常，并拼接出错的参数异常信息。
	 * <p>
	 * 创建人：leigq <br>
	 * <p>
	 * 修改人： <br>
	 * 修改时间： <br>
	 * 修改备注： <br>
	 * </p>
	 */
	private String handleBindingResult(BindingResult result) {
		if (result.hasErrors()) {
			Optional<FieldError> fieldError = result.getFieldErrors().stream().findFirst();
			if (fieldError.isPresent()) {
				return fieldError.get().getDefaultMessage();
			}
		}
		return null;
	}

}
