package com.td.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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

	/**
	 * @param str
	 * @return
	 */
	@GetMapping("auth")
	@ResponseBody
	public String auth(String str) throws Exception {
		return "auth str=" + str;
	}

	@ApiOperation(value = "查询通过 OAuth2.0 授权后获取的用户信息", notes = "通过 OAuth2.0 授权后获取的用户信息")
	@GetMapping("/principal")
	@ResponseBody
	public Principal principal(Principal principal) {
		return principal;
	}

}
