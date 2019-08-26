package com.bittrade.admin.controller.wallet;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.admin.service.wallet.IWUserWalletService;
import com.bittrade.pojo.dto.WUserWalletDTO;
import com.bittrade.pojo.vo.WUserWalletVO;
import com.bittrade.pojo.model.WUserWallet;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/wUserWallet" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WUserWalletController extends BaseController<WUserWallet, WUserWalletDTO, WUserWalletVO, IWUserWalletService> {
	
}