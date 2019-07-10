package com.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"classpath:spring/applicationContext-test.xml"})
public abstract class BaseTest {

    @Test
    public void testA() {
        System.out.println("public void testA() {");
    }
    
//    public static void main(String[] args) {
//    	org.springframework.context.ApplicationContext ac = new org.springframework.context.support.ClassPathXmlApplicationContext("classpath:spring/applicationContext-test.xml");
//    	System.out.println("ac=" + ac);
//    	ArticleService as = ac.getBean(ArticleService.class);
//    	System.out.println("as=" + as);
//	}
    
}
