package com.wallet.biz.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bittrade.pojo.vo.CoinVo;
import com.wallet.biz.pojo.model.WCoin;

import java.util.List;

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
