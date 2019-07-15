package com.test.bittrade.svc.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.framework.base.controller.BaseController;
import com.test.bittrade.api.service.ITUserService;
import com.test.bittrade.pojo.dto.TUserDTO;
import com.test.bittrade.pojo.model.TUser;
import com.test.bittrade.pojo.vo.TUserVO;

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
