package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.currency.api.service.IWConfigWalletService;
import com.bittrade.pojo.dto.WConfigWalletDTO;
import com.bittrade.pojo.model.WConfigWallet;
import com.bittrade.pojo.vo.WConfigWalletVO;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/wConfigWallet" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WConfigWalletController extends BaseController<WConfigWallet, WConfigWalletDTO, WConfigWalletVO, IWConfigWalletService> {
	
}
