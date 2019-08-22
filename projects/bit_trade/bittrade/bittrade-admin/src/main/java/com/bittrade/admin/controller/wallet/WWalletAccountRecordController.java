package com.bittrade.admin.controller.wallet;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.admin.service.wallet.IWWalletAccountRecordService;
import com.bittrade.pojo.dto.WWalletAccountRecordDTO;
import com.bittrade.pojo.vo.WWalletAccountRecordVO;
import com.bittrade.pojo.model.WWalletAccountRecord;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/wWalletAccountRecord" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WWalletAccountRecordController extends BaseController<WWalletAccountRecord, WWalletAccountRecordDTO, WWalletAccountRecordVO, IWWalletAccountRecordService> {
	
}
