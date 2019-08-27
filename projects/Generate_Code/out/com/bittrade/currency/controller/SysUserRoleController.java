package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.SysUserRoleDTO;
import com.bittrade.pojo.vo.SysUserRoleVO;
import com.bittrade.pojo.model.SysUserRole;
import com.bittrade.currency.api.service.ISysUserRoleService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/sysUserRole" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SysUserRoleController extends BaseController<SysUserRole, SysUserRoleDTO, SysUserRoleVO, ISysUserRoleService> {
	
}
