package com.bittrade.c2c.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bittrade.__default.service.impl.DefaultTLegalCurrencyAccountServiceImpl;
import com.bittrade.c2c.dao.ITLegalCurrencyAccountDAO;
import com.bittrade.c2c.dao.ITLegalCurrencyCoinDAO;
import com.bittrade.c2c.service.ITLegalCurrencyAccountService;
import com.bittrade.common.constant.IConstant;
import com.bittrade.common.enums.StatusEnumer;
import com.bittrade.common.utils.RedisKeyUtil;
import com.bittrade.pojo.dto.TLegalCurrencyAccountDTO;
import com.bittrade.pojo.model.TLegalCurrencyAccount;
import com.bittrade.pojo.model.TLegalCurrencyCoin;
import com.bittrade.pojo.vo.AssetsVO;
import com.bittrade.pojo.vo.ConversionVo;
import com.bittrade.pojo.vo.TLegalCurrencyAccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Administrator
 *
 */
@Service
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
        List<AssetsVO> AssetsVOs = legalCurrencyAccountDAO.getAssets(userId);
        for(AssetsVO x : AssetsVOs){
            //获取最新价
            String s = jedisCluster.get(IConstant.REDIS_PREFIX__LINE_PRICE + x.getCurrencyName());
           /* if(s == null){
                s = "2";
            }*/
            BigDecimal price = new BigDecimal(s);
            //USDT累计
            BigDecimal all = x.getTotal().add(x.getTradeFrozen().add(x.getTransferFrozen()));
            totalUSDT = totalUSDT.add(price.multiply(all));
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
        //获取法币币种列表
        TLegalCurrencyCoin qryCoin = TLegalCurrencyCoin.builder().status(StatusEnumer.ENABLE.getCode()).build();
        List<TLegalCurrencyCoin> coins = legalCurrencyCoinDAO.getsBy(qryCoin);

        //获取用户所有的法币钱包
        List<AssetsVO> assetsVOs = legalCurrencyAccountDAO.getAssets(userId);
        if(assetsVOs == null || assetsVOs.size() <= 0){
            //为用户创建全部法币钱包
            createAllWallet(coins,userId);
            //封装好数据后返回给前端
            coins.stream().forEach(coin -> {
                AssetsVO vo = AssetsVO.builder().total(BigDecimal.ZERO).totalFrozen(BigDecimal.ZERO).currencyId(coin.getId()).currencyName(coin.getName()).build();
                assetsVOs.add(vo);
            });
        }else if(coins.size() > assetsVOs.size()){
            //为用户补全缺失的钱包
            List<TLegalCurrencyCoin> lockVos = createLockWallet(coins,assetsVOs,userId);
            lockVos.forEach(x -> {
                //将缺失的钱包添加到用户钱包列表，然后返回数据给前端
                assetsVOs.add(AssetsVO.builder().total(BigDecimal.ZERO).totalFrozen(BigDecimal.ZERO).currencyId(x.getId()).currencyName(x.getName()).build());
            });
        }
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
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        legalCurrencyAccountDAO.add(account);
        return account;
    }
}
