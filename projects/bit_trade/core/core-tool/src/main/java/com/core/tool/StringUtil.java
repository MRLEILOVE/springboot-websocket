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
 *   String实用类
 * </p>
 * ClassName:StringUtil <br/>
 * Description: TODO 添加描述. <br/>
 * DateTime: Jul 18, 2019 6:03:17 PM <br />
 * 
 * @author Administrator
 * @version
 * @since JDK 1.8
 * @see
 */
public class StringUtil {
	
	private static void t_1(String str) {
		System.out.println( "str=" + str );
		str = "12";
		System.out.println( "str=" + str );
	}
	private static void t_2(String strArr[]) {
		System.out.println( "strArr[0]=" + strArr[0] );
		strArr[0] = "22";
		System.out.println( "strArr[0]=" + strArr[0] );
	}
	
	private static void testStrRef() {
		String str_1 = "11";
		System.out.println( "str_1=" + str_1 );
		t_1(str_1);
		System.out.println( "str_1=" + str_1 );
		
		System.out.println(  );
		
		String strArr_2[] = { "21" };
		System.out.println( "strArr_2[0]=" + strArr_2[0] );
		t_2(strArr_2);
		System.out.println( "strArr_2[0]=" + strArr_2[0] );
	}
	
	public static void main(String[] args) {
		testStrRef();
	}
	
}
