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

import com.bittrade.api.service.ITEntrustService;
import com.bittrade.api.service.ITTrustService;
import com.bittrade.currency.dao.ITEntrustDAO;
import com.bittrade.pojo.dto.DealDTO;
import com.bittrade.pojo.dto.TEntrustDTO;
import com.bittrade.pojo.model.TEntrust;
import com.bittrade.pojo.vo.TEntrustInfoVO;
import com.bittrade.pojo.vo.TEntrustVO;
import com.core.framework.DTO.ReturnDTO;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tEntrust" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TEntrustController extends BaseController<TEntrust, TEntrustDTO, TEntrustVO, ITEntrustDAO, ITEntrustService<TEntrust, TEntrustDTO, TEntrustVO, ITEntrustDAO>> {
    @Autowired
    private ITTrustService trustService;

    //    @ApiOperation(value = "查询用户当前委托")
    @GetMapping(value = "/queryPresentEntrustByUserId/{userId}")
    @ResponseBody
    public List<TEntrustVO> queryPresentEntrustByUserId(@PathVariable("userId") String userId) {
        return trustService.queryPresentEntrustByUserId(userId);
    }

    //    @ApiOperation(value = "查询用户历史委托")
    @GetMapping(value = "/queryHistoryEntrustByUserId/{userId}")
    @ResponseBody
    public List<TEntrustVO> queryHistoryEntrustByUserId(@PathVariable("userId") String userId) {
        return trustService.queryHistoryEntrustByUserId(userId);
    }

    //    @ApiOperation(value = "买/卖交易对")
    @PostMapping(value = "/deal")
    @ResponseBody
    public String queryDealEntrustByUserId(@RequestBody DealDTO dealDTO) {
        return trustService.deal(dealDTO);
    }

    /**
     * 查询用户的委托单成交明细
     */
    @RequestMapping(value = "/queryEntrustInfoByUserId/{userId}/{entrustId}",method = RequestMethod.GET)
    @ResponseBody
    public TEntrustInfoVO queryEntrustInfoByUserId(@PathVariable("userId") String userId, @PathVariable("entrustId") String entrustId) {
        return trustService.queryEntrustInfoByUserId(userId,entrustId);
    }

    /**
     * 用户撤单
     */
    @RequestMapping(value = "/killOrder/{entrustId}",method = RequestMethod.GET)
    @ResponseBody
    public ReturnDTO killOrder(@PathVariable("entrustId") String entrustId) {
        return trustService.killOrder(entrustId);
    }
}
