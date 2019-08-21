package com.wallet.biz.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/wConfigWallet" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WConfigWalletController {
	
}
