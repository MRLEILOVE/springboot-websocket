/**  
 * Project Name:core-tool  
 * File Name:DateUtil.java  
 * Package Name:com.core.tool  
 * DateTime: Jul 18, 2019 6:03:17 PM <br />
 * Copyright (c) 2019, 仙灵科技 All Rights Reserved.  
 *  
*/

package com.core.web.tool;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import com.core.common.DTO.ReturnDTO;
import com.core.common.constant.IConstant;
import com.core.tool.JSONUtil;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * <p>
 *   Web实用类
 * </p>
 * ClassName:WebUtil <br/>
 * Description: TODO 添加描述. <br/>
 * DateTime: Jul 18, 2019 6:03:17 PM <br />
 * 
 * @author Administrator
 * @version
 * @since JDK 1.8
 * @see
 */
public class WebUtil {
	
	private static final Logger LOG = LoggerFactory.getLogger(WebUtil.class);

	/**
	 * <p>
	 *   判断是否是AJAX请求
	 * </p>
	 * isAJAX:(这里用一句话描述这个方法的作用). <br/>  
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>  
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>  
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>  
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>  
	 *  
	 * @author Administrator  
	 * @param httpRequest
	 * @return  
	 * @since JDK 1.8
	 */
	public static final boolean isAJAX(HttpServletRequest httpRequest) {
		String xRequestedWith = httpRequest.getHeader( "X-Requested-With" );
		return xRequestedWith != null && "XMLHttpRequest".equals( xRequestedWith );
	}
	
	/**
	 * 
	 * @param returnDTO
	 * @param response
	 */
	public static final void writeError(ReturnDTO<Object> returnDTO, HttpServletResponse response) {
		response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
		response.setCharacterEncoding(IConstant.UTF_8);
		response.setStatus(returnDTO.getCode());
		
		try {
			OutputStream os = response.getOutputStream();
			
//			com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
//			objectMapper.writeValue(os, returnDTO);
			os.write(JSONUtil.toString(returnDTO).getBytes(IConstant.UTF_8));
			
			os.flush();
			os.close();
			os = null;
		} catch (JsonGenerationException e) {
			LOG.error(e.toString());
		} catch (JsonMappingException e) {
			LOG.error(e.toString());
		} catch (IOException e) {
			LOG.error(e.toString());
		}
	}
	
}
