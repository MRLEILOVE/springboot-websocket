package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.SysJobLogDTO;
import com.bittrade.pojo.vo.SysJobLogVO;
import com.bittrade.pojo.model.SysJobLog;
import com.bittrade.currency.api.service.ISysJobLogService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/sysJobLog" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SysJobLogController extends BaseController<SysJobLog, SysJobLogDTO, SysJobLogVO, ISysJobLogService> {
	
}
