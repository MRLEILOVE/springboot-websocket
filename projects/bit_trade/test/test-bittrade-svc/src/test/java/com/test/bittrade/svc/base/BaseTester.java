package com.test.bittrade.svc.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.bittrade.svc.SvcApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SvcApplication.class })
public abstract class BaseTester {

	@org.junit.Before
	public void setUp() {
//		System.out.println("com.bittrade.svc.base.BaseTester.setUp()");
	}

	@Test
	public void test() {
	}

}
