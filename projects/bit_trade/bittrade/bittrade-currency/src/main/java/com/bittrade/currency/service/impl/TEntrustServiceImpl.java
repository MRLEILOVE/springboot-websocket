package com.bittrade.currency.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bittrade.__default.service.impl.DefaultTEntrustServiceImpl;
import com.bittrade.common.enums.EntrustDirectionEnumer;
import com.bittrade.currency.api.service.ITEntrustService;
import com.bittrade.currency.dao.ITCurrencyTradeDAO;
import com.bittrade.currency.dao.ITEntrustDAO;
import com.bittrade.currency.dao.ITWalletDAO;
import com.bittrade.pojo.dto.DealDTO;
import com.bittrade.pojo.dto.TEntrustDTO;
import com.bittrade.pojo.model.TCurrencyTrade;
import com.bittrade.pojo.model.TEntrust;
import com.bittrade.pojo.model.TWallet;
import com.bittrade.pojo.vo.TEntrustInfoVO;
import com.bittrade.pojo.vo.TEntrustVO;
import com.core.framework.DTO.ReturnDTO;

/**
 * 
 * @author Administrator
 *
 */
@Service
@com.alibaba.dubbo.config.annotation.Service
public class TEntrustServiceImpl extends DefaultTEntrustServiceImpl<ITEntrustDAO, TEntrust, TEntrustDTO, TEntrustVO>
		implements ITEntrustService {

	@Autowired
	private ITEntrustDAO entrustDAO;

    @Autowired
    private ITCurrencyTradeDAO currencyTradeDAO;
    @Autowired
    private ITWalletDAO walletDAO;

    /**
     * 查询用户当前委托
     */
    @Override
    public List<TEntrustVO> queryPresentEntrustByUserId(String userId) {
        return entrustDAO.queryPresentEntrustByUserId(userId);
    }

    /**
     * 查询用户历史委托
     */
    @Override
    public List<TEntrustVO> queryHistoryEntrustByUserId(String userId) {
        return entrustDAO.queryHistoryEntrustByUserId(userId);
    }

    /**
     * 买/卖交易对
     */
    @Override
    public ReturnDTO<String> deal(DealDTO dealDTO) {
        //1.校验参数
        String price = dealDTO.getPrice();      //单价
        String count = dealDTO.getCount();      //数量
        String amount = dealDTO.getAmount();    //总价
        if(price == null || Double.parseDouble(price) < 0.0)            return ReturnDTO.error("价格异常");
        if(count == null || Double.parseDouble(count) <= 0)             return ReturnDTO.error("数量异常");
        if(amount == null || Double.parseDouble(amount) < 0.0)          return ReturnDTO.error("总价异常");
        if(dealDTO.getCurrencyTradeId() == null)                        return ReturnDTO.error("交易对id为空");
        if(dealDTO.getEntrustDirection() == null)                       return ReturnDTO.error("买卖方向为空");
        if(dealDTO.getUserId() == null)                                 return ReturnDTO.error("用户id为空");

        TCurrencyTrade currencyTrade = currencyTradeDAO.selectById(dealDTO.getCurrencyTradeId());
        if(currencyTrade == null)                                       return ReturnDTO.error("交易对不存在");

        String[] split_price = price.split(".");
        if(split_price != null && split_price.length == 2){
            int length = split_price[1].length();
            if(length > currencyTrade.getPriceDecimalDigits()){
                return ReturnDTO.error("单价小数位过长");
            }
        }

        String[] split_count = count.split(".");
        if(split_count != null && split_count.length == 2){
            int length = split_count[1].length();
            if(length > currencyTrade.getCountDecimalDigits()){
                return ReturnDTO.error("数量小数位过长");
            }
        }

        BigDecimal big_count = new BigDecimal(count);
        BigDecimal big_price = new BigDecimal(price);
        BigDecimal big_amount = new BigDecimal(amount);

        if(big_count.compareTo(currencyTrade.getMinBuyCount()) == -1)        return ReturnDTO.error("数量低于最小可买/可卖数量");
        if(big_price.compareTo(currencyTrade.getMinBuyPrice()) == -1)        return ReturnDTO.error("单价低于最小可买/可卖单价");
        if(big_amount.compareTo(currencyTrade.getMinBuyAmount()) == -1)      return ReturnDTO.error("总价低于最小可买/可卖总价");
        if(big_count.compareTo(currencyTrade.getMaxBuyCount()) ==1)          return ReturnDTO.error("数量高于最大可买/可卖数量");
        if(big_price.compareTo(currencyTrade.getMaxBuyPrice()) == 1)         return ReturnDTO.error("单价高于最大可买/可卖单价");
        if(big_amount.compareTo(currencyTrade.getMaxBuyAmount()) ==1)        return ReturnDTO.error("总价高于最大可买/可卖总价");

        if(currencyTrade.getStatus() != 1)                                   return ReturnDTO.error("交易对状态不可用");

        //2.校验用户钱包

        QueryWrapper<TWallet> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",dealDTO.getUserId());
        if(EntrustDirectionEnumer.BUY.getCode() == dealDTO.getEntrustDirection()){
            //买入
            //检验法币钱包数量
            queryWrapper.eq("currency_id",currencyTrade.getCurrencyId2()).select("total");
        }else{
            //卖出
            //检验货币钱包数量
            queryWrapper.eq("currency_id",currencyTrade.getCurrencyId1()).select("total");
        }
        TWallet tWallet = walletDAO.selectOne(queryWrapper);
        if(tWallet == null)                                             return ReturnDTO.error("用户钱包不存在");
        BigDecimal total = tWallet.getTotal();
        if(total == null || total.compareTo(big_amount) == -1)          return ReturnDTO.error("用户钱包余额不足");



        //#TODO 调用接口
        return null;
    }

    /**
     * 查询用户的委托单成交明细
     */
    @Override
    public TEntrustInfoVO queryEntrustInfoByUserId(String userId, String entrustId) {
        return entrustDAO.queryEntrustInfoByUserId(userId,entrustId);
    }

    /**
     * 用户撤单
     */
    @Override
    public ReturnDTO killOrder(String entrustId) {
        //获取委托交易表
        TEntrust tEntrust = entrustDAO.selectById(entrustId);
        if(tEntrust == null){
            return ReturnDTO.error("交易单不存在");
        }
        //撤单
        int result = entrustDAO.killOrder(tEntrust);
        if(result == 0){
            return ReturnDTO.error("撤单失败");
        }else{
            return ReturnDTO.ok("撤单成功");
        }
    }

	@Override
	public void updateOnMatch(BigDecimal successAmount, BigDecimal leftCount, int status, long ID) {
		entrustDAO.updateOnMatch(successAmount, leftCount, status, ID);
	}

}
