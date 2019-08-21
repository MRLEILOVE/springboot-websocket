package com.wallet.biz.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bittrade.common.constant.IConstant;
import com.bittrade.common.enums.FundCoinEnumer;
import com.bittrade.currency.api.service.ITCurrencyService;
import com.bittrade.pojo.model.TCurrency;
import com.core.tool.SnowFlake;
import com.wallet.biz.pojo.vo.AssetsVO;
import com.wallet.biz.pojo.vo.ConversionVo;
import com.wallet.biz.utils.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wallet.biz.api.service.IWWalletAccountService;
import com.wallet.biz.dao.IWWalletAccountDAO;
import com.wallet.biz.pojo.model.WWalletAccount;

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
@Component
public class WWalletAccountServiceImpl extends ServiceImpl<IWWalletAccountDAO, WWalletAccount> implements IWWalletAccountService {
    private static final SnowFlake SNOW_FLAKE__ENTRUST	= new SnowFlake( 2, 2);
    @Autowired
    private IWWalletAccountDAO walletAccountDAO;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Reference
    private ITCurrencyService currencyService;

    /**
     * 通过用户ID跟币种id获取资金钱包
     * @param userId 用户id
     * @param currencyId 币种id
     * @return 资金账户钱包
     */
    @Override
    public WWalletAccount getAccount(Long userId, Integer currencyId) {
        QueryWrapper<WWalletAccount> wrapper = new QueryWrapper<>();
        wrapper.eq(WWalletAccount.FieldNames.USER_ID,userId)
                .eq(WWalletAccount.FieldNames.CURRENCY_ID,currencyId);
        WWalletAccount wWalletAccount = walletAccountDAO.selectOne(wrapper);

        if(wWalletAccount == null){
            wWalletAccount = createAccount(userId,currencyId);
        }
        return wWalletAccount;
    }

    /**
     * 资金账户入账
     * @param fundAccount 资金账户
     * @param num 数量
     * @return
     */
    @Override
    public Integer fundAccountIn(WWalletAccount fundAccount, BigDecimal num) {
        return walletAccountDAO.fundAccountIn(fundAccount.getId(),num,fundAccount.getVersion());
    }

    /**
     * 资金账户入账
     * @param fundAccount 资金账户
     * @param num 数量
     * @return
     */
    @Override
    public Integer fundAccountOut(WWalletAccount fundAccount, BigDecimal num) {
        return walletAccountDAO.fundAccountOut(fundAccount.getId(),num,fundAccount.getVersion());
    }

    /**
     * 为用户开通资金钱包
     * @param userId 用户id
     * @param currencyId 币种id
     * @return
     */
    private WWalletAccount createAccount(Long userId, Integer currencyId) {
        WWalletAccount account = WWalletAccount.builder()
                .id(SNOW_FLAKE__ENTRUST.nextId())
                .userId(userId)
                .currencyId(currencyId)
                .total(BigDecimal.ZERO)
                .tradeFrozen(BigDecimal.ZERO)
                .transferFrozen(BigDecimal.ZERO)
                .version(0)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        int insert = walletAccountDAO.insert(account);
        return account;
    }


    /**
     * 总资金折合
     * @param userId 用户Id
     * @return
     */
    @Override
    public ConversionVo totalConversion(Long userId) {
        ConversionVo vo = ConversionVo.builder().CNY(BigDecimal.ZERO).USDT(BigDecimal.ZERO).build();
        BigDecimal totalUSDT = BigDecimal.ZERO;
        //获取人民币-usdt汇率
        String value = redisTemplate.opsForValue().get(RedisKeyUtil.USD_TO_CNY_RATE_KEY).toString();
        BigDecimal USD_TO_CNY_RATE_RATE = new BigDecimal(JSONObject.parseObject(value).get("rate").toString());

        //获取用户所有的法币钱包
        List<AssetsVO> AssetsVOs = walletAccountDAO.getAssets(userId);
        for(AssetsVO x : AssetsVOs){
            BigDecimal all = x.getTotal().add(x.getTradeFrozen().add(x.getTransferFrozen()));
            if("USDT".equals(x.getCurrencyName())){
                totalUSDT = totalUSDT.add(all);
            }else{
                //获取最新价
                String s = redisTemplate.opsForValue().get(IConstant.REDIS_PREFIX__LINE_PRICE + x.getCurrencyName() + "-USDT").toString();
                BigDecimal price = new BigDecimal(s);
                //USDT累计
                totalUSDT = totalUSDT.add(price.multiply(all));
            }
        }
        BigDecimal totalCNY = totalUSDT.multiply(USD_TO_CNY_RATE_RATE);
        return ConversionVo.builder().CNY(totalCNY).USDT(totalUSDT).build();
    }

