package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.TUserAccountDTO;
import com.bittrade.pojo.vo.TUserAccountVO;
import com.bittrade.pojo.model.TUserAccount;
import com.bittrade.currency.api.service.ITUserAccountService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tUserAccount" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TUserAccountController extends BaseController<TUserAccount, TUserAccountDTO, TUserAccountVO, ITUserAccountService> {
	
}
