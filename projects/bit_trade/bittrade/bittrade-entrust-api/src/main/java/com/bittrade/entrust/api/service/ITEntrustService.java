package com.bittrade.entrust.api.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
	 * 修改信息在撮合时（要轮询？）
	 * @param successAmount
	 * @param leftCount
	 * @param status
	 * @param updateTime
	 * @param ID
	 * @param version
	 */
	int updateOnMatch(BigDecimal successAmount, BigDecimal leftCount, int status, LocalDateTime updateTime, long ID, int version);
	
}
