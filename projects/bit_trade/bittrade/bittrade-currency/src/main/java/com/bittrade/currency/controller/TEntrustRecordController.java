package com.bittrade.currency.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bittrade.entrust.api.service.ITEntrustRecordService;
import com.bittrade.pojo.dto.TEntrustRecordDTO;
import com.bittrade.pojo.model.TEntrustRecord;
import com.bittrade.pojo.vo.TEntrustRecordVO;
import com.bittrade.pojo.vo.TRealTimeTransactionVO;
import com.core.framework.DTO.ReturnDTO;
import com.core.framework.base.controller.BaseController;

import io.swagger.annotations.ApiOperation;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tEntrustRecord" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TEntrustRecordController extends BaseController<TEntrustRecord, TEntrustRecordDTO, TEntrustRecordVO, ITEntrustRecordService> {
	@Reference
    private ITEntrustRecordService entrustRecordService;

    @ApiOperation(value = "查询用户成交记录")
    @GetMapping(value = "/queryDealEntrustByUserId/{userId}")
    @ResponseBody
    public ReturnDTO<List<TEntrustRecord>> queryDealEntrustByUserId(@PathVariable("userId") String userId) {
        try{
            return ReturnDTO.ok(entrustRecordService.queryDealEntrustByUserId(userId));
        }catch (Exception e){
            e.printStackTrace();
            return ReturnDTO.error("服务器异常");
        }

    }

    @ApiOperation(value = "实时成交")
    @GetMapping(value = "/realTimeTransaction/{currencyTradeId}")
    @ResponseBody
    public ReturnDTO<List<TRealTimeTransactionVO>> realTimeTransaction(@PathVariable("currencyTradeId") String currencyTradeId) {
        try{
            return ReturnDTO.ok(entrustRecordService.realTimeTransaction(currencyTradeId));
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnDTO.error("服务器异常");
        }

    }
}
