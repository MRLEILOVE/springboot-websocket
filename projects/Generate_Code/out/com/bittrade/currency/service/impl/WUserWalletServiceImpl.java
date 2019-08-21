package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultWUserWalletServiceImpl;
import com.bittrade.currency.dao.IWUserWalletDAO;
import com.bittrade.pojo.dto.WUserWalletDTO;
import com.bittrade.pojo.vo.WUserWalletVO;
import com.bittrade.pojo.model.WUserWallet;
import com.bittrade.currency.api.service.IWUserWalletService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class WUserWalletServiceImpl extends DefaultWUserWalletServiceImpl<IWUserWalletDAO, WUserWallet, WUserWalletDTO, WUserWalletVO> implements IWUserWalletService {
	
}
