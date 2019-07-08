package com.bittrade.api.service;

import com.core.framework.base.DAO.IBaseDAO;

import com.bittrade.api.__default.service.IDefaultWUserWalletService;
import com.bittrade.pojo.dto.WUserWalletDTO;
import com.bittrade.pojo.vo.WUserWalletVO;
import com.bittrade.pojo.model.WUserWallet;

/**
 * 
 * @author Administrator
 *
 */
public interface IWUserWalletService<DAO extends IBaseDAO<WUserWallet, WUserWalletDTO, WUserWalletVO>> extends IDefaultWUserWalletService<WUserWallet, WUserWalletDTO, WUserWalletVO, DAO> {
	
}
