package com.bittrade.admin.service.impl.wallet;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTWalletTransferServiceImpl;
import com.bittrade.pojo.dto.TWalletTransferDTO;
import com.bittrade.pojo.vo.TWalletTransferVO;
import com.bittrade.pojo.model.TWalletTransfer;
import com.bittrade.admin.dao.wallet.ITWalletTransferDAO;
import com.bittrade.admin.service.wallet.ITWalletTransferService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TWalletTransferServiceImpl extends DefaultTWalletTransferServiceImpl<ITWalletTransferDAO, TWalletTransfer, TWalletTransferDTO, TWalletTransferVO> implements ITWalletTransferService {
	
}
