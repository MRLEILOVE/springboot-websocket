/**  
 * Project Name:core-tool  
 * File Name:DateUtil.java  
 * Package Name:com.core.tool  
 * DateTime: Jul 18, 2019 6:03:17 PM <br />
 * Copyright (c) 2019, 仙灵科技 All Rights Reserved.  
 *  
*/

package com.core.tool;

import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSON;
import com.core.common.constant.IConstant;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * JWT实用类
 * </p>
 * ClassName:JWTUtil <br/>
 * Description: TODO 添加描述. <br/>
 * DateTime: Jul 18, 2019 6:03:17 PM <br />
 * 
 * @author Administrator
 * @version
 * @since JDK 1.8
 * @see
 */
@Slf4j
public class JWTUtil {

	/**
	 * 
	 * getJWTPayload:(这里用一句话描述这个方法的作用). <br/>
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>
	 * 
	 * @author Administrator
	 * @param _JWTToken
	 * @param cls
	 * @return
	 * @since JDK 1.8
	 */
	public static final <T> T getJWTPayload(String _JWTToken, Class<T> cls) {
		T t = null;
		
		if (_JWTToken != null && _JWTToken.length() > 0) {
			String strArr_token[] = _JWTToken.split("\\.");
			if (strArr_token != null && strArr_token.length == 3) {
				final java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
				try {
					String loginStr = new String(decoder.decode(strArr_token[1]), IConstant.UTF_8);
					t = JSON.parseObject(loginStr, cls);
				} catch (UnsupportedEncodingException e) {
					log.error( e.toString() );
				}
			}
		}
		
		return t;
	}

}
