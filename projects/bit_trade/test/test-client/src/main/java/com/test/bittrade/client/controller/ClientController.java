package com.test.bittrade.client.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.test.bittrade.client.feign.IFeignService;
import com.test.bittrade.client.service.ClientService;

@RestController
public class ClientController {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private IFeignService feignService;
	@Autowired
	private ClientService clientService;

	@RequestMapping(value = "/client/{bb}", method = RequestMethod.GET)
	public String __(
			/* @RequestParam(name="zz", required=false) */
			@PathVariable(value="bb") String abc
			, HttpServletRequest req
			) {
		System.out.println("com.bittrade.client.controller.ClientController.index()");
//		String str_ret = restTemplate.getForObject("http://bittrade-svc/svc", String.class);
		String str_ret = feignService.svc(abc == null ? req.getParameter("a") : abc);
		return "hello world! str_ret=" + str_ret + " .";
	}

	@RequestMapping(value = "/c2", method = RequestMethod.GET)
	public String ___(
			HttpServletRequest req
			) {
		System.out.println("com.bittrade.client.controller.ClientController.___(HttpServletRequest)");
		String str_ret = clientService.helloService(req.getParameter("str"));
		return "hello world! str_ret=" + str_ret + " .";
	}

}