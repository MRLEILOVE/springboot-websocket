package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.api.__default.service.impl.DefaultWConfigWalletServiceImpl;
import com.bittrade.api.dao.IWConfigWalletDAO;
import com.bittrade.pojo.dto.WConfigWalletDTO;
import com.bittrade.pojo.vo.WConfigWalletVO;
import com.bittrade.pojo.model.WConfigWallet;
import com.bittrade.api.service.IWConfigWalletService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class WConfigWalletServiceImpl extends DefaultWConfigWalletServiceImpl<IWConfigWalletDAO, WConfigWallet, WConfigWalletDTO, WConfigWalletVO> implements IWConfigWalletService {
	
}
