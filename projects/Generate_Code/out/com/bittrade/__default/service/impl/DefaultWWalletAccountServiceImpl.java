/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.pojo.model.WWalletAccount;
import com.bittrade.pojo.dto.WWalletAccountDTO;
import com.bittrade.pojo.vo.WWalletAccountVO;
import com.bittrade.__default.service.IDefaultWWalletAccountService;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class DefaultWWalletAccountServiceImpl extends BaseServiceImpl<WWalletAccount, WWalletAccountDTO, WWalletAccountVO, IWWalletAccountDAO> implements IDefaultWWalletAccountService {
public abstract class DefaultWWalletAccountServiceImpl<DAO extends IBaseDAO<WWalletAccount, WWalletAccountDTO, WWalletAccountVO>> extends BaseServiceImpl<WWalletAccount, WWalletAccountDTO, WWalletAccountVO, DAO> implements IDefaultWWalletAccountService {
	
}
