package com.bittrade.api.service;

import com.core.framework.base.DAO.IBaseDAO;

import com.bittrade.api.__default.service.IDefaultWWithdrawWalletBillService;
import com.bittrade.pojo.dto.WWithdrawWalletBillDTO;
import com.bittrade.pojo.vo.WWithdrawWalletBillVO;
import com.bittrade.pojo.model.WWithdrawWalletBill;

/**
 * 
 * @author Administrator
 *
 */
public interface IWWithdrawWalletBillService<DAO extends IBaseDAO<WWithdrawWalletBill, WWithdrawWalletBillDTO, WWithdrawWalletBillVO>> extends IDefaultWWithdrawWalletBillService<WWithdrawWalletBill, WWithdrawWalletBillDTO, WWithdrawWalletBillVO, DAO> {
	
}
