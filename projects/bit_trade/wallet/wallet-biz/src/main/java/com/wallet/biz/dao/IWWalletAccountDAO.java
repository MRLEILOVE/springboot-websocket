package com.wallet.biz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wallet.biz.pojo.model.WWalletAccount;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * 
 * @author Administrator
 *
 */
public interface IWWalletAccountDAO extends BaseMapper<WWalletAccount> {

    /**
     * 资金账户入账
     * @param id 资金账户id
     * @param num 数量
     * @Param version 版本号
     * @return
     */
    Integer fundAccountIn(@Param("id") Long id, @Param("num") BigDecimal num, @Param("version") Integer version);

    /**
     * 资金账户出账
     * @param id 资金账户id
     * @param num 数量
     * @Param version 版本号
     * @return
     */
    Integer fundAccountOut(@Param("id") Long id, @Param("num") BigDecimal num, @Param("version") Integer version);
}
