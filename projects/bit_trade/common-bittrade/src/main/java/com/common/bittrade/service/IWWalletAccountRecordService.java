package com.common.bittrade.service;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bittrade.pojo.dto.AccountTypeDto;
import com.bittrade.pojo.model.WCoin;
import com.bittrade.pojo.model.WWalletAccountRecord;
import com.bittrade.pojo.vo.RecordVO;

/**
 * 
 * @author Administrator
 *
 */
public interface IWWalletAccountRecordService extends IService<WWalletAccountRecord> {

    /**
     * 资金账户流水（划入）
     * @param userId 用户id
     * @Param num 数量
     * @param currencyId 币种id
     * @param beforeAmount 原本金额
     * @param type 类型
     */
    void recordIn(Long userId, BigDecimal num, Integer currencyId, BigDecimal beforeAmount, int type);

    /**
     * 资金账户流水（划出）
     * @param userId 用户id
     * @Param num 数量
     * @param currencyId 币种id
     * @param beforeAmount 原本金额
     * @param type 类型
     */
    void recordOut(Long userId, BigDecimal num, Integer currencyId, BigDecimal beforeAmount, int type);

    /**
     * 查詢资金账户记录
     * @param userId 用户id
     * @param accountTypeDto 条件对象
     * @return
     */
    Page<RecordVO> queryFundAccountRecord(Long userId, AccountTypeDto accountTypeDto);

    /**
     * 资金账户记录币种下拉框
     */
    List<WCoin> queryCurrencies();
}
