package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.SysDictTypeDTO;
import com.bittrade.pojo.vo.SysDictTypeVO;
import com.bittrade.pojo.model.SysDictType;
import com.bittrade.currency.api.service.ISysDictTypeService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/sysDictType" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SysDictTypeController extends BaseController<SysDictType, SysDictTypeDTO, SysDictTypeVO, ISysDictTypeService> {
	
}
