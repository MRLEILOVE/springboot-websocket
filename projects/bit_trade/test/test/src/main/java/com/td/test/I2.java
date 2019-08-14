/**  
 * Project Name:bittrade-entrust  
 * File Name:I.java  
 * Package Name:com.bittrade.entrust.service.impl.tz  
 * DateTime: Aug 14, 2019 4:55:11 PM <br />
 * Copyright (c) 2019, 仙灵科技 All Rights Reserved.  
 *  
*/  
  
package com.td.test;  
/**  
 * ClassName:I <br/>  
 * Description: TODO 添加描述. <br/>  
 * DateTime: Aug 14, 2019 4:55:11 PM <br />
 * @author   Administrator  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
public interface I2 {

	int X = 3;
	
	default String i2(String str) {
		return "i2() str=" + str;
	}

	default String common(String str) {
		return "I2.common() str=" + str;
	}
	
}
  
