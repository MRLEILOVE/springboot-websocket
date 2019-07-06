package com.bittrade.currency.controller;

import com.bittrade.pojo.vo.TransactionPairVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.api.dao.ITCurrencyTradeDAO;
import com.bittrade.pojo.dto.TCurrencyTradeDTO;
import com.bittrade.pojo.vo.TCurrencyTradeVO;
import com.bittrade.pojo.model.TCurrencyTrade;
import com.bittrade.api.service.ITCurrencyTradeService;
import com.core.framework.base.controller.BaseController;

import java.util.List;

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
    @RequestMapping(value="/findTradeByCurrencyId/{currencyId2}",method = RequestMethod.GET)
    @ResponseBody
    public List<TransactionPairVO> findTradeByCurrencyId(@PathVariable("currencyId2") String currencyId2) {
        return tCurrencyTradeService.findTradeByCurrencyId(currencyId2);
    }
}
