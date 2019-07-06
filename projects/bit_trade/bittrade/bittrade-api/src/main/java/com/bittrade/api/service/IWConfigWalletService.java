package com.bittrade.api.service;

import com.bittrade.api.__default.service.IDefaultWConfigWalletService;
import com.bittrade.api.__default.DAO.IDefaultWConfigWalletDAO;
import com.bittrade.pojo.dto.WConfigWalletDTO;
import com.bittrade.pojo.vo.WConfigWalletVO;
import com.bittrade.pojo.model.WConfigWallet;

/**
 * 
 * @author Administrator
 *
 */
public interface IWConfigWalletService extends IDefaultWConfigWalletService<WConfigWallet, WConfigWalletDTO, WConfigWalletVO, IDefaultWConfigWalletDAO> {
	
}
