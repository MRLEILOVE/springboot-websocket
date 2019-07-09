package com.bittrade.api.service;

import com.bittrade.pojo.dto.DealDTO;
import com.bittrade.pojo.vo.TEntrustInfoVO;
import com.bittrade.pojo.vo.TEntrustVO;
import com.core.framework.DTO.ReturnDTO;

import java.util.List;

public interface ITTrustService {
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
    ReturnDTO killOrder(String entrustId);
}
