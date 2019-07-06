package com.test.bittrade.svc.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.bittrade.svc.api.dao.ITUserDAO;
import com.test.bittrade.svc.pojo.dto.TUserDTO;
import com.test.bittrade.svc.pojo.vo.TUserVO;
import com.test.bittrade.svc.pojo.model.TUser;
import com.test.bittrade.svc.api.service.ITUserService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tUser" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TUserController extends BaseController<TUser, TUserDTO, TUserVO, ITUserDAO, ITUserService> {
	
}
