/**  
 * Project Name:core-tool  
 * File Name:DateUtil.java  
 * Package Name:com.core.tool  
 * DateTime: Jul 18, 2019 6:03:17 PM <br />
 * Copyright (c) 2019, 仙灵科技 All Rights Reserved.  
 *  
*/

package com.core.tool;

import java.util.Date;

/**
 * <p>
 *   日期时间实用类
 * </p>
 * ClassName:DateUtil <br/>
 * Description: TODO 添加描述. <br/>
 * DateTime: Jul 18, 2019 6:03:17 PM <br />
 * 
 * @author Administrator
 * @version
 * @since JDK 1.8
 * @see
 */
public class DateUtil {
	
	/**
	 * 
	 * getBeginMinute:(这里用一句话描述这个方法的作用). <br/>  
	 * TODO(这里描述这个方法适用条件 – 可选).<br/>  
	 * TODO(这里描述这个方法的执行流程 – 可选).<br/>  
	 * TODO(这里描述这个方法的使用方法 – 可选).<br/>  
	 * TODO(这里描述这个方法的注意事项 – 可选).<br/>  
	 *  
	 * @author Administrator  
	 * @param date
	 * @return  
	 * @since JDK 1.8
	 */
	public static final Date getBeginMinute(Date date) {
		Date dt = new Date(date.getTime());
		
		dt.setSeconds( 0 );
		
		return dt;
	}
	
	public static void main(String[] args) {
		Date dt = new Date();
		System.out.println( "1 dt=" + dt );
		Date dt_2 =getBeginMinute(dt);
		System.out.println( "1 dt=" + dt );
		System.out.println( "2 dt_2=" + dt_2 );
		System.out.println( dt == dt_2 );
		System.out.println( dt_2.compareTo( dt ) );
	}
	
}
