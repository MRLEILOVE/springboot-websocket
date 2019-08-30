package com.common.bittrade.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.__default.DAO.IDefaultTLegalCurrencyRecordDAO;
import com.bittrade.pojo.vo.RecordVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public interface ITLegalCurrencyRecordDAO extends IDefaultTLegalCurrencyRecordDAO {

    /**
     * 查詢法币账户划转记录
     * @param page 分页对象
     * @param userId 用户id
     * @param list 类型列表
     * @param currencyId 币种id
     * @return
     */
    List<RecordVO> queryFundAccountRecord(Page<RecordVO> page, @Param("userId") Long userId, @Param("list")List<Integer> list, @Param("currencyId")Integer currencyId);
}
