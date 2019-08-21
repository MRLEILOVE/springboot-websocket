package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.WWalletBillDTO;
import com.bittrade.pojo.vo.WWalletBillVO;
import com.bittrade.pojo.model.WWalletBill;
import com.bittrade.currency.api.service.IWWalletBillService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/wWalletBill" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WWalletBillController extends BaseController<WWalletBill, WWalletBillDTO, WWalletBillVO, IWWalletBillService> {
	
}
