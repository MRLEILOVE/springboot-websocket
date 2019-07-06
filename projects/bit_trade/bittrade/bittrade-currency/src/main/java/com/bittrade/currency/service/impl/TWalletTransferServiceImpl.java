package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.api.__default.DAO.IDefaultTWalletTransferDAO;
import com.bittrade.api.__default.service.impl.DefaultTWalletTransferServiceImpl;
import com.bittrade.api.service.ITWalletTransferService;
import com.bittrade.pojo.dto.TWalletTransferDTO;
import com.bittrade.pojo.model.TWalletTransfer;
import com.bittrade.pojo.vo.TWalletTransferVO;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TWalletTransferServiceImpl extends DefaultTWalletTransferServiceImpl<IDefaultTWalletTransferDAO, TWalletTransfer, TWalletTransferDTO, TWalletTransferVO> implements ITWalletTransferService {
	
}
