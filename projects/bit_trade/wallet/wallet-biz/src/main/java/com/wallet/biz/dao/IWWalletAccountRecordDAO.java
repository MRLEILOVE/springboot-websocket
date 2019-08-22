package com.wallet.biz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.pojo.vo.RecordVO;
import com.wallet.biz.pojo.model.WWalletAccountRecord;

import java.util.List;

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
     * @param types 类型列表
     * @param currencyId 币种id
     * @return 记录列表
     */
    List<RecordVO> queryFundAccountRecord(Page<RecordVO> page, Long userId, List<Integer> types, Integer currencyId);
}
