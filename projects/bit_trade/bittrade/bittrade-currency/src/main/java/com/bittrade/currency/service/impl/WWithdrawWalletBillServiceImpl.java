package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.api.__default.service.impl.DefaultWWithdrawWalletBillServiceImpl;
import com.bittrade.currency.dao.IWWithdrawWalletBillDAO;
import com.bittrade.pojo.dto.WWithdrawWalletBillDTO;
import com.bittrade.pojo.vo.WWithdrawWalletBillVO;
import com.bittrade.pojo.model.WWithdrawWalletBill;
import com.bittrade.api.service.IWWithdrawWalletBillService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class WWithdrawWalletBillServiceImpl extends DefaultWWithdrawWalletBillServiceImpl<IWWithdrawWalletBillDAO, WWithdrawWalletBill, WWithdrawWalletBillDTO, WWithdrawWalletBillVO> implements IWWithdrawWalletBillService<IWWithdrawWalletBillDAO> {
	
}
