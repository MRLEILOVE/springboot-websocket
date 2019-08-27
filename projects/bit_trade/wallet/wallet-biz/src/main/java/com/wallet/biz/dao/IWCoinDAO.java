package com.wallet.biz.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bittrade.pojo.vo.CoinVo;
import com.wallet.biz.pojo.model.WCoin;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
public interface IWCoinDAO extends BaseMapper<WCoin> {

    /**
     * 获取币种列表
     */
    List<CoinVo> getCoins();
}
