package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultWWalletAccountServiceImpl;
import com.bittrade.currency.dao.IWWalletAccountDAO;
import com.bittrade.pojo.dto.WWalletAccountDTO;
import com.bittrade.pojo.vo.WWalletAccountVO;
import com.bittrade.pojo.model.WWalletAccount;
import com.bittrade.currency.api.service.IWWalletAccountService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class WWalletAccountServiceImpl extends DefaultWWalletAccountServiceImpl<IWWalletAccountDAO, WWalletAccount, WWalletAccountDTO, WWalletAccountVO> implements IWWalletAccountService {
	
}
