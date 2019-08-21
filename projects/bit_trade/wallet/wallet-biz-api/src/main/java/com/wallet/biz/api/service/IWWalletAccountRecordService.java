package com.wallet.biz.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wallet.biz.pojo.model.WWalletAccountRecord;

import java.math.BigDecimal;

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


}
