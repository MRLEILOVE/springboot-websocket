package com.bittrade.admin.controller.wallet;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.admin.service.wallet.ITUserCapitalAccountRecordService;
import com.bittrade.pojo.dto.TUserCapitalAccountRecordDTO;
import com.bittrade.pojo.vo.TUserCapitalAccountRecordVO;
import com.bittrade.pojo.model.TUserCapitalAccountRecord;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tUserCapitalAccountRecord" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TUserCapitalAccountRecordController extends BaseController<TUserCapitalAccountRecord, TUserCapitalAccountRecordDTO, TUserCapitalAccountRecordVO, ITUserCapitalAccountRecordService> {
	
}
