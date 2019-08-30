/**
 * This code is generated automatically. Please do not edit it.
 */
package com.bittrade.__default.service.impl;

import com.bittrade.pojo.model.WCoin;
import com.bittrade.pojo.dto.WCoinDTO;
import com.bittrade.pojo.vo.WCoinVO;
import com.bittrade.__default.service.IDefaultWCoinService;
import com.core.framework.base.DAO.IBaseDAO;
import com.core.framework.base.service.impl.BaseServiceImpl;

/**
 * 
 * @author Administrator
 *
 */
//public abstract class DefaultWCoinServiceImpl extends BaseServiceImpl<WCoin, WCoinDTO, WCoinVO, IWCoinDAO> implements IDefaultWCoinService {
public abstract class DefaultWCoinServiceImpl<DAO extends IBaseDAO<WCoin, WCoinDTO, WCoinVO>> extends BaseServiceImpl<WCoin, WCoinDTO, WCoinVO, DAO> implements IDefaultWCoinService {
	
}
