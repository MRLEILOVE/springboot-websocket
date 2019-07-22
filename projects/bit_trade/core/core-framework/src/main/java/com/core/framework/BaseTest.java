package com.core.framework;

public abstract class BaseTest {
	
	public static /*abstract */String getName() {
		return "testCase";
	}
	
	static {
		System.setProperty("svc_name", getName());
//		System.setProperty("spring.devtools.restart.enabled", "false");
	};

}
