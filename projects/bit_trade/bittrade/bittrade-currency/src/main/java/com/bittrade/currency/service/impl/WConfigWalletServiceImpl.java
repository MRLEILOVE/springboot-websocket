package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.api.__default.DAO.IDefaultWConfigWalletDAO;
import com.bittrade.api.__default.service.impl.DefaultWConfigWalletServiceImpl;
import com.bittrade.api.service.IWConfigWalletService;
import com.bittrade.pojo.dto.WConfigWalletDTO;
import com.bittrade.pojo.model.WConfigWallet;
import com.bittrade.pojo.vo.WConfigWalletVO;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class WConfigWalletServiceImpl extends DefaultWConfigWalletServiceImpl<IDefaultWConfigWalletDAO, WConfigWallet, WConfigWalletDTO, WConfigWalletVO> implements IWConfigWalletService {
	
}
