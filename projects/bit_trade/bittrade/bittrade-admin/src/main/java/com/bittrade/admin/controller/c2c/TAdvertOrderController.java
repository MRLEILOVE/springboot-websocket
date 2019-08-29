package com.bittrade.admin.controller.c2c;

import com.bittrade.pojo.dto.TAdvertOrderDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.admin.service.c2c.ITAdvertOrderService;
import com.bittrade.pojo.vo.TAdvertOrderVO;
import com.bittrade.pojo.model.TAdvertOrder;
import com.core.framework.base.controller.BaseController;

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
