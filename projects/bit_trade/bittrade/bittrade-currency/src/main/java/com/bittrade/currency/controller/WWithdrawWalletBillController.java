package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.currency.dao.IWWithdrawWalletBillDAO;
import com.bittrade.pojo.dto.WWithdrawWalletBillDTO;
import com.bittrade.pojo.vo.WWithdrawWalletBillVO;
import com.bittrade.pojo.model.WWithdrawWalletBill;
import com.bittrade.api.service.IWWithdrawWalletBillService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/wWithdrawWalletBill" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WWithdrawWalletBillController extends BaseController<WWithdrawWalletBill, WWithdrawWalletBillDTO, WWithdrawWalletBillVO, IWWithdrawWalletBillDAO, IWWithdrawWalletBillService<WWithdrawWalletBill, WWithdrawWalletBillDTO, WWithdrawWalletBillVO, IWWithdrawWalletBillDAO>> {
	
}
