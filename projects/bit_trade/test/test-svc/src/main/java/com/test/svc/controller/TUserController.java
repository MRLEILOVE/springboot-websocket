package com.test.svc.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.pojo.dto.TUserDTO;
import com.test.pojo.vo.TUserVO;
import com.test.pojo.model.TUser;
import com.test.api.service.ITUserService;
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
