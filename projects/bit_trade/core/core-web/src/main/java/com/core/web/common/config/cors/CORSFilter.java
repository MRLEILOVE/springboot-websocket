///**
// * https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Access_control_CORS
// */
//package com.core.web.common.config.cors;
//
//import java.io.IOException;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.context.annotation.Configuration;
//
///**
// * 
// * ClassName: CORSFilter <br/>  
// * Function: TODO ADD FUNCTION. <br/>  
// * Reason: TODO ADD REASON(可选). <br/>  
// * DateTime: Aug 6, 2019 2:49:57 PM <br />
// *  
// * @author Administrator  
// * @version   
// * @since JDK 1.8
// */
//@Configuration
//@WebFilter(filterName = "CORSFilter", urlPatterns = "/*")
//public class CORSFilter implements Filter {
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//
//	}
//
//	@Override
//	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
//			throws IOException, ServletException {
//		HttpServletResponse response = (HttpServletResponse) servletResponse;
//		HttpServletRequest request = (HttpServletRequest) servletRequest;
//		
//		/**
//		 * CORS可以分成两种：
//		 *   简单请求
//		 *   复杂请求
//		 * 
//		 * 一个简单的请求大致如下：
//		 *   HTTP方法是下列之一
//		 *     HEAD
//		 *     GET
//		 *     POST
//		 *   HTTP头包含
//		 *     Accept
//		 *     Accept-Language
//		 *     Content-Language
//		 *     Last-Event-ID
//		 *     Content-Type，但仅能是下列之一
//		 *       application/x-www-form-urlencoded
//		 *       multipart/form-data
//		 *       text/plain
//		 * 
//		 * 任何一个不满足上述要求的请求，即被认为是复杂请求。一个复杂请求不仅有包含通信内容的请求，同时也包含预请求（preflight request）。
//		 */
//		
//		/**
//		 * >>> Access to XMLHttpRequest at 'http://192.168.1.12:8080/ab?x=1' from origin 'http://192.168.1.12' has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource.
//		 * >>> Access to XMLHttpRequest at 'http://192.168.1.12:8080/ab?x=1' from origin 'http://192.168.1.12' has been blocked by CORS policy: The 'Access-Control-Allow-Origin' header contains multiple values '*,192.168.1.12', but only one is allowed.
//		 * >>> Access to XMLHttpRequest at 'http://192.168.1.12:8080/ab?x=1' from origin 'http://192.168.1.12' has been blocked by CORS policy: The 'Access-Control-Allow-Origin' header contains the invalid value '192.168.1.12'.
//		 * >>> Access to XMLHttpRequest at 'http://192.168.1.12:8080/ab?x=1' from origin 'http://192.168.1.12' has been blocked by CORS policy: The 'Access-Control-Allow-Origin' header has a value 'http://192.168.1.13' that is not equal to the supplied origin.
//		 * >>> Access to XMLHttpRequest at 'http://192.168.1.12:8080/ab?x=1' from origin 'http://192.168.1.12' has been blocked by CORS policy: Method PUT is not allowed by Access-Control-Allow-Methods in preflight response.
//		 * >>> Access to XMLHttpRequest at 'http://192.168.1.12:8080/ab?x=1' from origin 'http://192.168.1.12' has been blocked by CORS policy: Request header field authorization is not allowed by Access-Control-Allow-Headers in preflight response.
//		 * >>> Access to XMLHttpRequest at 'http://192.168.1.12:8080/ab?x=1' from origin 'null' has been blocked by CORS policy: Response to preflight request doesn't pass access control check: The 'Access-Control-Allow-Origin' header has a value 'http://192.168.1.12' that is not equal to the supplied origin.
//		 * >>> Response to preflight request doesn't pass access control check: No 'Access-Control-Allow-Origin' header is present on the requested resource. Origin 'null' is therefore not allowed access. The response had HTTP status code 403
//		 */
//		
//		/**
//		 * nginx.conf
//		 * 
//		 * #add_header 'Access-Control-Allow-Origin' '*'; # http://192.168.1.12
//		 * #add_header 'Access-Control-Allow-Credentials' 'true';
//		 * #add_header 'Access-Control-Allow-Methods' 'GET, POST, OPTIONS';
//		 * #add_header 'Access-Control-Allow-Headers' 'DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Authorization';
//		 */
//		
////		String origin = request.getHeader( "Origin" );
//		response.setHeader( "Access-Control-Allow-Origin", "*" ); // origin "*" "http://192.168.1.12" "*,http://192.168.1.12"
//		response.setHeader( "Access-Control-Allow-Methods", "POST, DELETE, PUT, GET, OPTIONS" ); // POST, DELETE, PUT, GET, OPTIONS
//		response.setHeader( "Access-Control-Allow-Headers", "DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Authorization" ); // *
//		response.setHeader( "Access-Control-Allow-Credentials", "true" );
//		response.setHeader( "Access-Control-Max-Age", "3600" );
////		response.setHeader( "Access-Control-Expose-Headers", "X-forwared-port, X-forwarded-host" );
////		response.setHeader( "Vary", "Origin,Access-Control-Request-Method,Access-Control-Request-Headers" );
//		
//		String method = request.getMethod();
//		if (method.equalsIgnoreCase( "OPTIONS" )) {
//			servletResponse.getOutputStream().write( "Success".getBytes( "utf-8" ) );
//		} else {
//			filterChain.doFilter( servletRequest, servletResponse );
//		}
//	}
//
//	@Override
//	public void destroy() {
//
//	}
//}
