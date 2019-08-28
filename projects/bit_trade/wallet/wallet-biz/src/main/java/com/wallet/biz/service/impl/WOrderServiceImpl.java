package com.wallet.biz.service.impl;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.pojo.model.WCoin;
import com.bittrade.pojo.model.WCoinConfig;
import com.bittrade.pojo.model.WOrder;
import com.bittrade.pojo.model.WWalletAccount;
import com.common.bittrade.service.IWCoinService;
import com.common.bittrade.service.IWWalletAccountService;
import com.core.common.DTO.ReturnDTO;
import com.wallet.biz.api.service.IWCoinConfigService;
import com.wallet.biz.api.service.IWOrderService;
import com.wallet.biz.dao.IWOrderDAO;
import com.wallet.biz.tool.SnowFlake;

/**
 * 
 * @author Administrator
 *
 */
@Service
@Component
public class WOrderServiceImpl extends ServiceImpl<IWOrderDAO, WOrder> implements IWOrderService {

    @Autowired
    private IWCoinConfigService WCoinConfigService;
    @Autowired
    IWWalletAccountService wWalletAccountService;
    @Autowired
    IWCoinService wCoinService;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public ReturnDTO addOrder(WOrder order) {
        WCoinConfig coinConfig = WCoinConfigService.getOne(new QueryWrapper<>(WCoinConfig.builder()
                .coinType(order.getCoinType())
                .token(order.getToken())
                .valid("E")
                .build()));

        WCoin orderWCoin = wCoinService.getOne(new QueryWrapper<>(WCoin.builder()
                .token(order.getToken())
                .isWithdraw("E").build()));

        if(null == coinConfig || null == orderWCoin){ return ReturnDTO.error("该币种暂时不支持提币");}

        WWalletAccount orderAccount = wWalletAccountService.getOne(new QueryWrapper<>(WWalletAccount.builder()
                .userId(order.getUserId())
                .currencyId(orderWCoin.getId().intValue())
                .build()));
        BigDecimal chAmount = (new BigDecimal(String.valueOf(order.getAmount())).add(new BigDecimal(String.valueOf(order.getFee()))));
        int i = orderAccount.getTotal().compareTo(chAmount);
        if (i<0){
            return ReturnDTO.error("提币超过用户当前余额");
        }
        Long id = (Long)redisTemplate.opsForValue().get("user" + order.getUserId());
        if (null!=id&&id.equals(order.getUserId())){
            return ReturnDTO.error("3秒内不可重复提交订单");
        }
        redisTemplate.opsForValue().set("user"+order.getOrderId(),order.getOrderId());
        redisTemplate.expire("user"+order.getOrderId(),3, TimeUnit.SECONDS);
        SnowFlake snowFlake = new SnowFlake(1, 1);
        order.setOrderId(String .valueOf(snowFlake.nextId()));
        order.setAmount(order.getAmount().multiply(new BigDecimal(-1)));

        orderAccount.setTransferFrozen(orderAccount.getTransferFrozen().add(chAmount));
        orderAccount.setTotal(orderAccount.getTotal().subtract(chAmount));
        boolean insert1 = wWalletAccountService.updateById(orderAccount);
        boolean insert = this.save(order);
        if (insert&&insert1){
            return ReturnDTO.ok("您的提币申请已经成功提交，请等待人工审核!");
        }

        return ReturnDTO.error("提交申请出错");

    }
}
