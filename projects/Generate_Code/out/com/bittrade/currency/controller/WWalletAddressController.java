package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.WWalletAddressDTO;
import com.bittrade.pojo.vo.WWalletAddressVO;
import com.bittrade.pojo.model.WWalletAddress;
import com.bittrade.currency.api.service.IWWalletAddressService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/wWalletAddress" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WWalletAddressController extends BaseController<WWalletAddress, WWalletAddressDTO, WWalletAddressVO, IWWalletAddressService> {
	
}
