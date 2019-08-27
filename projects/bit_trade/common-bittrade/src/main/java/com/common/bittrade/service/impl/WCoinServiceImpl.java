package com.common.bittrade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.pojo.model.WCoin;
import com.bittrade.pojo.vo.CoinVo;
import com.common.bittrade.dao.IWCoinDAO;
import com.common.bittrade.service.IWCoinService;

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

}