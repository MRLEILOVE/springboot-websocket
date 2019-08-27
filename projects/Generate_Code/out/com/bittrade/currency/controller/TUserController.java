package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.TUserDTO;
import com.bittrade.pojo.vo.TUserVO;
import com.bittrade.pojo.model.TUser;
import com.bittrade.currency.api.service.ITUserService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tUser" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TUserController extends BaseController<TUser, TUserDTO, TUserVO, ITUserService> {
	
}
