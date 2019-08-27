package com.bittrade.currency.service.impl;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultWWalletBillServiceImpl;
import com.bittrade.currency.dao.IWWalletBillDAO;
import com.bittrade.pojo.dto.WWalletBillDTO;
import com.bittrade.pojo.vo.WWalletBillVO;
import com.bittrade.pojo.model.WWalletBill;
import com.bittrade.currency.api.service.IWWalletBillService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class WWalletBillServiceImpl extends DefaultWWalletBillServiceImpl<IWWalletBillDAO, WWalletBill, WWalletBillDTO, WWalletBillVO> implements IWWalletBillService {
	
}
