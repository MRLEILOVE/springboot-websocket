package com.walletbiz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.walletbiz.pojo.TWalletFundAccountRecord;
import com.walletbiz.vo.RecordVO;
import com.walletbiz.vo.TopNVO;
import com.walletbiz.vo.TransferRecordVO;

public interface TwalletFundAccountRecordMapper extends BaseMapper<TWalletFundAccountRecord> {
    /**
     * 查询资金账号划转记录
     */
    List<TransferRecordVO> transferRecord(Page<TransferRecordVO> page, @Param("userId") Long userId);

    /**
     * 查询topN
     */
    List<TopNVO> topN(Page<RecordVO> page, @Param("userId")Long userId, @Param("productType")Integer productType, @Param("currency") String currency);

    /**
     * 通过id获取资金账户流水
     */
    RecordVO getRecordById(@Param("id") String id);

    /**
     * 查询记录
     * @param page 分页对象
     * @param userId 用户id
     * @param currency 币种
     * @param list 1:提币 2：充值 3，5：转出资金账户  4，6：转入资金账户
     * @return
     */
    List<RecordVO> selectRecord(Page<RecordVO> page, @Param("userId") Long userId, @Param("currency") String currency, @Param("list") List<Integer> list);

    /**
     * 查詢资金账户记录
     * @param page 分页对象
     * @param userId 用户id
     * @param types 类型列表
     * @param currency 币种
     * @return
     */
    List<RecordVO> queryFundAccountRecord(Page<RecordVO> page, @Param("userId") Long userId, @Param("list")List<Integer> types, @Param("currency") String currency);
}
