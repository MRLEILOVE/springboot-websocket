package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.pojo.dto.TRecordLogDTO;
import com.bittrade.pojo.vo.TRecordLogVO;
import com.bittrade.pojo.model.TRecordLog;
import com.bittrade.currency.api.service.ITRecordLogService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tRecordLog" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TRecordLogController extends BaseController<TRecordLog, TRecordLogDTO, TRecordLogVO, ITRecordLogService> {
	
}
