/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.pojo.model.TUserAccount;
import com.bittrade.pojo.dto.TUserAccountDTO;
import com.bittrade.pojo.vo.TUserAccountVO;
import com.bittrade.__default.service.IDefaultTUserAccountService;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class DefaultTUserAccountServiceImpl extends BaseServiceImpl<TUserAccount, TUserAccountDTO, TUserAccountVO, ITUserAccountDAO> implements IDefaultTUserAccountService {
public abstract class DefaultTUserAccountServiceImpl<DAO extends IBaseDAO<TUserAccount, TUserAccountDTO, TUserAccountVO>> extends BaseServiceImpl<TUserAccount, TUserAccountDTO, TUserAccountVO, DAO> implements IDefaultTUserAccountService {
	
}
