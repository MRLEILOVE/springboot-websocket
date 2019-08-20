package com.bittrade.c2c.service;

import com.bittrade.__default.service.IDefaultTLegalCurrencyRecordService;
import com.bittrade.pojo.dto.TLegalCurrencyRecordDTO;
import com.bittrade.pojo.model.TLegalCurrencyCoin;
import com.bittrade.pojo.vo.TLegalCurrencyRecordVO;
import com.bittrade.pojo.model.TLegalCurrencyRecord;

import java.math.BigDecimal;

/**
 * 
 * @author Administrator
 *
 */
public interface ITLegalCurrencyRecordService extends IDefaultTLegalCurrencyRecordService<TLegalCurrencyRecord, TLegalCurrencyRecordDTO, TLegalCurrencyRecordVO> {

    /**
     * c2c钱包流水（划入）
     * @param userId 用户id
     * @param coin 币种
     * @param beforeAmount 转账前金额
     * @param type 类型
     * @param num 数量
     * @return
     */
    Integer c2cRecordIn(Long userId, TLegalCurrencyCoin coin, BigDecimal beforeAmount, int type, BigDecimal num);

    /**
     * c2c钱包流水（划出）
     * @param userId 用户id
     * @param coin 币种
     * @param beforeAmount 转账前金额
     * @param type 类型
     * @param num 数量
     * @return
     */
    Integer c2cRecordOut(Long userId, TLegalCurrencyCoin coin, BigDecimal beforeAmount, int type, BigDecimal num);
}