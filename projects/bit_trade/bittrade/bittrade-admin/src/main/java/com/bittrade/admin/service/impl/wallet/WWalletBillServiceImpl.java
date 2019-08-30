package com.bittrade.admin.service.impl.wallet;

import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultWWalletBillServiceImpl;
import com.bittrade.pojo.dto.WWalletBillDTO;
import com.bittrade.pojo.vo.WWalletBillVO;
import com.bittrade.pojo.model.WWalletBill;
import com.bittrade.admin.dao.wallet.IWWalletBillDAO;
import com.bittrade.admin.service.wallet.IWWalletBillService;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class WWalletBillServiceImpl extends DefaultWWalletBillServiceImpl<IWWalletBillDAO, WWalletBill, WWalletBillDTO, WWalletBillVO> implements IWWalletBillService {
	
}
