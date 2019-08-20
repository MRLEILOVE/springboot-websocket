package com.walletbiz.service;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.core.common.DTO.ReturnDTO;
import com.walletbiz.dto.AccountNameDto;
import com.walletbiz.dto.AccountTypeDto;
import com.walletbiz.dto.TransferDto;
import com.walletbiz.pojo.TAccountConfig;
import com.walletbiz.pojo.TAccountManage;
import com.walletbiz.pojo.TWalletFundAccount;
import com.walletbiz.vo.ConversionVo;
import com.walletbiz.vo.RecordVO;

public interface ITwalletFundAccountService extends IService<TWalletFundAccount> {
    /**
     * 查询用户的资金账户
     */
//    AccountVO queryAccountByUserId(Integer userId);

    /**
     * 资金划转
     */
    ReturnDTO<String> transferOfFunds(TransferDto transferDto) throws Exception;

    /**
     * 查詢账户余额
     */
    String amountBalance(Long userId, Integer accountType, String currency);

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
     * 查詢划转记录
     */
//    Page<RecordVO> queryTransferRecord(Long userId, AccountTypeDto accountTypeDto);

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
     * 账户入账（资金划转，被远程调用）
     * @param userId 用户id
     * @param currency 币种名称
     * @param num 数量
     * @param type 划转类型
     * @return 返回字符串 成功 : succ
     */
    String accountEntryFeign(Long userId, String currency, BigDecimal num,Integer type) throws Exception;

    /**
     * 查询用户的所有的usdt
     * @param userId 用户id
     * @return
     */
    String getAssets(Long userId);

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
    List<TWalletFundAccount> detail(Long userId);

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
