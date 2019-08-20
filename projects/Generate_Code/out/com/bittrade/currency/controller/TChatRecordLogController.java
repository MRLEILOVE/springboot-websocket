package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.TChatRecordLogDTO;
import com.bittrade.pojo.vo.TChatRecordLogVO;
import com.bittrade.pojo.model.TChatRecordLog;
import com.bittrade.currency.api.service.ITChatRecordLogService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tChatRecordLog" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TChatRecordLogController extends BaseController<TChatRecordLog, TChatRecordLogDTO, TChatRecordLogVO, ITChatRecordLogService> {
	
}
