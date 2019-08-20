package com.walletbiz.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.walletbiz.mapper.PersonalAccountMapper;
import com.walletbiz.mapper.TAccountConfigMapper;
import com.walletbiz.pojo.CPersonalAccount;
import com.walletbiz.service.ParameterConfigService;
import com.walletbiz.service.PersonalAccountService;
import com.walletbiz.utils.RedisKeyUtil;
import com.walletbiz.vo.ConversionVo;
import com.walletbiz.vo.PersonalAccountVO;

@Service
public class PersonalAccountServiceImpl extends ServiceImpl<PersonalAccountMapper, CPersonalAccount> implements PersonalAccountService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private PersonalAccountMapper personalAccountMapper;
    @Autowired
    private TAccountConfigMapper accountConfigMapper;
    @Autowired
    private ParameterConfigService parameterConfigService;

    /**
     * 法币账户总资金折合
     * @param userId 用户id
     * @return
     */
    @Override
    public ConversionVo totalConversion(Long userId) {
        ConversionVo vo = ConversionVo.builder().CNY(BigDecimal.ZERO).USDT(BigDecimal.ZERO).build();
        BigDecimal totalUsdt = BigDecimal.ZERO;
//        BigDecimal totalCNY = BigDecimal.ZERO;
        //获取usdt转人民币汇率
        Object usdt_cny = redisTemplate.opsForValue().get(RedisKeyUtil.OK_USD_TO_CNY_RATE_KEY);
        BigDecimal USDT_CNY_RATE = new BigDecimal(usdt_cny.toString());

        //获取用户的法币钱包
        List<PersonalAccountVO> accounts = personalAccountMapper.getByUserId(userId);
        if(accounts != null && accounts.size() > 0){
            for(PersonalAccountVO account : accounts){
                //判断是不是bitt,是的话略过
                if("BITT".equals(account.getCurrency())){
                    continue;
                }
                //获取汇率
                Object o = redisTemplate.opsForValue().get(RedisKeyUtil.REDIS_PREFIX_LINE_PRICE + account.getCurrency());
                BigDecimal rate = new BigDecimal(o.toString());
                //转换成usdt
                BigDecimal total = account.getBalance().add(account.getUsedMargin());
                totalUsdt = totalUsdt.add(rate.multiply(total));
            }
        }

        vo.setUSDT(totalUsdt);
        vo.setCNY(USDT_CNY_RATE.multiply(totalUsdt));
        return vo;
    }

    /**
     * 用户的法币账户钱包列表
     * @param userId 用户id
     * @return
     */
    @Override
    public List<PersonalAccountVO> list(Long userId) {
        return personalAccountMapper.getByUserId(userId);
    }
}
