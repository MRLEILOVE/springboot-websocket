package com.bittrade.api.service;

import com.core.framework.base.DAO.IBaseDAO;

import com.bittrade.api.__default.service.IDefaultTEntrustService;
import com.bittrade.pojo.dto.TEntrustDTO;
import com.bittrade.pojo.vo.TEntrustVO;
import com.bittrade.pojo.model.TEntrust;

/**
 * 
 * @author Administrator
 *
 */
public interface ITEntrustService<DAO extends IBaseDAO<TEntrust, TEntrustDTO, TEntrustVO>> extends IDefaultTEntrustService<TEntrust, TEntrustDTO, TEntrustVO, DAO> {
	
}
