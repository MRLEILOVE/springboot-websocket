package com.bittrade.currency.dao;

import java.util.List;

import com.bittrade.__default.DAO.IDefaultTEntrustRecordDAO;
import com.bittrade.pojo.vo.TEntrustRecordVO;

/**
 * 
 * @author Administrator
 *
 */
public interface ITEntrustRecordDAO extends IDefaultTEntrustRecordDAO {

	/**
	 * 查询用户成交记录
	 */
	List<TEntrustRecordVO> queryDealEntrustByUserId(String userId);

}
