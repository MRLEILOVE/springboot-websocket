//package com;
//
//
//import java.util.HashSet;
//import java.util.Set;
//
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.base.BaseTest;
//import com.test.model.T1;
//import com.test.model.T11;
//import com.test.model.T12;
//import com.test.service.T1Srvc;
//
//public class T1Tester extends BaseTest {
//
//	@Autowired
//	T1Srvc t1Srvc;
//
//	@Test
//	public void testAdd() {
//		T1 t1 = new T1();
//		t1.setName_1("name_1_1");
//		T11 t2 = new T11();
//		t2.setName_2("name_2_1");
//		t1.setT2(t2);
//		Set<T12> t3s = new HashSet<>();
//		T12 t3 = new T12();
//		t3.setName_22_("name_3_1");
//		t3s.add(t3);
//		t1.setHaha(t3s);
//		System.out.println(t1.get_ID());
//		t1Srvc.save(t1);
//		System.out.println(t1.get_ID());
//	}
//
//	@Test
//	public void testGet() {
//		T1 t1 = t1Srvc.get(1);
//		System.out.println(t1);
//		System.out.println(t1.getName_1());
//		System.out.println(t1.getT2());
//		System.out.println(t1.getT2().getName_2());
//		System.out.println(t1.getHaha());
//		System.out.println(t1.getHaha().size());
//	}
//
//}
