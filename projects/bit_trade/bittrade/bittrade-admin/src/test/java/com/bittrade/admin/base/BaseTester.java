package com.bittrade.admin.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bittrade.admin.AdminApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { AdminApplication.class })
public abstract class BaseTester extends com.core.framework.BaseTest {

	@org.junit.Before
	public void setUp() {
//		System.out.println("com.bittrade.svc.base.BaseTester.setUp()");
	}

	@Test
	public void test() {
	}

}