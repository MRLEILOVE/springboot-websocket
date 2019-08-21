/**  
 * Project Name:admin-bittrade  
 * File Name:IndexController.java  
 * Package Name:com.admin.bittrade.controller  
 * DateTime: Aug 21, 2019 11:56:06 AM <br />
 * Copyright (c) 2019, 仙灵科技 All Rights Reserved.  
 *  
*/  
  
package com.admin.bittrade.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.bittrade.dto.IndexDataDto;

/**  
 * ClassName:IndexController <br/>  
 * Description: TODO 添加描述. <br/>  
 * DateTime: Aug 21, 2019 11:56:06 AM <br />
 * @author   Administrator  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
@Controller
public class IndexController {
	
	@RequestMapping(value = "/login", method = { RequestMethod.GET })
	public String login() {
		System.out.println( "com.admin.bittrade.controller.IndexController.login()" );
		return "login";
	}
	
	@RequestMapping(value = "/index", method = { RequestMethod.GET })
	public String index(HttpServletRequest req) {
		System.out.println( "com.admin.bittrade.controller.IndexController.index()" );
		
		req.setAttribute( "user", new HashMap<String, Object>() {
			private static final long serialVersionUID = 1L;
			{
				put( "userName", "用户名" );
				put( "avatar", "" );
				put( "dept", new HashMap<String, Object>() {
					private static final long serialVersionUID = 1L;
					{
						put( "deptName", "部门名称" );
					}
				} );
			}
		} );
		
		return "index";
	}
	
	@RequestMapping(value = "/system/main", method = { RequestMethod.GET })
	@ResponseBody
	public IndexDataDto main(HttpServletRequest req) {
		return new IndexDataDto() {
			private static final long serialVersionUID = 1L;
			{
				
			}
		};
	}
	
}
  
