package com.bittrade.currency.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.currency.dao.ITWalletTransferDAO;
import com.bittrade.pojo.dto.TWalletTransferDTO;
import com.bittrade.pojo.vo.TWalletTransferVO;
import com.bittrade.pojo.model.TWalletTransfer;
import com.bittrade.api.service.ITWalletTransferService;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tWalletTransfer" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TWalletTransferController extends BaseController<TWalletTransfer, TWalletTransferDTO, TWalletTransferVO, ITWalletTransferDAO, ITWalletTransferService<TWalletTransfer, TWalletTransferDTO, TWalletTransferVO, ITWalletTransferDAO>> {
	
}
