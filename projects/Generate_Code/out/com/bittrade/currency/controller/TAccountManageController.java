package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.TAccountManageDTO;
import com.bittrade.pojo.vo.TAccountManageVO;
import com.bittrade.pojo.model.TAccountManage;
import com.bittrade.currency.api.service.ITAccountManageService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tAccountManage" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TAccountManageController extends BaseController<TAccountManage, TAccountManageDTO, TAccountManageVO, ITAccountManageService> {
	
}
