package com.bittrade.admin.controller.wallet;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.admin.service.wallet.ITWalletRecordService;
import com.bittrade.pojo.dto.TWalletRecordDTO;
import com.bittrade.pojo.vo.TWalletRecordVO;
import com.bittrade.pojo.model.TWalletRecord;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tWalletRecord" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TWalletRecordController extends BaseController<TWalletRecord, TWalletRecordDTO, TWalletRecordVO, ITWalletRecordService> {
	
}
