package com.bittrade.c2c.service;

import com.bittrade.__default.service.IDefaultTLegalCurrencyAccountService;
import com.bittrade.pojo.dto.TLegalCurrencyAccountDTO;
import com.bittrade.pojo.vo.AssetsVO;
import com.bittrade.pojo.vo.ConversionVo;
import com.bittrade.pojo.vo.TLegalCurrencyAccountVO;
import com.bittrade.pojo.model.TLegalCurrencyAccount;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public interface ITLegalCurrencyAccountService extends IDefaultTLegalCurrencyAccountService<TLegalCurrencyAccount, TLegalCurrencyAccountDTO, TLegalCurrencyAccountVO> {

    /**
     * c2c账户总资金折合
     * @param userId 用户id
     * @return
     */
    ConversionVo totalConversion(Long userId);

    /**
     * 查询当前用户的法币账户钱包列表
     * @param userId 用户id
     * @return
     */
    List<AssetsVO> detail(Long userId);

    /**
     * 查询用户钱包可用余额
     * @param userId 用户id
     * @param coinName 币种名称
     * @return
     */
    String availableBalance(Long userId, String coinName);

    /**
     * 获取用户法币账户总的usdt数量
     * @param userId 用户id
     * @return
     */
    BigDecimal getAssets(Long userId);
}
