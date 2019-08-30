package com.common.bittrade.dao;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bittrade.pojo.model.WCoin;
import com.bittrade.pojo.vo.CoinVo;

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
