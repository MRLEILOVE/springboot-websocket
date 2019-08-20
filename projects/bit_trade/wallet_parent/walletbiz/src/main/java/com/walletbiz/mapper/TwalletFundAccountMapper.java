package com.walletbiz.mapper;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.walletbiz.pojo.TWalletFundAccount;


public interface TwalletFundAccountMapper extends BaseMapper<TWalletFundAccount> {
    /**
     * 资金账户改变金额
     */
    Integer changeMoney(TWalletFundAccount walletFundAccount);

    /**
     * 冻结划转金额
     * @param id 钱包id
     * @param num 数量
     * @param version 版本号
     * @return 修改成功数据条数
     */
    Integer modifyTransferFrozen(@Param("id") Integer id, @Param("num") BigDecimal num, @Param("version") Integer version);

    /**
     * 释放划转解冻
     * @param id 钱包id
     * @param num 数量
     * @param version 版本号
     * @return
     */
    Integer decreaseFreeze(@Param("id") Integer id,@Param("num") BigDecimal num,@Param("version") Integer version);
}
