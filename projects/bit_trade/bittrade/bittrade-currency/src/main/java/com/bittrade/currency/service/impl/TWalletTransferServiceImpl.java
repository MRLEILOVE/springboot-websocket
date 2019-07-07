package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.api.__default.service.impl.DefaultTWalletTransferServiceImpl;
import com.bittrade.currency.dao.ITWalletTransferDAO;
import com.bittrade.pojo.dto.TWalletTransferDTO;
import com.bittrade.pojo.vo.TWalletTransferVO;
import com.bittrade.pojo.model.TWalletTransfer;
import com.bittrade.api.service.ITWalletTransferService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TWalletTransferServiceImpl extends DefaultTWalletTransferServiceImpl<ITWalletTransferDAO, TWalletTransfer, TWalletTransferDTO, TWalletTransferVO> implements ITWalletTransferService<TWalletTransfer, TWalletTransferDTO, TWalletTransferVO, ITWalletTransferDAO> {
	
}
