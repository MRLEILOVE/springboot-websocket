package com.wallet.biz.service;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.core.common.DTO.ReturnDTO;
import com.wallet.biz.dto.AccountNameDto;
import com.wallet.biz.dto.AccountTypeDto;
import com.wallet.biz.dto.TransferDto;
import com.wallet.biz.pojo.TAccountConfig;
import com.wallet.biz.pojo.TAccountManage;
import com.wallet.biz.pojo.TWalletFundAccount;
import com.wallet.biz.vo.AssetsVO;
import com.wallet.biz.vo.ConversionVo;
import com.wallet.biz.vo.RecordVO;

public interface ITwalletFundAccountService extends IService<TWalletFundAccount> {
    /**
     * 根据账户名称查询可以划转的方向
     */
    ReturnDTO<List<TAccountManage>> queryAccountDirectionList(AccountNameDto accountNameDto);

    /**
     * 查询两个账户共同的币种
     * @param accountId1 账户1id
     * @param accountId2 账户2id
     */
    List<TAccountConfig> commonCurrency(Integer accountId1, Integer accountId2);

    /**
     * 查找币种列表
     */
    List<TAccountConfig> currencyList();

    /**
     * 为用户创建资金账户
     * @param userId 用户id
     * @param currency 币种
     */
    TWalletFundAccount createFundAccount(Long userId, String currency);

    /**
     * 查詢资金账户记录
     * @param userId 用户id
     * @param accountTypeDto 条件对象
     * @return
     */
    Page<RecordVO> queryFundAccountRecord(Long userId, AccountTypeDto accountTypeDto);

    /**
     * 查询用户的资金账户钱包列表
     * @param userId 用户id
     * @return
     */
    List<AssetsVO> detail(Long userId);

    /**
     * 查询用户的资金账户总资金折合
     * @param userId
     * @return
     */
    ConversionVo totalConversion(Long userId);

    /**
     * 获取划转类型
     * @param accountInId 入账钱包id
     * @param accountOutId 出账钱包id
     * @return 划转类型
     */
    Integer getType(Integer accountInId, Integer accountOutId);
}
