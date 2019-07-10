package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultWConfigWalletServiceImpl;
import com.bittrade.currency.api.service.IWConfigWalletService;
import com.bittrade.currency.dao.IWConfigWalletDAO;
import com.bittrade.pojo.dto.WConfigWalletDTO;
import com.bittrade.pojo.vo.WConfigWalletVO;
import com.bittrade.pojo.model.WConfigWallet;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class WConfigWalletServiceImpl extends DefaultWConfigWalletServiceImpl<IWConfigWalletDAO, WConfigWallet, WConfigWalletDTO, WConfigWalletVO> implements IWConfigWalletService {
	
}
