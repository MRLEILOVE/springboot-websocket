package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.WOrderDTO;
import com.bittrade.pojo.vo.WOrderVO;
import com.bittrade.pojo.model.WOrder;
import com.bittrade.currency.api.service.IWOrderService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/wOrder" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WOrderController extends BaseController<WOrder, WOrderDTO, WOrderVO, IWOrderService> {
	
}
