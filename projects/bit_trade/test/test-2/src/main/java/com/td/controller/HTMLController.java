package com.td.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 */
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
