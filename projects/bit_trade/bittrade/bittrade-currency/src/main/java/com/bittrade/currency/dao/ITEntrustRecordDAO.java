package com.bittrade.currency.dao;

import com.bittrade.api.__default.DAO.IDefaultTEntrustRecordDAO;
import com.bittrade.pojo.vo.TEntrustRecordVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
@Repository
public interface ITEntrustRecordDAO extends IDefaultTEntrustRecordDAO {

    /**
     * 查询用户成交记录
     */
    List<TEntrustRecordVO> queryDealEntrustByUserId(String userId);
}
