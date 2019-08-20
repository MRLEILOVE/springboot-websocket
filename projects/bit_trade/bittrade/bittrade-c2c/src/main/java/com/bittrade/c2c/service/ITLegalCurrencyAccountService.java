package com.bittrade.c2c.service;

import com.bittrade.__default.service.IDefaultTLegalCurrencyAccountService;
import com.bittrade.pojo.dto.TLegalCurrencyAccountDTO;
import com.bittrade.pojo.vo.AssetsVO;
import com.bittrade.pojo.vo.ConversionVo;
import com.bittrade.pojo.vo.TLegalCurrencyAccountVO;
import com.bittrade.pojo.model.TLegalCurrencyAccount;

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
     * 根据 userId 、coinName 获取法币账户
     * <br/>
     * create by: leigq
     * <br/>
     * create time: 2019/8/20 14:19
     * @param userId : 用户id
     * @param coinName : 币名
     * @return  法币账户
     */
    TLegalCurrencyAccount getByUserIdAndCoinName(Long userId, String coinName);
}
