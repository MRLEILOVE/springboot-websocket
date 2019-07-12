package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.currency.api.service.IWUserWalletBillService;
import com.bittrade.pojo.dto.WUserWalletBillDTO;
import com.bittrade.pojo.model.WUserWalletBill;
import com.bittrade.pojo.vo.WUserWalletBillVO;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/wUserWalletBill" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WUserWalletBillController extends BaseController<WUserWalletBill, WUserWalletBillDTO, WUserWalletBillVO, IWUserWalletBillService> {
	
}
