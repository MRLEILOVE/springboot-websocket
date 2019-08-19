package com.bittrade.c2c.controller;

import com.bittrade.c2c.service.ITAdvertOrderService;
import com.bittrade.pojo.dto.TAdvertOrderDTO;
import com.bittrade.pojo.model.TAdvertOrder;
import com.bittrade.pojo.vo.TAdvertOrderVO;
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
@RequestMapping(value = { "/tAdvertOrder" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TAdvertOrderController extends BaseController<TAdvertOrder, TAdvertOrderDTO, TAdvertOrderVO, ITAdvertOrderService> {
	
}
