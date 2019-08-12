/**  
 * Project Name:bittrade-entrust  
 * File Name:BeanConfig.java  
 * Package Name:com.bittrade.entrust.config  
 * DateTime: Aug 12, 2019 2:30:44 PM <br />
 * Copyright (c) 2019, 仙灵科技 All Rights Reserved.  
 *  
*/  
  
package com.bittrade.entrust.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationContextEvent;

import com.bittrade.entrust.api.service.ITEntrustService;
import com.bittrade.entrust.service.impl.MakeAMatchServiceImpl;

/**  
 * ClassName:SpringApplicationContextListener <br/>  
 * Description: TODO 添加描述. <br/>  
 * DateTime: Aug 12, 2019 2:30:44 PM <br />
 * @author   Administrator  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
@Configuration
public class SpringApplicationContextListener implements ApplicationListener<ApplicationContextEvent> {

	@Override
	public void onApplicationEvent(ApplicationContextEvent event) {
//		System.out.println( event.getApplicationContext().getParent() );
//		System.out.println( event.getApplicationContext().getDisplayName() );
		
		init();
	}

	@Autowired
	MakeAMatchServiceImpl makeAMatchService;
	@Autowired
	ITEntrustService entrustService;
	
	private void init() {
		System.out.println( "makeAMatchService=" + makeAMatchService );
		System.out.println( "entrustService=" + entrustService );
		makeAMatchService.initialEntrust();
	}
	
}
