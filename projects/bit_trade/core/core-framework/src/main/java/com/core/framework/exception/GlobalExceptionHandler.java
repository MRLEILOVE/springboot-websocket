package com.core.framework.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.core.framework.DTO.ReturnDTO;

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

}
