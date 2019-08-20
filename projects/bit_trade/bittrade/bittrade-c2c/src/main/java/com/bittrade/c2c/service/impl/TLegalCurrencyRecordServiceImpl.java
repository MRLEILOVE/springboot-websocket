package com.bittrade.c2c.service.impl;

import com.bittrade.__default.service.impl.DefaultTLegalCurrencyRecordServiceImpl;
import com.bittrade.c2c.dao.ITLegalCurrencyRecordDAO;
import com.bittrade.c2c.service.ITLegalCurrencyRecordService;
import com.bittrade.pojo.dto.TLegalCurrencyRecordDTO;
import com.bittrade.pojo.model.TLegalCurrencyCoin;
import com.bittrade.pojo.model.TLegalCurrencyRecord;
import com.bittrade.pojo.vo.TLegalCurrencyRecordVO;
import com.core.tool.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 
 * @author Administrator
 *
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class TLegalCurrencyRecordServiceImpl extends DefaultTLegalCurrencyRecordServiceImpl<ITLegalCurrencyRecordDAO, TLegalCurrencyRecord, TLegalCurrencyRecordDTO, TLegalCurrencyRecordVO> implements ITLegalCurrencyRecordService {
    private static final SnowFlake SNOW_FLAKE__ENTRUST	= new SnowFlake( 2, 2);
    @Autowired
    private ITLegalCurrencyRecordDAO legalCurrencyRecordDAO;

    /**
     * c2c钱包流水（划入）
     * @param userId 用户id
     * @param coin 币种
     * @param beforeAmount 转账前金额
     * @param type 划转类型
     * @param num
     * @return
     */
    @Override
    public Integer c2cRecordIn(Long userId, TLegalCurrencyCoin coin, BigDecimal beforeAmount, int type, BigDecimal num) {
        TLegalCurrencyRecord record = TLegalCurrencyRecord.builder()
                .id(SNOW_FLAKE__ENTRUST.nextId())
                .userId(userId)
                .coinId(coin.getId().intValue())
                .beforeAmount(beforeAmount)
                .changeAmount(num)
                .afterAmount(num.add(beforeAmount))
                .type(type)
                .createTime(LocalDateTime.now())
                .build();
        int result = legalCurrencyRecordDAO.add(record);
        return result;
    }

    /**
     * c2c钱包流水（划出）
     * @param userId 用户id
     * @param coin 币种
     * @param beforeAmount 转账前金额
     * @param type 类型
     * @param num 数量
     * @return
     */
    @Override
    public Integer c2cRecordOut(Long userId, TLegalCurrencyCoin coin, BigDecimal beforeAmount, int type, BigDecimal num) {
        TLegalCurrencyRecord record = TLegalCurrencyRecord.builder()
                .id(SNOW_FLAKE__ENTRUST.nextId())
                .userId(userId)
                .coinId(coin.getId().intValue())
                .beforeAmount(beforeAmount)
                .changeAmount(num.negate())
                .afterAmount(beforeAmount.subtract(num))
                .type(type)
                .createTime(LocalDateTime.now())
                .build();
        int result = legalCurrencyRecordDAO.add(record);
        return result;
    }
}
