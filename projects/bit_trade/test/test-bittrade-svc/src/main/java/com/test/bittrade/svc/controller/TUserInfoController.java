package com.test.bittrade.svc.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.framework.base.controller.BaseController;
import com.test.bittrade.api.service.ITUserInfoService;
import com.test.bittrade.pojo.dto.TUserInfoDTO;
import com.test.bittrade.pojo.model.TUserInfo;
import com.test.bittrade.pojo.vo.TUserInfoVO;
import com.test.bittrade.svc.dao.ITUserInfoDAO;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tUserInfo" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TUserInfoController extends BaseController<TUserInfo, TUserInfoDTO, TUserInfoVO, ITUserInfoDAO, ITUserInfoService<TUserInfo, TUserInfoDTO, TUserInfoVO, ITUserInfoDAO>> {
	
}
