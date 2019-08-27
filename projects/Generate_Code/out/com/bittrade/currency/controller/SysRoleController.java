package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.SysRoleDTO;
import com.bittrade.pojo.vo.SysRoleVO;
import com.bittrade.pojo.model.SysRole;
import com.bittrade.currency.api.service.ISysRoleService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/sysRole" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SysRoleController extends BaseController<SysRole, SysRoleDTO, SysRoleVO, ISysRoleService> {
	
}
