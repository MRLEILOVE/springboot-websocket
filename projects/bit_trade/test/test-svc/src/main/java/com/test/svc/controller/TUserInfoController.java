package com.test.svc.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.pojo.dto.TUserInfoDTO;
import com.test.pojo.vo.TUserInfoVO;
import com.test.pojo.model.TUserInfo;
import com.test.api.service.ITUserInfoService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tUserInfo" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TUserInfoController extends BaseController<TUserInfo, TUserInfoDTO, TUserInfoVO, ITUserInfoService> {
	
}
