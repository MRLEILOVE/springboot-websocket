package com.bittrade.c2c.controller;

import com.bittrade.c2c.service.ITPaymentModeService;
import com.bittrade.pojo.dto.TPaymentModeDTO;
import com.bittrade.pojo.model.TPaymentMode;
import com.bittrade.pojo.vo.TPaymentModeVO;
import com.core.framework.base.controller.BaseController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
