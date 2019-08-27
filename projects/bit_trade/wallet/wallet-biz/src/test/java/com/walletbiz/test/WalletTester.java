package com.walletbiz.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.common.bittrade.service.impl.WWalletAccountServiceImpl;
import com.walletbiz.test.base.BaseTester;

public class WalletTester extends BaseTester {

	@Autowired
	WWalletAccountServiceImpl walletAccountServiceImpl;

	@Test
	public void test() {
	}

}
