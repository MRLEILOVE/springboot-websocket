package test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.service.ITestService;
import test.service.impl.TestServiceImpl;

public class Snippet {
	
	public static void main(String[] args) throws Throwable {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
		System.out.println("ac=" + ac);
		TestServiceImpl ts = (TestServiceImpl) ac.getBean(ITestService.class);
		System.out.println("ts= " + ts);
		System.out.println("ts.getAddr()=" + ts.getAddr());
	}
}
