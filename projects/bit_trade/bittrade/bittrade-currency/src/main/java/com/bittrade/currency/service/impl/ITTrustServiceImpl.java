package com.bittrade.currency.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bittrade.api.service.ITTrustService;
import com.bittrade.currency.dao.ITEntrustDAO;
import com.bittrade.pojo.dto.DealDTO;
import com.bittrade.pojo.model.TEntrust;
import com.bittrade.pojo.vo.TEntrustInfoVO;
import com.bittrade.pojo.vo.TEntrustVO;
import com.core.framework.DTO.ReturnDTO;

@Service
public class ITTrustServiceImpl implements ITTrustService {

    @Autowired
    private ITEntrustDAO tEntrustDAO;

    /**
     * 查询用户当前委托
     */
    @Override
    public List<TEntrustVO> queryPresentEntrustByUserId(String userId) {
        return tEntrustDAO.queryPresentEntrustByUserId(userId);
    }

    /**
     * 查询用户历史委托
     */
    @Override
    public List<TEntrustVO> queryHistoryEntrustByUserId(String userId) {
        return tEntrustDAO.queryHistoryEntrustByUserId(userId);
    }

    /**
     * 买/卖交易对
     */
    @Override
    public ReturnDTO<String> deal(DealDTO dealDTO) {
        //#TODO 调用接口
        return null;
    }

    /**
     * 查询用户的委托单成交明细
     */
    @Override
    public TEntrustInfoVO queryEntrustInfoByUserId(String userId, String entrustId) {
        return tEntrustDAO.queryEntrustInfoByUserId(userId,entrustId);
    }

    /**
     * 用户撤单
     */
    @Override
    public ReturnDTO killOrder(String entrustId) {
        //获取委托交易表
        TEntrust tEntrust = tEntrustDAO.selectById(entrustId);
        if(tEntrust == null){
            return ReturnDTO.error("交易单不存在");
        }
        //撤单
        int result = tEntrustDAO.killOrder(tEntrust);
        if(result == 0){
            return ReturnDTO.error("撤单失败");
        }else{
            return ReturnDTO.ok("撤单成功");
        }
    }
}
