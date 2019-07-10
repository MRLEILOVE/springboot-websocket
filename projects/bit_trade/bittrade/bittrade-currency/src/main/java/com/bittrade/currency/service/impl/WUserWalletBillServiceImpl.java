package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultWUserWalletBillServiceImpl;
import com.bittrade.currency.api.service.IWUserWalletBillService;
import com.bittrade.currency.dao.IWUserWalletBillDAO;
import com.bittrade.pojo.dto.WUserWalletBillDTO;
import com.bittrade.pojo.vo.WUserWalletBillVO;
import com.bittrade.pojo.model.WUserWalletBill;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class WUserWalletBillServiceImpl extends DefaultWUserWalletBillServiceImpl<IWUserWalletBillDAO, WUserWalletBill, WUserWalletBillDTO, WUserWalletBillVO> implements IWUserWalletBillService {
	
}
