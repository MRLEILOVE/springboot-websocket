package com.bittrade.currency.controller;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bittrade.pojo.model.TCurrency;
import com.bittrade.pojo.vo.TCurrencyVO;
import com.core.framework.DTO.ReturnDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.currency.api.service.ITCurrencyTradeService;
import com.bittrade.currency.dao.ITCurrencyTradeDAO;
import com.bittrade.pojo.dto.TCurrencyTradeDTO;
import com.bittrade.pojo.model.TCurrencyTrade;
import com.bittrade.pojo.vo.TCurrencyTradeVO;
import com.bittrade.pojo.vo.TransactionPairVO;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tCurrencyTrade" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TCurrencyTradeController extends BaseController<TCurrencyTrade, TCurrencyTradeDTO, TCurrencyTradeVO, ITCurrencyTradeDAO, ITCurrencyTradeService> {
    @Autowired
    private ITCurrencyTradeService tCurrencyTradeService;

    /**
     * 根据法币id查找交易对
     */
    @ApiOperation(value = "根据法币id查找交易对",notes = "传法币的id")
    @RequestMapping(value="/findTradeByCurrencyId2/{currencyId2}",method = RequestMethod.GET)
    @ResponseBody
    public ReturnDTO<List<TransactionPairVO>> findTradeByCurrencyId2(@PathVariable("currencyId2") String currencyId2) {
        QueryWrapper<TCurrencyVO> wrapper = new QueryWrapper<>();
        wrapper.eq(TCurrency.FieldNames.STATUS,1).select(TCurrency.FieldNames.ID,TCurrency.FieldNames.NAME);
        try{
            return ReturnDTO.ok(tCurrencyTradeService.findTradeByCurrencyId2(currencyId2));
        }catch (Exception e){
            return ReturnDTO.error("服务器异常");
        }

    }
}
