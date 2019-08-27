package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.SysUserDTO;
import com.bittrade.pojo.vo.SysUserVO;
import com.bittrade.pojo.model.SysUser;
import com.bittrade.currency.api.service.ISysUserService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/sysUser" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SysUserController extends BaseController<SysUser, SysUserDTO, SysUserVO, ISysUserService> {
	
}
