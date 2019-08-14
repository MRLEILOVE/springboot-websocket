/**  
 * Project Name:bittrade-entrust  
 * File Name:A.java  
 * Package Name:com.bittrade.entrust.service.impl.tz  
 * DateTime: Aug 14, 2019 4:55:01 PM <br />
 * Copyright (c) 2019, 仙灵科技 All Rights Reserved.  
 *  
*/  
  
package com.td.test;  
/**  
 * ClassName:A <br/>  
 * Description: TODO 添加描述. <br/>  
 * DateTime: Aug 14, 2019 4:55:01 PM <br />
 * @author   Administrator  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
public class A implements I1, I2 {

	public static void main(String[] args) {
		A a = new A();
		
		System.out.println( a.i1( "i1-" ) );
		System.out.println( a.i2( "i2-" ) );
		System.out.println( a.common( "common-" ) );
		System.out.println( X );
	}

	@Override
	public String common(String str) {
		return I1.super.common( str ) + "  =======================";
//		return "============== "+str;
	}
	
}
  
