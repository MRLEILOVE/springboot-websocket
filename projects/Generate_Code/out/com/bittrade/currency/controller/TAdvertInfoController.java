package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.TAdvertInfoDTO;
import com.bittrade.pojo.vo.TAdvertInfoVO;
import com.bittrade.pojo.model.TAdvertInfo;
import com.bittrade.currency.api.service.ITAdvertInfoService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tAdvertInfo" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TAdvertInfoController extends BaseController<TAdvertInfo, TAdvertInfoDTO, TAdvertInfoVO, ITAdvertInfoService> {
	
}
