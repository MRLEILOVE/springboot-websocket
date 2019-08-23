package com.wallet.biz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.common.enums.FundCoinEnumer;
import com.bittrade.pojo.model.TCurrency;
import com.bittrade.pojo.vo.RecordVO;
import com.wallet.biz.pojo.model.WWalletAccountRecord;
import org.apache.ibatis.annotations.Param;

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
     * @param list 类型列表
     * @param currencyId 币种id
     * @return 记录列表
     */
    List<RecordVO> queryFundAccountRecord(Page<RecordVO> page,@Param("userId") Long userId, @Param("list") List<Integer> list, @Param("currencyId") Integer currencyId);

    /**
     * 资金账户记录币种下拉框
     */
    List<TCurrency> queryCurrencies(@Param("list") List list);
}
