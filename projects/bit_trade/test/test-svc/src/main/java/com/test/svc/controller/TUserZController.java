package com.test.svc.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.pojo.dto.TUserZDTO;
import com.test.pojo.vo.TUserZVO;
import com.test.pojo.model.TUserZ;
import com.test.api.service.ITUserZService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tUserZ" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TUserZController extends BaseController<TUserZ, TUserZDTO, TUserZVO, ITUserZService> {
	
}
