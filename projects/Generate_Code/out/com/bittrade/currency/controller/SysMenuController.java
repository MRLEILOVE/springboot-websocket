package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.SysMenuDTO;
import com.bittrade.pojo.vo.SysMenuVO;
import com.bittrade.pojo.model.SysMenu;
import com.bittrade.currency.api.service.ISysMenuService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/sysMenu" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SysMenuController extends BaseController<SysMenu, SysMenuDTO, SysMenuVO, ISysMenuService> {
	
}
