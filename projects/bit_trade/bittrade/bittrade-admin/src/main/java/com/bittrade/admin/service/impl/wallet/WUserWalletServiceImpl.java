package com.bittrade.admin.service.impl.wallet;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultWUserWalletServiceImpl;
import com.bittrade.pojo.dto.WUserWalletDTO;
import com.bittrade.pojo.vo.WUserWalletVO;
import com.bittrade.pojo.model.WUserWallet;
import com.bittrade.admin.dao.wallet.IWUserWalletDAO;
import com.bittrade.admin.service.wallet.IWUserWalletService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class WUserWalletServiceImpl extends DefaultWUserWalletServiceImpl<IWUserWalletDAO, WUserWallet, WUserWalletDTO, WUserWalletVO> implements IWUserWalletService {
	
}
