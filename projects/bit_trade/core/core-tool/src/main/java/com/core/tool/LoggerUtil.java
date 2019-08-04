/**  
 * Project Name:core-tool  
 * File Name:DateUtil.java  
 * Package Name:com.core.tool  
 * DateTime: Jul 18, 2019 6:03:17 PM <br />
 * Copyright (c) 2019, 仙灵科技 All Rights Reserved.  
 *  
*/

package com.core.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 *   Logger实用类
 * </p>
 * ClassName:LoggerUtil <br/>
 * Description: TODO 添加描述. <br/>
 * DateTime: Jul 18, 2019 6:03:17 PM <br />
 * 
 * @author Administrator
 * @version
 * @since JDK 1.8
 * @see
 */
public class LoggerUtil {
	
	private Logger logger;
	
	/**
	 * 
	 * @param cls
	 */
	LoggerUtil(Class<?> cls) {
		LoggerUtil.this.logger = LoggerFactory.getLogger(cls);
	}
	
	/**
	 * 
	 * @param msg
	 */
	public void info(String msg) {
		logger.info(msg);
	}
	
	/**
	 * 
	 * @param msg
	 */
	public void warn(String msg) {
		logger.warn(msg);
	}
	
	/**
	 * 
	 * @param msg
	 */
	public void error(String msg) {
		logger.error(msg);
	}
	
	/**
	 * 
	 * @param t
	 */
	public void error(Throwable t) {
		logger.error( t.toString() );
	}
	
}
