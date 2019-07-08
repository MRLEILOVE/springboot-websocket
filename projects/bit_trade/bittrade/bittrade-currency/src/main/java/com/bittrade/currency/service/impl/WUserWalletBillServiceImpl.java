package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.api.__default.service.impl.DefaultWUserWalletBillServiceImpl;
import com.bittrade.currency.dao.IWUserWalletBillDAO;
import com.bittrade.pojo.dto.WUserWalletBillDTO;
import com.bittrade.pojo.vo.WUserWalletBillVO;
import com.bittrade.pojo.model.WUserWalletBill;
import com.bittrade.api.service.IWUserWalletBillService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class WUserWalletBillServiceImpl extends DefaultWUserWalletBillServiceImpl<IWUserWalletBillDAO, WUserWalletBill, WUserWalletBillDTO, WUserWalletBillVO> implements IWUserWalletBillService<IWUserWalletBillDAO> {
	
}
