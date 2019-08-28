package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.SysRoleDeptDTO;
import com.bittrade.pojo.vo.SysRoleDeptVO;
import com.bittrade.pojo.model.SysRoleDept;
import com.bittrade.currency.api.service.ISysRoleDeptService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/sysRoleDept" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SysRoleDeptController extends BaseController<SysRoleDept, SysRoleDeptDTO, SysRoleDeptVO, ISysRoleDeptService> {
	
}
