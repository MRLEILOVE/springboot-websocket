package com.bittrade.currency.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTCurrencyTradeServiceImpl;
import com.bittrade.currency.api.service.ITCurrencyTradeService;
import com.bittrade.currency.dao.ITCurrencyTradeDAO;
import com.bittrade.pojo.vo.CurrencyTradeVO;
import com.bittrade.pojo.vo.TransactionPairVO;

import redis.clients.jedis.JedisCluster;

/**
 * 
 * @author Administrator
 *
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class TCurrencyTradeServiceImpl extends
		DefaultTCurrencyTradeServiceImpl<ITCurrencyTradeDAO>
		implements ITCurrencyTradeService {

	@Autowired
	private ITCurrencyTradeDAO tCurrencyTradeDAO;
	@Autowired
    private JedisCluster jedisCluster;

	/**
	 * 根据法币id查找交易对
	 */
	@Override
	public List<TransactionPairVO> findTradeByCurrencyId2(String currencyId2,String userId) {
		List<TransactionPairVO> vos = tCurrencyTradeDAO.findTradeByCurrencyId2(currencyId2, userId);
		if(vos != null && vos.size() > 0){
			vos.stream().forEach(vo ->{
				//#TODO 价格跟涨幅待完善
//                String price = jedisCluster.get(RedisKeyUtil.getOkexSymbolLast(vo.getSymbol().replace('/', '_')));
                vo.setPrice(new BigDecimal(100));
                //涨跌幅
				vo.setChg(new BigDecimal(0.05));
			});
		}
		return vos;
	}

	/**
	 * 刚点进币币页面，获取交易对信息
	 * @param id 交易对id
	 * @return 交易对对象信息
	 */
	@Override
	public CurrencyTradeVO queryCurrencyTradeAtFirst(Integer id) {
		//如果id为空，就返回优先级最高的交易对信息
		if(id == null){
			return tCurrencyTradeDAO.getOneOrderBySort();
		}else {
			return tCurrencyTradeDAO.getById(id);
		}
	}
}
