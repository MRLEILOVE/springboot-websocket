package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.TPaymentDTO;
import com.bittrade.pojo.vo.TPaymentVO;
import com.bittrade.pojo.model.TPayment;
import com.bittrade.currency.api.service.ITPaymentService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tPayment" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TPaymentController extends BaseController<TPayment, TPaymentDTO, TPaymentVO, ITPaymentService> {
	
}
