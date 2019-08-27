package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.SysJobDTO;
import com.bittrade.pojo.vo.SysJobVO;
import com.bittrade.pojo.model.SysJob;
import com.bittrade.currency.api.service.ISysJobService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/sysJob" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SysJobController extends BaseController<SysJob, SysJobDTO, SysJobVO, ISysJobService> {
	
}