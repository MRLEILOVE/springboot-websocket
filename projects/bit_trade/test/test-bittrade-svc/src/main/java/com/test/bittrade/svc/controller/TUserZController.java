package com.test.bittrade.svc.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.framework.base.controller.BaseController;
import com.test.bittrade.api.service.ITUserZService;
import com.test.bittrade.pojo.dto.TUserZDTO;
import com.test.bittrade.pojo.model.TUserZ;
import com.test.bittrade.pojo.vo.TUserZVO;
import com.test.bittrade.svc.dao.ITUserZDAO;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tUserZ" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TUserZController extends BaseController<TUserZ, TUserZDTO, TUserZVO, ITUserZDAO, ITUserZService<TUserZ, TUserZDTO, TUserZVO, ITUserZDAO>> {
	
}
