package com.test.bittrade.client.service;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ClientService {

	@Autowired
	private RestTemplate restTemplate;

	// 请求熔断注解，当服务出现问题时候会执行fallbackMetho属性的名为helloFallBack的方法
	@HystrixCommand(fallbackMethod = "helloFallBack")
	public String helloService(String str)/* throws ExecutionException, InterruptedException */ {
		return restTemplate.getForEntity("http://bittrade-svc/svc?str_=" + str, String.class).getBody();
	}

	public String helloFallBack(String str) {
		return "error str=" + str;
	}

}