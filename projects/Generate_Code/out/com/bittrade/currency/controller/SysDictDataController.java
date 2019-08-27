package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.SysDictDataDTO;
import com.bittrade.pojo.vo.SysDictDataVO;
import com.bittrade.pojo.model.SysDictData;
import com.bittrade.currency.api.service.ISysDictDataService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/sysDictData" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SysDictDataController extends BaseController<SysDictData, SysDictDataDTO, SysDictDataVO, ISysDictDataService> {
	
}
