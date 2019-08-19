package com.bittrade.c2c.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bittrade.__default.service.impl.DefaultTLegalCurrencyAccountServiceImpl;
import com.bittrade.c2c.dao.ITLegalCurrencyAccountDAO;
import com.bittrade.c2c.service.ITLegalCurrencyAccountService;
import com.bittrade.common.constant.IConstant;
import com.bittrade.common.utils.RedisKeyUtil;
import com.bittrade.pojo.dto.TLegalCurrencyAccountDTO;
import com.bittrade.pojo.model.TLegalCurrencyAccount;
import com.bittrade.pojo.vo.AssetsVO;
import com.bittrade.pojo.vo.ConversionVo;
import com.bittrade.pojo.vo.TLegalCurrencyAccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import java.math.BigDecimal;
import java.util.List;

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
}
