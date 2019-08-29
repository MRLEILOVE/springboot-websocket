/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.pojo.model.TWallet;
import com.bittrade.pojo.dto.TWalletDTO;
import com.bittrade.pojo.vo.TWalletVO;
import com.bittrade.__default.service.IDefaultTWalletService;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class DefaultTWalletServiceImpl extends BaseServiceImpl<TWallet, TWalletDTO, TWalletVO, ITWalletDAO> implements IDefaultTWalletService {
public abstract class DefaultTWalletServiceImpl<DAO extends IBaseDAO<TWallet, TWalletDTO, TWalletVO>> extends BaseServiceImpl<TWallet, TWalletDTO, TWalletVO, DAO> implements IDefaultTWalletService {
	
}
