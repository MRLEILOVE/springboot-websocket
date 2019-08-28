package com.common.bittrade.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.bittrade.__default.service.impl.DefaultTLegalCurrencyAccountServiceImpl;
import com.bittrade.common.constant.IConstant;
import com.bittrade.common.enums.StatusEnumer;
import com.bittrade.common.utils.RedisKeyUtil;
import com.bittrade.pojo.dto.TLegalCurrencyAccountDTO;
import com.bittrade.pojo.model.TLegalCurrencyAccount;
import com.bittrade.pojo.model.TLegalCurrencyCoin;
import com.bittrade.pojo.vo.AssetsVO;
import com.bittrade.pojo.vo.ConversionVo;
import com.bittrade.pojo.vo.TLegalCurrencyAccountVO;
import com.common.bittrade.dao.ITLegalCurrencyAccountDAO;
import com.common.bittrade.dao.ITLegalCurrencyCoinDAO;
import com.common.bittrade.service.ITLegalCurrencyAccountService;

import redis.clients.jedis.JedisCluster;

/**
 * 
 * @author Administrator
 *
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class TLegalCurrencyAccountServiceImpl extends DefaultTLegalCurrencyAccountServiceImpl<ITLegalCurrencyAccountDAO, TLegalCurrencyAccount, TLegalCurrencyAccountDTO, TLegalCurrencyAccountVO> implements ITLegalCurrencyAccountService {

    @Autowired
    private ITLegalCurrencyAccountDAO legalCurrencyAccountDAO;
    @Autowired
    private ITLegalCurrencyCoinDAO legalCurrencyCoinDAO;
    @Autowired
    private JedisCluster jedisCluster;

    /**
     * c2c账户总资金折合
     * @param userId 用户id
     * @return
     */
    @Override
    public ConversionVo totalConversion(Long userId) {
        ConversionVo vo = ConversionVo.builder().CNY(BigDecimal.ZERO).USDT(BigDecimal.ZERO).build();
        BigDecimal totalUSDT = BigDecimal.ZERO;
        //获取人民币-usdt汇率
        String value = jedisCluster.get(RedisKeyUtil.USD_TO_CNY_RATE_KEY);
        BigDecimal USD_TO_CNY_RATE_RATE = new BigDecimal(JSONObject.parseObject(value).get("rate").toString());

        //获取用户所有的法币钱包
        List<AssetsVO> assetsVOs = legalCurrencyAccountDAO.getAssets(userId);
        //获取法币币种列表
        TLegalCurrencyCoin qryCoin = TLegalCurrencyCoin.builder().status(StatusEnumer.ENABLE.getCode()).build();
        List<TLegalCurrencyCoin> coins = legalCurrencyCoinDAO.getsBy(qryCoin);
        if(assetsVOs == null || assetsVOs.size() <= 0){
            //为用户创建全部法币钱包
            createAllWallet(coins,userId);
            //封装好数据后返回给前端
            coins.stream().forEach(coin -> {
                AssetsVO o = AssetsVO.builder().total(BigDecimal.ZERO).totalFrozen(BigDecimal.ZERO).currencyId(coin.getId()).tradeFrozen(BigDecimal.ZERO).transferFrozen(BigDecimal.ZERO).currencyName(coin.getName()).build();
                assetsVOs.add(o);
            });
        }else if(coins.size() > assetsVOs.size()){
            //为用户补全缺失的钱包
            List<TLegalCurrencyCoin> lockVos = createLockWallet(coins,assetsVOs,userId);
            lockVos.forEach(x -> {
                //将缺失的钱包添加到用户钱包列表，然后返回数据给前端
                assetsVOs.add(AssetsVO.builder().total(BigDecimal.ZERO).totalFrozen(BigDecimal.ZERO).currencyId(x.getId()).tradeFrozen(BigDecimal.ZERO).transferFrozen(BigDecimal.ZERO).currencyName(x.getName()).build());
            });
        }

        for(AssetsVO x : assetsVOs){
            BigDecimal all = x.getTotal().add(x.getTotalFrozen());
            if("USDT".equals(x.getCurrencyName())){
                totalUSDT = totalUSDT.add(all);
            }else{
                //获取最新价
                String s = jedisCluster.get(IConstant.REDIS_PREFIX__LINE_PRICE + x.getCurrencyName() + "-USDT");

                if(s == null){
                    s = "1.0";
                }

                BigDecimal price = new BigDecimal(s);
                //USDT累计
                totalUSDT = totalUSDT.add(price.multiply(all));
            }
        }
        BigDecimal totalCNY = totalUSDT.multiply(USD_TO_CNY_RATE_RATE);
        return ConversionVo.builder().CNY(totalCNY).USDT(totalUSDT).build();
    }

    /**
     * 查询当前用户的法币账户钱包列表
     * @param userId 用户id
     * @return
     */
    @Override
    public List<AssetsVO> detail(Long userId) {
        //获取用户所有的法币钱包
        List<AssetsVO> assetsVOs = legalCurrencyAccountDAO.getAssets(userId);
        return assetsVOs;
    }

    /**
     * 为用户补全缺失的钱包
     * @param coins 可用的法币币种列表
     * @param assetsVOs 用户已有的钱包列表
     * @param userId
     * @return
     */
    private List<TLegalCurrencyCoin> createLockWallet(List<TLegalCurrencyCoin> coins, List<AssetsVO> assetsVOs, Long userId) {
        List<TLegalCurrencyCoin> lockList = new ArrayList<>();//需要返回的缺失钱包的币种列表
        Map<String,String> existAccountMap = new HashMap<>();//已经存在的钱包map

        //将已有的钱包封装进existAccountMap
        assetsVOs.forEach(x -> {
            existAccountMap.put(x.getCurrencyName(),x.getCurrencyName());
        });

        //遍历法币币种列表，寻找以及创建缺失的钱包列表
        coins.forEach(x ->{
            if(existAccountMap.get(x.getName()) == null){
                //将对象加入lockList
                lockList.add(x);
                //创建钱包
                createWallet(x.getId(),userId);
            }
        });
        return lockList;
    }

    /**
     * 为用户创建所有的法币钱包
     * @param coins 法币可用的币种列表
     * @param userId 用户id
     */
    private void createAllWallet(List<TLegalCurrencyCoin> coins, Long userId) {
        coins.stream().forEach(x -> {
            createWallet(x.getId(),userId);
        });
    }

    /**
     * 创建法币钱包
     * @param coinId 法币币种id
     * @param userId 用户id
     */
    private TLegalCurrencyAccount createWallet(Long coinId, Long userId) {
        TLegalCurrencyAccount account = TLegalCurrencyAccount.builder()
                .userId(userId)
                .coinId(coinId.intValue())
                .balanceAmount(BigDecimal.ZERO)
                .freezeAmount(BigDecimal.ZERO)
                .version(0)
                .c2cAlreadyDealCount(0)
                .c2cTotalCount(0)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        legalCurrencyAccountDAO.add(account);
        return account;
    }

    /**
     * 查询用户钱包可用余额
     * @param userId 用户id
     * @param coinName 币种名称
     * @return
     */
    @Override
    public String availableBalance(Long userId, String coinName) {
        return legalCurrencyAccountDAO.availableBalance(userId,coinName);
    }

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
    @Override
    public TLegalCurrencyAccount getByUserIdAndCoinId(Long userId, Long coinId) {
        return legalCurrencyAccountDAO.getByUserIdAndCoinId(userId, coinId);
    }

    /**
     * 获取用户法币账户总的usdt数量
     * @param userId 用户id
     * @return
     */
    @Override
    public BigDecimal getAssets(Long userId) {
        ConversionVo vo = totalConversion(userId);
        return vo.getUSDT();
    }

    /**
     * 根据币种名称获取c2c钱包
     * @param coinName 币种名称
     * @return
     */
    @Override
    public TLegalCurrencyCoin getCoinByName(String coinName) {
        TLegalCurrencyCoin qryCoin = TLegalCurrencyCoin.builder().name(coinName).status(StatusEnumer.ENABLE.getCode()).build();
        return legalCurrencyCoinDAO.getBy(qryCoin);
    }

    /**
     * 获取c2c账号
     * @param userId 用户id
     * @param coinId 法币币种id
     * @return
     */
    @Override
    public TLegalCurrencyAccount getC2CAccount(Long userId, Long coinId) {
        TLegalCurrencyAccount qryAccount = TLegalCurrencyAccount.builder().userId(userId).coinId(coinId.intValue()).build();
        TLegalCurrencyAccount c2cAccount = legalCurrencyAccountDAO.getBy(qryAccount);
        if(c2cAccount == null){
            //为用户开通钱包
            return createWallet(coinId,userId);
        }
        return c2cAccount;
    }

    /**
     * c2c钱包入账
     * @param id 钱包id
     * @param num 划转数量
     * @param version 版本号
     * @return
     */
    @Override
    public Integer c2cIn(Long id, BigDecimal num, Integer version) {
        return legalCurrencyAccountDAO.c2cIn(id,num,version);
    }

    /**
     * c2c钱包出账
     * @param id 钱包id
     * @param num 数量
     * @param version 版本号
     * @return
     */
    @Override
    public Integer c2cOut(Long id, BigDecimal num, Integer version) {
        return legalCurrencyAccountDAO.c2cOut(id,num,version);
    }

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
    @Override
    public Boolean freezeAmount(Long userId, Long coinId, BigDecimal amount) {
        TLegalCurrencyAccount currencyAccount = baseMapper.selectOne(new LambdaQueryWrapper<TLegalCurrencyAccount>()
                .eq(TLegalCurrencyAccount::getUserId, userId)
                .eq(TLegalCurrencyAccount::getCoinId, coinId));
        TLegalCurrencyAccount account = TLegalCurrencyAccount.builder()
                .id(currencyAccount.getId())
                .balanceAmount(currencyAccount.getBalanceAmount().subtract(amount))
                .freezeAmount(currencyAccount.getFreezeAmount().add(amount))
                .build();
        return baseMapper.updateById(account) > 0;
    }

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
    @Override
    public Boolean unFreezeAmount(Long userId, Long coinId, BigDecimal amount) {
        TLegalCurrencyAccount currencyAccount = baseMapper.selectOne(new LambdaQueryWrapper<TLegalCurrencyAccount>()
                .eq(TLegalCurrencyAccount::getUserId, userId)
                .eq(TLegalCurrencyAccount::getCoinId, coinId));
        TLegalCurrencyAccount account = TLegalCurrencyAccount.builder()
                .id(currencyAccount.getId())
                .balanceAmount(currencyAccount.getBalanceAmount().add(amount))
                .freezeAmount(currencyAccount.getFreezeAmount().subtract(amount))
                .build();
        return baseMapper.updateById(account) > 0;
    }
}