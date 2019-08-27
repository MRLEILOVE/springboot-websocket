package com.common.bittrade.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.common.enums.FundRecordTypeEnumer;
import com.bittrade.common.enums.StatusEnumer;
import com.bittrade.pojo.dto.AccountTypeDto;
import com.bittrade.pojo.model.WCoin;
import com.bittrade.pojo.model.WWalletAccountRecord;
import com.bittrade.pojo.vo.RecordVO;
import com.common.bittrade.dao.IWCoinDAO;
import com.common.bittrade.dao.IWWalletAccountRecordDAO;
import com.common.bittrade.service.IWWalletAccountRecordService;
import com.core.tool.SnowFlake;

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
    @Autowired
    private IWCoinDAO wCoinDAO;

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

    /**
     * 查詢资金账户记录
     * @param userId 用户id
     * @param accountTypeDto 条件对象
     * @return
     */
    @Override
    public Page<RecordVO> queryFundAccountRecord(Long userId, AccountTypeDto accountTypeDto) {
        //1.提币  2.充币  3.转入 4.转出
        List<Integer> types;
        switch (accountTypeDto.getType()){
            case 1 :
                types = Arrays.asList(FundRecordTypeEnumer.withdraw.getCode());
                break;
            case 2 :
                types = Arrays.asList(FundRecordTypeEnumer.recharge.getCode());
                break;
            case 3 :
                types = Arrays.asList(FundRecordTypeEnumer.BIBI_TO_FUNDS.getCode(),FundRecordTypeEnumer.C2C_TO_FUNDS.getCode());
                break;
            case 4 :
                types = Arrays.asList(FundRecordTypeEnumer.FUNDS_TO_BIBI.getCode(),FundRecordTypeEnumer.FUNDS_TO_C2C.getCode());
                break;
            default:
                types = Arrays.asList(FundRecordTypeEnumer.recharge.getCode(),
                                      FundRecordTypeEnumer.withdraw.getCode(),
                                      FundRecordTypeEnumer.BIBI_TO_FUNDS.getCode(),
                                      FundRecordTypeEnumer.FUNDS_TO_BIBI.getCode(),
                                      FundRecordTypeEnumer.FUNDS_TO_C2C.getCode(),
                                      FundRecordTypeEnumer.C2C_TO_FUNDS.getCode());
                break;
        }

        Page<RecordVO> page = new Page<>(accountTypeDto.getCurrent(),accountTypeDto.getSize());
        List<RecordVO> list = wWalletAccountRecordDAO.queryFundAccountRecord(page,userId,types,accountTypeDto.getCurrencyId());
        page.setRecords(list);
        return page;
    }

    /**
     * 资金账户记录币种下拉框
     * @return
     */
    @Override
    public List<WCoin> queryCurrencies() {
        QueryWrapper<WCoin> qry = new QueryWrapper<>();
        qry.eq(WCoin.FieldNames.STATUS, StatusEnumer.ENABLE.getCode());
        return wCoinDAO.selectList(qry);
    }
}
