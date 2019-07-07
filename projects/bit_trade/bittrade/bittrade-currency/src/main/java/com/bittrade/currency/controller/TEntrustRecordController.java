package com.bittrade.currency.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.api.service.ITDealService;
import com.bittrade.api.service.ITEntrustRecordService;
import com.bittrade.currency.dao.ITEntrustRecordDAO;
import com.bittrade.pojo.dto.TEntrustRecordDTO;
import com.bittrade.pojo.model.TEntrustRecord;
import com.bittrade.pojo.vo.TEntrustRecordVO;
import com.bittrade.pojo.vo.TRealTimeTransactionVO;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tEntrustRecord" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TEntrustRecordController extends BaseController<TEntrustRecord, TEntrustRecordDTO, TEntrustRecordVO, ITEntrustRecordDAO, ITEntrustRecordService<TEntrustRecord, TEntrustRecordDTO, TEntrustRecordVO, ITEntrustRecordDAO>> {
    @Autowired
    private ITDealService dealService;

    //    @ApiOperation(value = "查询用户成交记录")
    @GetMapping(value = "/queryDealEntrustByUserId/{userId}")
    @ResponseBody
    public List<TEntrustRecordVO> queryDealEntrustByUserId(@PathVariable("userId") String userId) {
        return dealService.queryDealEntrustByUserId(userId);
    }

    //    @ApiOperation(value = "实时成交")
    @GetMapping(value = "/realTimeTransaction/{currencyTradeId}")
    @ResponseBody
    public List<TRealTimeTransactionVO> realTimeTransaction(@PathVariable("currencyTradeId") String currencyTradeId) {
        return dealService.realTimeTransaction(currencyTradeId);
    }
}
