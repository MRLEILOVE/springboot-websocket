package com.bittrade.api.service;

import com.core.framework.base.DAO.IBaseDAO;

import com.bittrade.api.__default.service.IDefaultTWalletTransferService;
import com.bittrade.pojo.dto.TWalletTransferDTO;
import com.bittrade.pojo.vo.TWalletTransferVO;
import com.bittrade.pojo.model.TWalletTransfer;

/**
 * 
 * @author Administrator
 *
 */
public interface ITWalletTransferService<DAO extends IBaseDAO<TWalletTransfer, TWalletTransferDTO, TWalletTransferVO>> extends IDefaultTWalletTransferService<TWalletTransfer, TWalletTransferDTO, TWalletTransferVO, DAO> {
	
}
