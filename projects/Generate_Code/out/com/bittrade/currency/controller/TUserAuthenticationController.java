package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.TUserAuthenticationDTO;
import com.bittrade.pojo.vo.TUserAuthenticationVO;
import com.bittrade.pojo.model.TUserAuthentication;
import com.bittrade.currency.api.service.ITUserAuthenticationService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tUserAuthentication" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TUserAuthenticationController extends BaseController<TUserAuthentication, TUserAuthenticationDTO, TUserAuthenticationVO, ITUserAuthenticationService> {
	
}
