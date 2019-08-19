package com.bittrade.c2c.controller;

import com.bittrade.c2c.service.ITLegalCurrencyRecordService;
import com.bittrade.pojo.dto.TLegalCurrencyRecordDTO;
import com.bittrade.pojo.model.TLegalCurrencyRecord;
import com.bittrade.pojo.vo.TLegalCurrencyRecordVO;
import com.core.framework.base.controller.BaseController;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tLegalCurrencyRecord" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TLegalCurrencyRecordController extends BaseController<TLegalCurrencyRecord, TLegalCurrencyRecordDTO, TLegalCurrencyRecordVO, ITLegalCurrencyRecordService> {
	
}
