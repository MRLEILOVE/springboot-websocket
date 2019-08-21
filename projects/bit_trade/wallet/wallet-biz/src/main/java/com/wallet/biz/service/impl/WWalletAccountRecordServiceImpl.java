package com.wallet.biz.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.core.tool.SnowFlake;
import com.wallet.biz.api.service.IWWalletAccountRecordService;
import com.wallet.biz.dao.IWWalletAccountRecordDAO;
import com.wallet.biz.pojo.model.WWalletAccountRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 
 * @author Administrator
 *
 */
@Service
@Component
public class WWalletAccountRecordServiceImpl extends ServiceImpl<IWWalletAccountRecordDAO, WWalletAccountRecord> implements IWWalletAccountRecordService {
    private static final SnowFlake SNOW_FLAKE__ENTRUST	= new SnowFlake( 2, 2);
    @Autowired
    private IWWalletAccountRecordDAO wWalletAccountRecordDAO;

    /**
     * 资金账户流水（划入）
     * @param userId 用户id
     * @Param num 数量
     * @param currencyId 币种id
     * @param beforeAmount 原本金额
     * @param type 类型
     */
    @Override
    public void recordIn(Long userId, BigDecimal num,Integer currencyId, BigDecimal beforeAmount, int type) {
        WWalletAccountRecord record = WWalletAccountRecord.builder()
                .id(SNOW_FLAKE__ENTRUST.nextId())
                .userId(userId)
                .currencyId(currencyId)
                .beforeAmount(beforeAmount)
                .afterAmount(beforeAmount.add(num))
                .changeAmount(num)
                .type((byte) type)
                .createTime(LocalDateTime.now())
                .build();
        wWalletAccountRecordDAO.insert(record);
    }

    /**
     * 资金账户流水（划出）
     * @param userId 用户id
     * @Param num 数量
     * @param currencyId 币种id
     * @param beforeAmount 原本金额
     * @param type 类型
     */
    @Override
    public void recordOut(Long userId, BigDecimal num, Integer currencyId, BigDecimal beforeAmount, int type) {
        WWalletAccountRecord record = WWalletAccountRecord.builder()
                .id(SNOW_FLAKE__ENTRUST.nextId())
                .userId(userId)
                .currencyId(currencyId)
                .beforeAmount(beforeAmount)
                .afterAmount(beforeAmount.subtract(num))
                .changeAmount(num.negate())
                .type((byte) type)
                .createTime(LocalDateTime.now())
                .build();
        wWalletAccountRecordDAO.insert(record);
    }
}
