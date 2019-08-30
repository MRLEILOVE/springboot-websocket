package com.bittrade.entrust.api.service;

import java.math.BigDecimal;
import java.util.List;

import com.bittrade.__default.service.IDefaultTKlineService;
import com.bittrade.pojo.dto.QueryKLineDto;
import com.bittrade.pojo.dto.TKlineDTO;
import com.bittrade.pojo.model.TEntrustRecord;
import com.bittrade.pojo.model.TKline;
import com.bittrade.pojo.vo.QueryKLineVO;
import com.bittrade.pojo.vo.TKlineVO;

/**
 * 也可以单独一个进程或者把相关的存储也一同单独。
 * 
 * @author Administrator
 *
 */
public interface ITKlineService extends IDefaultTKlineService<TKline, TKlineDTO, TKlineVO> {

	/**
	 * k线查询
	 */
	List<QueryKLineVO> queryKLine(QueryKLineDto queryKLineDto);

	/**
	 * 生成K线
	 * 
	 * @param entrustRecord
	 * @param dealPrice
	 */
	void modifyKLine(TEntrustRecord entrustRecord, BigDecimal dealPrice);

	/**
	 * 根据交易对查询最新k线
	 * @param currencyTradeId 交易对id
	 * @return
	 */
	QueryKLineVO queryKLineBySymbol(Integer currencyTradeId);
}
