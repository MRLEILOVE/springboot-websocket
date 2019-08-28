package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.TPaymentModeDTO;
import com.bittrade.pojo.vo.TPaymentModeVO;
import com.bittrade.pojo.model.TPaymentMode;
import com.bittrade.currency.api.service.ITPaymentModeService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tPaymentMode" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TPaymentModeController extends BaseController<TPaymentMode, TPaymentModeDTO, TPaymentModeVO, ITPaymentModeService> {
	
}