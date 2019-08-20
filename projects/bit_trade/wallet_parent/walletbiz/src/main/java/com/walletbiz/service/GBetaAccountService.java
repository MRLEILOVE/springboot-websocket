package com.walletbiz.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.walletbiz.pojo.GBetaAccount;
import com.walletbiz.vo.BetaTotal;

public interface GBetaAccountService extends IService<GBetaAccount> {
    /**
     * 用户币种列表及总余额
     */
    List<BetaTotal> selectTotal(Long userId);

    /**
     * 查询用户beta账户列表（关联账户-币种 中间表 做成可配置）
     * @param userId
     * @return
     */
    List<GBetaAccount> selectUserBetaAccount(Long userId);
}
