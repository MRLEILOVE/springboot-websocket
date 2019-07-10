package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.currency.dao.ITKlineDAO;
import com.bittrade.pojo.dto.TKlineDTO;
import com.bittrade.pojo.vo.TKlineVO;
import com.bittrade.pojo.model.TKline;
import com.bittrade.currency.api.service.ITKlineService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tKline" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TKlineController extends BaseController<TKline, TKlineDTO, TKlineVO, ITKlineDAO, ITKlineService> {
	
}
