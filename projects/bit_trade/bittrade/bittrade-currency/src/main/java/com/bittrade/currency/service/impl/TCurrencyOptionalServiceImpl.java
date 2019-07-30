package com.bittrade.currency.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bittrade.__default.service.impl.DefaultTCurrencyOptionalServiceImpl;
import com.bittrade.currency.api.service.ITCurrencyOptionalService;
import com.bittrade.currency.dao.ITCurrencyOptionalDAO;
import com.bittrade.pojo.dto.TCurrencyOptionalDTO;
import com.bittrade.pojo.model.TCurrencyOptional;
import com.bittrade.pojo.vo.TCurrencyOptionalVO;
import com.bittrade.pojo.vo.TransactionPairVO;
import com.core.common.DTO.ReturnDTO;

/**
 * 
 * @author Administrator
 *
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class TCurrencyOptionalServiceImpl extends DefaultTCurrencyOptionalServiceImpl<ITCurrencyOptionalDAO, TCurrencyOptional, TCurrencyOptionalDTO, TCurrencyOptionalVO> implements ITCurrencyOptionalService {
    @Autowired
    private ITCurrencyOptionalDAO currencyOptionalDAO;

    /**
     * 查询用户自选的交易对
     */
    @Override
    public List<TransactionPairVO> findOptionalByUserId(String userId) {
        return currencyOptionalDAO.findOptionalByUserId(userId);
    }

    /**
     * 添加自选
     */
    @Override
    public ReturnDTO<String> addOptional(TCurrencyOptionalDTO currencyOptionalDTO) {
        //查询是否添加过
        Map<String,Object> map = new HashMap<>();
        map.put(TCurrencyOptional.FieldNames.USER_ID,currencyOptionalDTO.getUserId());
        map.put(TCurrencyOptional.FieldNames.CURRENCY_TRADE_ID,currencyOptionalDTO.getCurrencyTradeId());
        List<TCurrencyOptional> tCurrencyOptionals = currencyOptionalDAO.selectByMap(map);

        if(tCurrencyOptionals != null && tCurrencyOptionals.size() > 0){
            //存在就更新
            TCurrencyOptional tCurrencyOptional = tCurrencyOptionals.get(0);
            tCurrencyOptional.setUpdateTime(LocalDateTime.now());
            tCurrencyOptional.setStatus((byte) 1);
            int update = currencyOptionalDAO.updateById(tCurrencyOptional);
            if(update == 0){
                return ReturnDTO.error("添加失败");
            }else{
                return ReturnDTO.ok("添加成功");
            }
        }else{
            //不存在就添加
            TCurrencyOptional currencyOptional =
                    TCurrencyOptional.builder()
                    .userId(currencyOptionalDTO.getUserId())
                    .currencyTradeId(currencyOptionalDTO.getCurrencyTradeId())
                    .status((byte) 1)
                    .updateTime(LocalDateTime.now())
                    .createTime(LocalDateTime.now())
                    .build();
            int add = currencyOptionalDAO.add(currencyOptional);


            if(add == 0){
                return ReturnDTO.error("添加失败");
            }else{
                return ReturnDTO.ok("添加成功");
            }
        }
    }

    /**
     * 删除自选
     */
    @Override
    public ReturnDTO<String> deleteOptional(TCurrencyOptionalDTO currencyOptionalDTO) {
        QueryWrapper<TCurrencyOptional> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(TCurrencyOptional.FieldNames.USER_ID,currencyOptionalDTO.getUserId())
                    .eq(TCurrencyOptional.FieldNames.CURRENCY_TRADE_ID,currencyOptionalDTO.getCurrencyTradeId());

        TCurrencyOptional tCurrencyOptional = currencyOptionalDAO.selectOne(queryWrapper);

        if(tCurrencyOptional != null){
            //存在就更新
            tCurrencyOptional.setUpdateTime(LocalDateTime.now());
            tCurrencyOptional.setStatus((byte) 0);
            int update = currencyOptionalDAO.updateById(tCurrencyOptional);
            if(update == 0){
                return ReturnDTO.error("删除失败");
            }else{
                return ReturnDTO.ok("删除成功");
            }
        }else{
            return ReturnDTO.error("删除失败,不存在该信息");
        }
    }
}
