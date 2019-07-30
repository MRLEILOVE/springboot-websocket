package com.td.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.Api;

/**
 */
@Api(value = "网页控制层", description = "网页控制层")
@Controller
@RequestMapping("/html/")
public class HTMLController {

	/**
	 * @return
	 */
	@GetMapping("index")
	public String index() throws Exception {
		return "index";
	}

}
