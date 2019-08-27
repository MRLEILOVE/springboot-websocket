package com.common.bittrade.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bittrade.pojo.model.WCoin;
import com.bittrade.pojo.vo.CoinVo;

/**
 * 
 * @author Administrator
 *
 */
public interface IWCoinService extends IService<WCoin> {

    /**
     * 获取币种列表
     */
    List<CoinVo> getCoins();
	
}
