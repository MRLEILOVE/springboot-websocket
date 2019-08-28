package com.common.bittrade.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.pojo.model.WWalletAccountRecord;
import com.bittrade.pojo.vo.RecordVO;

/**
 * 
 * @author Administrator
 *
 */
public interface IWWalletAccountRecordDAO extends BaseMapper<WWalletAccountRecord> {

    /**
     * 查詢资金账户记录
     * @param page 分页对象
     * @param userId 用户id
     * @param list 类型列表
     * @param currencyId 币种id
     * @return 记录列表
     */
    List<RecordVO> queryFundAccountRecord(Page<RecordVO> page,@Param("userId") Long userId, @Param("list") List<Integer> list, @Param("currencyId") Integer currencyId);

}
