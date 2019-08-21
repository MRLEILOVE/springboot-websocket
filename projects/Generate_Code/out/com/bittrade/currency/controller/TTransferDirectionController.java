package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.TTransferDirectionDTO;
import com.bittrade.pojo.vo.TTransferDirectionVO;
import com.bittrade.pojo.model.TTransferDirection;
import com.bittrade.currency.api.service.ITTransferDirectionService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tTransferDirection" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TTransferDirectionController extends BaseController<TTransferDirection, TTransferDirectionDTO, TTransferDirectionVO, ITTransferDirectionService> {
	
}
