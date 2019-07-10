//package com;
//
//
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.base.BaseTest;
//import com.corn.common.core.DTO.PageDTO;
//import com.test.model.T2;
//import com.test.service.T2Srvc;
//
//public class T2Tester extends BaseTest {
//
//	@Autowired
//	T2Srvc t2Srvc;
//
//	@Test
//	public void test() {
//		t2Srvc.add();
//		System.out.println("over !~~");
//	}
//
//	@Test
//	public void test_get() {
//		T2 t2 = t2Srvc.get(1);
//		System.out.println(t2);
//	}
//
//	@Test
//	public void test_slt() {
//		T2 t2Query = new T2();
//		PageDTO<T2> pgDTO = t2Srvc.slt(t2Query);
//		System.out.println(pgDTO);
//		System.out.println(pgDTO.getData());
//	}
//
//}
