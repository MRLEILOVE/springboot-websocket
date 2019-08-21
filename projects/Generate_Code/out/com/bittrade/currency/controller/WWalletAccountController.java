package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.WWalletAccountDTO;
import com.bittrade.pojo.vo.WWalletAccountVO;
import com.bittrade.pojo.model.WWalletAccount;
import com.bittrade.currency.api.service.IWWalletAccountService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/wWalletAccount" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WWalletAccountController extends BaseController<WWalletAccount, WWalletAccountDTO, WWalletAccountVO, IWWalletAccountService> {
	
}