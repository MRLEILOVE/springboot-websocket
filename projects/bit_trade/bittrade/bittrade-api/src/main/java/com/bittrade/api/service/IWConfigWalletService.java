package com.bittrade.api.service;

import com.core.framework.base.DAO.IBaseDAO;

import com.bittrade.api.__default.service.IDefaultWConfigWalletService;
import com.bittrade.pojo.dto.WConfigWalletDTO;
import com.bittrade.pojo.vo.WConfigWalletVO;
import com.bittrade.pojo.model.WConfigWallet;

/**
 * 
 * @author Administrator
 *
 */
public interface IWConfigWalletService<DAO extends IBaseDAO<WConfigWallet, WConfigWalletDTO, WConfigWalletVO>> extends IDefaultWConfigWalletService<WConfigWallet, WConfigWalletDTO, WConfigWalletVO, DAO> {
	
}
