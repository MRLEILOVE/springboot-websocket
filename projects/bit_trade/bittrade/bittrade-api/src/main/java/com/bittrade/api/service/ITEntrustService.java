package com.bittrade.api.service;

import java.math.BigDecimal;

import com.bittrade.api.__default.service.IDefaultTEntrustService;
import com.bittrade.pojo.dto.TEntrustDTO;
import com.bittrade.pojo.model.TEntrust;
import com.bittrade.pojo.vo.TEntrustVO;
import com.core.framework.base.DAO.IBaseDAO;

/**
 * 
 * @author Administrator
 *
 */
public interface ITEntrustService<DAO extends IBaseDAO<TEntrust, TEntrustDTO, TEntrustVO>> extends IDefaultTEntrustService<TEntrust, TEntrustDTO, TEntrustVO, DAO> {
	
	/**
	 * 修改信息在撮合的时候。
	 * @param successAmount 成功撮合金额
	 * @param leftCount 未成交数量
	 * @param status 状态
	 * @param ID
	 */
	void updateOnMatch(BigDecimal successAmount, BigDecimal leftCount, int status, long ID);
	
}
