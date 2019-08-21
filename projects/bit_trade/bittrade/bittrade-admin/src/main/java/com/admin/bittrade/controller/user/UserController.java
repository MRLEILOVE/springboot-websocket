/**  
 * Project Name:admin-bittrade  
 * File Name:IndexController.java  
 * Package Name:com.admin.bittrade.controller  
 * DateTime: Aug 21, 2019 11:56:06 AM <br />
 * Copyright (c) 2019, 仙灵科技 All Rights Reserved.  
 *  
*/  
  
package com.admin.bittrade.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**  
 * ClassName:UserController <br/>  
 * Description: TODO 添加描述. <br/>  
 * DateTime: Aug 21, 2019 11:56:06 AM <br />
 * @author   Administrator  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@RequestMapping(value = "/toAdd", method = { RequestMethod.GET })
	public String toAdd(HttpServletRequest req) {
		System.out.println( "com.admin.bittrade.controller.user.UserController.toAdd(HttpServletRequest)" );
		return "user/user";
	}
	
	@RequestMapping(value = "/users", method = { RequestMethod.GET })
	public String users(HttpServletRequest req) {
		return "user/users";
	}
	
}
  
