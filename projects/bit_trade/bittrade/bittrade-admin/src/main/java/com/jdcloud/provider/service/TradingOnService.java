package com.jdcloud.provider.service;

import com.jdcloud.provider.pojo.TradingOn;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.util.wrapper.Wrapper;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-07-26
 */
public interface TradingOnService extends IService<TradingOn> {

    Wrapper<String> saveTradingOn(TradingOn tradingOn);

    Wrapper<String> updateTradingOns(TradingOn tradingOn);
}
