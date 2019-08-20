package com.walletbiz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.walletbiz.pojo.GBetaAccount;


/**
 * <p>
 * 我的beta资产表 Mapper 接口
 * </p>
 *
 * @author helen
 * @since 2019-04-11
 */
public interface GBetaAccountMapper extends BaseMapper<GBetaAccount> {

    void updateBetaAccountById(GBetaAccount accountVo);
    /**
     * beta账户入账
     */
    Integer inMoney(GBetaAccount betaAccount);
    /**
     * beta账户出账
     */
    Integer outMoney(GBetaAccount betaAccount);

    /**
     * 查询用户beta账户列表（关联账户-币种 中间表 做成可配置）
     */
    List<GBetaAccount> selectUserBetaAccount(@Param("userId") Long userId);
}
