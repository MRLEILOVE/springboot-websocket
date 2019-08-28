package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.TLoginLogDTO;
import com.bittrade.pojo.vo.TLoginLogVO;
import com.bittrade.pojo.model.TLoginLog;
import com.bittrade.currency.api.service.ITLoginLogService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tLoginLog" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TLoginLogController extends BaseController<TLoginLog, TLoginLogDTO, TLoginLogVO, ITLoginLogService> {
	
}
