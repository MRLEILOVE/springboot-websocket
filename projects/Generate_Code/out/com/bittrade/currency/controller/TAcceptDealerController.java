package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.TAcceptDealerDTO;
import com.bittrade.pojo.vo.TAcceptDealerVO;
import com.bittrade.pojo.model.TAcceptDealer;
import com.bittrade.currency.api.service.ITAcceptDealerService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tAcceptDealer" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TAcceptDealerController extends BaseController<TAcceptDealer, TAcceptDealerDTO, TAcceptDealerVO, ITAcceptDealerService> {
	
}
