package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.SysOperLogDTO;
import com.bittrade.pojo.vo.SysOperLogVO;
import com.bittrade.pojo.model.SysOperLog;
import com.bittrade.currency.api.service.ISysOperLogService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/sysOperLog" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SysOperLogController extends BaseController<SysOperLog, SysOperLogDTO, SysOperLogVO, ISysOperLogService> {
	
}
