package com.bittrade.admin.service.impl.wallet;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTWalletServiceImpl;
import com.bittrade.pojo.dto.TWalletDTO;
import com.bittrade.pojo.vo.TWalletVO;
import com.bittrade.pojo.model.TWallet;
import com.bittrade.admin.dao.wallet.ITWalletDAO;
import com.bittrade.admin.service.wallet.ITWalletService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TWalletServiceImpl extends DefaultTWalletServiceImpl<ITWalletDAO, TWallet, TWalletDTO, TWalletVO> implements ITWalletService {
	
}
