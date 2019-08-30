package com.common.bittrade.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.__default.service.impl.DefaultTLegalCurrencyRecordServiceImpl;
import com.bittrade.common.enums.LegalRecordStatusEnumer;
import com.bittrade.pojo.dto.AccountTypeDto;
import com.bittrade.pojo.dto.TLegalCurrencyRecordDTO;
import com.bittrade.pojo.model.TLegalCurrencyCoin;
import com.bittrade.pojo.model.TLegalCurrencyRecord;
import com.bittrade.pojo.vo.RecordVO;
import com.bittrade.pojo.vo.TLegalCurrencyRecordVO;
import com.common.bittrade.dao.ITLegalCurrencyRecordDAO;
import com.common.bittrade.service.ITLegalCurrencyRecordService;
import com.core.tool.SnowFlake;

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

    /**
     * 查詢法币账户划转记录
     * @param userId 用户id
     * @param accountTypeDto 请求对象
     * @return
     */
    @Override
    public Page<RecordVO> queryAccountRecord(Long userId, AccountTypeDto accountTypeDto) {
        //1-转入 2-转出
        List<Integer> types;
        switch (accountTypeDto.getType()){
            case 1:
                types = Arrays.asList(LegalRecordStatusEnumer.FUNDS_TO_LEGAL.getCode(),LegalRecordStatusEnumer.FUNDS_TO_LEGAL.getCode());
                break;
            case 2:
                types = Arrays.asList(LegalRecordStatusEnumer.LEGAL_TO_BIBI.getCode(),LegalRecordStatusEnumer.LEGAL_TO_FUNDS.getCode());
                break;
            default:
                types = Arrays.asList(LegalRecordStatusEnumer.LEGAL_TO_BIBI.getCode(),
                                      LegalRecordStatusEnumer.LEGAL_TO_FUNDS.getCode(),
                                      LegalRecordStatusEnumer.FUNDS_TO_LEGAL.getCode(),
                                      LegalRecordStatusEnumer.BIBI_TO_LEGAL.getCode());
                break;
        }
        Page<RecordVO> page = new Page<>(accountTypeDto.getCurrent(),accountTypeDto.getSize());
        List<RecordVO> list = legalCurrencyRecordDAO.queryFundAccountRecord(page,userId,types,accountTypeDto.getCurrencyId());
        page.setRecords(list);
        return page;
    }
}
