package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.TEntrustRecordDTO;
import com.bittrade.pojo.vo.TEntrustRecordVO;
import com.bittrade.pojo.model.TEntrustRecord;
import com.bittrade.currency.api.service.ITEntrustRecordService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tEntrustRecord" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TEntrustRecordController extends BaseController<TEntrustRecord, TEntrustRecordDTO, TEntrustRecordVO, ITEntrustRecordService> {
	
}
