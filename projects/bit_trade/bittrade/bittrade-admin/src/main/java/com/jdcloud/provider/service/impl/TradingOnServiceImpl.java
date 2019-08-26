package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.mapper.TradingOnMapper;
import com.jdcloud.provider.pojo.AccountConfig;
import com.jdcloud.provider.pojo.TradingOn;
import com.jdcloud.provider.service.AccountConfigService;
import com.jdcloud.provider.service.TradingOnService;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-07-26
 */
@Service
public class TradingOnServiceImpl extends ServiceImpl<TradingOnMapper, TradingOn> implements TradingOnService {

    @Autowired
    private AccountConfigService accountConfigService;

    /**
     * 保存交易对
     *
     * @param tradingOn
     * @return
     */
    @Override
    public Wrapper<String> saveTradingOn(TradingOn tradingOn) {
        EntityWrapper entity = new EntityWrapper();
        entity.eq("currency_id", tradingOn.getCurrencyId());
        entity.eq("number_id", tradingOn.getNumberId());
        int count = baseMapper.selectCount(entity);
        if (count > 0) {
            return WrapMapper.error("交易对已存在无需配置！");
        }
        AccountConfig config = accountConfigService.selectById(tradingOn.getNumberId());
        if (config == null) {
            return WrapMapper.error("币种配置失败！");
        }
        tradingOn.setNmuberName(config.getKeyword());
        Integer row = baseMapper.insert(tradingOn);
        if (row != 1) {
            return WrapMapper.error("保存交易对失败！");
        }
        return WrapMapper.ok("保存成功！");
    }

    /**
     * 保存交易对
     *
     * @param tradingOn
     * @return
     */
    @Override
    public Wrapper<String> updateTradingOns(TradingOn tradingOn) {
        TradingOn trading = baseMapper.selectById(tradingOn.getId());
        if (null == trading) {
            return WrapMapper.error("交易对不存在！");
        }
        trading.setSort(tradingOn.getSort());
        Integer row = baseMapper.updateById(trading);
        if (row != 1) {
            return WrapMapper.error("保存交易对失败！");
        }
        return WrapMapper.ok("保存交易对成功");
    }
}
