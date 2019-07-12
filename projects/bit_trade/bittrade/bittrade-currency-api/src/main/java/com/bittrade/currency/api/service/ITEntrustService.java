package com.bittrade.currency.api.service;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.bittrade.__default.service.IDefaultTEntrustService;
import com.bittrade.pojo.dto.DealDTO;
import com.bittrade.pojo.dto.TEntrustDTO;
import com.bittrade.pojo.model.TEntrust;
import com.bittrade.pojo.vo.TEntrustInfoVO;
import com.bittrade.pojo.vo.TEntrustVO;
import com.core.framework.DTO.ReturnDTO;

/**
 * 
 * @author Administrator
 *
 */
public interface ITEntrustService extends IDefaultTEntrustService<TEntrust, TEntrustDTO, TEntrustVO> {

    /**
     * 查询用户当前委托
     */
    List<TEntrustVO> queryPresentEntrustByUserId(String userId);

    /**
     * 查询用户历史委托
     */
    List<TEntrustVO> queryHistoryEntrustByUserId(String userId);

    /**
     * 买/卖交易对
     */
    ReturnDTO<String> deal(DealDTO dealDTO);

    /**
     * 查询用户的委托单成交明细
     */
    TEntrustInfoVO queryEntrustInfoByUserId(String userId, String entrustId);

    /**
     * 用户撤单
     */
    ReturnDTO<Object> killOrder(String entrustId);

	/**
	 * 修改信息在撮合时
	 * @param successAmount
	 * @param leftCount
	 * @param status
	 * @param ID
	 */
	void updateOnMatch(BigDecimal successAmount, BigDecimal leftCount, int status, long ID);
	
	String testPrm(QueryWrapper<TEntrust> qw);
	String testPrm_2(MergeSegments ms);
	
}