    /**
     * 用户钱包列表
     * @param userId 用户id
     * @return 钱包列表
     */
    @Override
    public List<AssetsVO> detail(Long userId) {
        //查询币种列表
        List<TCurrency> currencies = currencyService.getCurrencies();

        //查询用户钱包列表
        List<AssetsVO> userAccountVOs =  walletAccountDAO.getAssets(userId);
        if(userAccountVOs == null || userAccountVOs.size() <= 0){
            //为用户创建全部钱包
            createAllWallet(currencies,userId);
            //封装好数据后返回给前端
            currencies.stream().forEach(x -> {
                AssetsVO vo = AssetsVO.builder().total(BigDecimal.ZERO).totalFrozen(BigDecimal.ZERO).currencyId(x.getId().longValue()).currencyName(x.getName()).build();
                userAccountVOs.add(vo);
            });
        }else if(currencies.size() > userAccountVOs.size()){
            //为用户创建缺失的钱包
            List<TCurrency> lockCurrency = createLockWallet(currencies,userAccountVOs,userId);
            lockCurrency.stream().forEach(x ->{
                //将缺失的钱包添加到用户钱包列表，然后返回数据给前端
                AssetsVO vo = AssetsVO.builder().total(BigDecimal.ZERO).totalFrozen(BigDecimal.ZERO).currencyId(x.getId().longValue()).currencyName(x.getName()).build();
                userAccountVOs.add(vo);
            });
        }
        return userAccountVOs;
    }

    /**
     * 为用户创建缺失的钱包
     * @param currencies 币种列表
     * @param userAccountVOs 用户已有的钱包列表
     * @param userId 用户id
     * @return 缺失钱包的币种列表
     */
    private List<TCurrency> createLockWallet(List<TCurrency> currencies, List<AssetsVO> userAccountVOs, Long userId) {
        List<TCurrency> lockList = new ArrayList<>();//需要返回的缺失钱包的币种列表
        Map<String,String> existAccountMap = new HashMap<>();//已经存在的钱包Map

        //将已经存在的钱包封装进existAccountMap
        userAccountVOs.stream().forEach(x ->{
            existAccountMap.put(x.getCurrencyName(),x.getCurrencyName());
        });

        //遍历币种列表，寻找以及创建缺失的钱包列表
        currencies.stream().forEach(x -> {
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
     * 为用户创建全部钱包
     * @param currencies
     * @param userId
     */
    private void createAllWallet(List<TCurrency> currencies, Long userId) {
        if(currencies != null || currencies.size() >= 0){
            currencies.forEach(x ->{
                //是否在枚举中，币币账户跟资金账户公用了一个币种表，但是资金账户现在只有BTC跟USDT
                if(FundCoinEnumer.getValueByKey(x.getId()) != null){
                    createWallet(x.getId(),userId);
                }
            });
        }
    }

    /**
     * 为用户创建钱包
     * @param currencyId 币种id
     * @param userId 用户id
     */
    private WWalletAccount createWallet(Integer currencyId, Long userId) {
        WWalletAccount account = WWalletAccount.builder()
                .id(SNOW_FLAKE__ENTRUST.nextId())
                .userId(userId)
                .currencyId(currencyId)
                .total(BigDecimal.ZERO)
                .tradeFrozen(BigDecimal.ZERO)
                .transferFrozen(BigDecimal.ZERO)
                .version(0)
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        walletAccountDAO.insert(account);
        return account;
    }
}
