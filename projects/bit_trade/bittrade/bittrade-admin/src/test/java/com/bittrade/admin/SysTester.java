package com.bittrade.admin;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bittrade.admin.base.BaseTester;
import com.bittrade.admin.service.impl.sys.SysLogininforServiceImpl;
import com.bittrade.pojo.model.SysLogininfor;

public class SysTester extends BaseTester {

	@Autowired
	SysLogininforServiceImpl sysLogininforService;

	@Test
	public void test() {
		System.out.println( "sysLogininforService=" + sysLogininforService );
		SysLogininfor entity = new SysLogininfor();
		entity.setLoginName("loginName");
		sysLogininforService.save(entity);
	}

}
