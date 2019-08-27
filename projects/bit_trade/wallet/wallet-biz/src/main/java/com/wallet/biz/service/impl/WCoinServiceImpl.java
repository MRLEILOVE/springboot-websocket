package com.wallet.biz.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.pojo.vo.CoinVo;
import com.wallet.biz.api.service.IWCoinService;
import com.wallet.biz.dao.IWCoinDAO;
import com.wallet.biz.pojo.model.WCoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
@Service
@Component
public class WCoinServiceImpl extends ServiceImpl<IWCoinDAO, WCoin> implements IWCoinService {
    @Autowired
    private IWCoinDAO wCoinDAO;

    /**
     * 获取币种列表
     */
    @Override
    public List<CoinVo> getCoins() {
        return wCoinDAO.getCoins();
    }

    /**
     * 根据名称获取币种
     * @param coinName 币种名称
     */
    @Override
    public WCoin getByName(String coinName) {
        QueryWrapper<WCoin> qry = new QueryWrapper<>();
        qry.eq(WCoin.FieldNames.TOKEN,coinName);
        return wCoinDAO.selectOne(qry);
    }
}
