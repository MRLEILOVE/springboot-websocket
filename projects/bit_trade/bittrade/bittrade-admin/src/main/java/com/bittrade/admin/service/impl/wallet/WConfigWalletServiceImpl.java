package com.bittrade.admin.service.impl.wallet;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultWConfigWalletServiceImpl;
import com.bittrade.pojo.dto.WConfigWalletDTO;
import com.bittrade.pojo.vo.WConfigWalletVO;
import com.bittrade.pojo.model.WConfigWallet;
import com.bittrade.admin.dao.wallet.IWConfigWalletDAO;
import com.bittrade.admin.service.wallet.IWConfigWalletService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class WConfigWalletServiceImpl extends DefaultWConfigWalletServiceImpl<IWConfigWalletDAO, WConfigWallet, WConfigWalletDTO, WConfigWalletVO> implements IWConfigWalletService {
	
}
