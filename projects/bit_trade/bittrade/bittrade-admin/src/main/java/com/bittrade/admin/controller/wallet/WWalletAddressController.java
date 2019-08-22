package com.bittrade.admin.controller.wallet;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.admin.service.wallet.IWWalletAddressService;
import com.bittrade.pojo.dto.WWalletAddressDTO;
import com.bittrade.pojo.vo.WWalletAddressVO;
import com.bittrade.pojo.model.WWalletAddress;
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
