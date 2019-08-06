package com.test.svc.ctl;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@RequestMapping(value="/post", method=RequestMethod.POST)
	public String post(String str) {
		System.out.println(LocalDateTime.now() + ", com.test.svc.ctl.TestController.post(String)");
		return "post() str=" + str;
	}
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public String delete(String str) {
		System.out.println(LocalDateTime.now() + ", com.test.svc.ctl.TestController.delete(String)");
		return "delete() str=" + str;
	}
	@RequestMapping(value="/put", method=RequestMethod.PUT)
	public String put(String str) {
		System.out.println(LocalDateTime.now() + ", com.test.svc.ctl.TestController.put(String)");
		return "put() str=" + str;
	}
	@RequestMapping(value="/get", method= {RequestMethod.GET, RequestMethod.OPTIONS})
	public String get(String str) {
		System.out.println(LocalDateTime.now() + ", com.test.svc.ctl.TestController.get(String)");
		return "get() str=" + str;
	}
}
