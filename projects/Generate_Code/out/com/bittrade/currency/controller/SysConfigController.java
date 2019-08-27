package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.SysConfigDTO;
import com.bittrade.pojo.vo.SysConfigVO;
import com.bittrade.pojo.model.SysConfig;
import com.bittrade.currency.api.service.ISysConfigService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/sysConfig" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SysConfigController extends BaseController<SysConfig, SysConfigDTO, SysConfigVO, ISysConfigService> {
	
}
