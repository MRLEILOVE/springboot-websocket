package com.bittrade.currency.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.__default.DAO.IDefaultTWalletRecordDAO;
import com.bittrade.pojo.vo.RecordVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public interface ITWalletRecordDAO extends IDefaultTWalletRecordDAO {
    List<RecordVO> queryBiBiAccountRecord(Page<RecordVO> page,
                                          @Param("userId") Long userId,
                                          @Param("currencyId") Integer currencyId,
                                          @Param("list") List type,
                                          @Param("beginTime") Date beginTime,
                                          @Param("endTime") Date endTime);
}
