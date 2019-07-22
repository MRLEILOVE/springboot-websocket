package com.bittrade.svc;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bittrade.entrust.machine.Robot;
import com.bittrade.svc.base.BaseTester;

public class RobotTester extends BaseTester {

	@Autowired
	private Robot robot;

	@Test
	public void test() {
		robot.test();
	}

}
