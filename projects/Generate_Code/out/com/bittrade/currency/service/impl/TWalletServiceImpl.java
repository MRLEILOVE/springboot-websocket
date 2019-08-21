package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTWalletServiceImpl;
import com.bittrade.currency.dao.ITWalletDAO;
import com.bittrade.pojo.dto.TWalletDTO;
import com.bittrade.pojo.vo.TWalletVO;
import com.bittrade.pojo.model.TWallet;
import com.bittrade.currency.api.service.ITWalletService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TWalletServiceImpl extends DefaultTWalletServiceImpl<ITWalletDAO, TWallet, TWalletDTO, TWalletVO> implements ITWalletService {
	
}
