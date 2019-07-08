package com.bittrade.api.service;

import com.core.framework.base.DAO.IBaseDAO;

import com.bittrade.api.__default.service.IDefaultWUserWalletBillService;
import com.bittrade.pojo.dto.WUserWalletBillDTO;
import com.bittrade.pojo.vo.WUserWalletBillVO;
import com.bittrade.pojo.model.WUserWalletBill;

/**
 * 
 * @author Administrator
 *
 */
public interface IWUserWalletBillService<DAO extends IBaseDAO<WUserWalletBill, WUserWalletBillDTO, WUserWalletBillVO>> extends IDefaultWUserWalletBillService<WUserWalletBill, WUserWalletBillDTO, WUserWalletBillVO, DAO> {
	
}
