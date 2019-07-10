package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTWalletTransferServiceImpl;
import com.bittrade.currency.api.service.ITWalletTransferService;
import com.bittrade.currency.dao.ITWalletTransferDAO;
import com.bittrade.pojo.dto.TWalletTransferDTO;
import com.bittrade.pojo.vo.TWalletTransferVO;
import com.bittrade.pojo.model.TWalletTransfer;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TWalletTransferServiceImpl extends DefaultTWalletTransferServiceImpl<ITWalletTransferDAO, TWalletTransfer, TWalletTransferDTO, TWalletTransferVO> implements ITWalletTransferService {
	
}
