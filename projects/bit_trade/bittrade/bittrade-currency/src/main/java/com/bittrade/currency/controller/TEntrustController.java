package com.bittrade.currency.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.currency.api.service.ITEntrustService;
import com.bittrade.currency.dao.ITEntrustDAO;
import com.bittrade.pojo.dto.DealDTO;
import com.bittrade.pojo.dto.TEntrustDTO;
import com.bittrade.pojo.model.TEntrust;
import com.bittrade.pojo.vo.TEntrustInfoVO;
import com.bittrade.pojo.vo.TEntrustVO;
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
@RequestMapping(value = { "/tEntrust" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TEntrustController extends BaseController<TEntrust, TEntrustDTO, TEntrustVO, ITEntrustDAO, ITEntrustService> {
    @Autowired
    private ITEntrustService entrustService;

    @ApiOperation(value = "查询用户当前委托")
    @GetMapping(value = "/queryPresentEntrustByUserId/{userId}")
    @ResponseBody
    public ReturnDTO<List<TEntrustVO>> queryPresentEntrustByUserId(@PathVariable("userId") String userId) {
        try{
            return ReturnDTO.ok(entrustService.queryPresentEntrustByUserId(userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnDTO.error("服务器异常");
        }

    }

    @ApiOperation(value = "查询用户历史委托")
    @GetMapping(value = "/queryHistoryEntrustByUserId/{userId}")
    @ResponseBody
    public ReturnDTO<List<TEntrustVO>> queryHistoryEntrustByUserId(@PathVariable("userId") String userId) {
        try{
            return ReturnDTO.ok(entrustService.queryHistoryEntrustByUserId(userId));
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnDTO.error("服务器异常");
        }

    }

    @ApiOperation(value = "买/卖交易对")
    @PostMapping(value = "/deal")
    @ResponseBody
    public ReturnDTO<String> queryDealEntrustByUserId(@RequestBody DealDTO dealDTO) {
        return entrustService.deal(dealDTO);
    }

    @ApiOperation(value = "查询用户的委托单成交明细")
    @RequestMapping(value = "/queryEntrustInfoByUserId/{userId}/{entrustId}",method = RequestMethod.GET)
    @ResponseBody
    public TEntrustInfoVO queryEntrustInfoByUserId(@PathVariable("userId") String userId, @PathVariable("entrustId") String entrustId) {
        return entrustService.queryEntrustInfoByUserId(userId,entrustId);
    }

    @ApiOperation(value = "用户撤单")
    @RequestMapping(value = "/killOrder/{entrustId}",method = RequestMethod.GET)
    @ResponseBody
    public ReturnDTO<Object> killOrder(@PathVariable("entrustId") String entrustId) {
        return entrustService.killOrder(entrustId);
    }
}
