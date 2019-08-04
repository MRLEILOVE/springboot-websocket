/**  
 * Project Name:core-tool  
 * File Name:DateUtil.java  
 * Package Name:com.core.tool  
 * DateTime: Jul 18, 2019 6:03:17 PM <br />
 * Copyright (c) 2019, 仙灵科技 All Rights Reserved.  
 *  
*/

package com.core.tool;

/**
 * <p>
 *   LoggerFactory实用类
 * </p>
 * ClassName:LoggerFactoryUtil <br/>
 * Description: TODO 添加描述. <br/>
 * DateTime: Jul 18, 2019 6:03:17 PM <br />
 * 
 * @author Administrator
 * @version
 * @since JDK 1.8
 * @see
 */
public class LoggerFactoryUtil {
	
	private LoggerFactoryUtil() {
	}
	
	/**
	 * 
	 * getLogger:(这里用一句话描述这个方法的作用). <br/>  
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>  
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>  
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>  
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>  
	 *  
	 * @author Administrator  
	 * @param cls
	 * @return  
	 * @since JDK 1.8
	 */
	public static final LoggerUtil getLogger(Class<?> cls) {
		return new LoggerUtil(cls);
	}
	
}
