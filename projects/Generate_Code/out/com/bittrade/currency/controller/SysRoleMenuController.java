package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.SysRoleMenuDTO;
import com.bittrade.pojo.vo.SysRoleMenuVO;
import com.bittrade.pojo.model.SysRoleMenu;
import com.bittrade.currency.api.service.ISysRoleMenuService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/sysRoleMenu" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SysRoleMenuController extends BaseController<SysRoleMenu, SysRoleMenuDTO, SysRoleMenuVO, ISysRoleMenuService> {
	
}
