package com.bittrade.admin.service.impl.wallet;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultWWalletAccountServiceImpl;
import com.bittrade.pojo.dto.WWalletAccountDTO;
import com.bittrade.pojo.vo.WWalletAccountVO;
import com.bittrade.pojo.model.WWalletAccount;
import com.bittrade.admin.dao.wallet.IWWalletAccountDAO;
import com.bittrade.admin.service.wallet.IWWalletAccountService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class WWalletAccountServiceImpl extends DefaultWWalletAccountServiceImpl<IWWalletAccountDAO, WWalletAccount, WWalletAccountDTO, WWalletAccountVO> implements IWWalletAccountService {
	
}
