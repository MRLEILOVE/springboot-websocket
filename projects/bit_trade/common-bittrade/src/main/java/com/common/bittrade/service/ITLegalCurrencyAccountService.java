package com.common.bittrade.service;

import com.bittrade.__default.service.IDefaultTLegalCurrencyAccountService;
import com.bittrade.pojo.dto.TLegalCurrencyAccountDTO;
import com.bittrade.pojo.model.TLegalCurrencyCoin;
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
     * 根据 userId 、coinName 获取法币账户
     * <br/>
     * create by: leigq
     * <br/>
     * create time: 2019/8/20 14:19
     * @param userId : 用户id
     * @param coinId : 币 id
     * @return  法币账户
     */
    TLegalCurrencyAccount getByUserIdAndCoinId(Long userId, Long coinId);

    /**
     * 获取用户法币账户总的usdt数量
     * @param userId 用户id
     * @return
     */
    BigDecimal getAssets(Long userId);

    /**
     * 根据币种名称获取c2c钱包
     * @param coinName 币种名称
     * @return
     */
    TLegalCurrencyCoin getCoinByName(String coinName);

    /**
     * 获取c2c账号
     * @param userId 用户id
     * @param coinId 法币币种id
     * @return
     */
    TLegalCurrencyAccount getC2CAccount(Long userId, Long coinId);

    /**
     * c2c钱包出账
     * @param id 钱包id
     * @param num 数量
     * @param version 版本号
     * @return
     */
    Integer c2cOut(Long id, BigDecimal num, Integer version);

    /**
     * c2c钱包入账
     * @param id 钱包id
     * @param num 划转数量
     * @param version 版本号
     * @return
     */
    Integer c2cIn(Long id, BigDecimal num, Integer version);


    /**
     * 冻结对应币种账户；余额减，冻结加
     * <br/>
     * create by: leigq
     * <br/>
     * create time: 2019/8/20 22:51
     * @param userId : userId
     * @param coinId : coinId
     * @param amount : 数量
     * @return
     */
	Boolean freezeAmount(Long userId, Long coinId, BigDecimal amount);


    /**
     * 解结对应币种账户；余额加，冻结减
     * <br/>
     * create by: leigq
     * <br/>
     * create time: 2019/8/20 22:51
     * @param userId : userId
     * @param coinId : coinId
     * @param amount : 数量
     * @return
     */
    Boolean unFreezeAmount(Long userId, Long coinId, BigDecimal amount);
}
