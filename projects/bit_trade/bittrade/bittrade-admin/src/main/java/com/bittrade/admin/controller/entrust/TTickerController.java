package com.bittrade.admin.controller.entrust;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.admin.service.entrust.ITTickerService;
import com.bittrade.pojo.dto.TTickerDTO;
import com.bittrade.pojo.vo.TTickerVO;
import com.bittrade.pojo.model.TTicker;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tTicker" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TTickerController extends BaseController<TTicker, TTickerDTO, TTickerVO, ITTickerService> {
	
}
