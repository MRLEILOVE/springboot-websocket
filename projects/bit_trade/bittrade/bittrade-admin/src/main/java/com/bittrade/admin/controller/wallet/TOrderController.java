package com.bittrade.admin.controller.wallet;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.admin.service.wallet.ITOrderService;
import com.bittrade.pojo.dto.TOrderDTO;
import com.bittrade.pojo.vo.TOrderVO;
import com.bittrade.pojo.model.TOrder;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tOrder" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TOrderController extends BaseController<TOrder, TOrderDTO, TOrderVO, ITOrderService> {
	
}
