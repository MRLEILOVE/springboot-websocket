package com.bittrade.currency.controller;

import com.bittrade.pojo.vo.TransactionPairVO;
import com.core.framework.DTO.ReturnDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.bittrade.api.__default.DAO.IDefaultTCurrencyOptionalDAO;
import com.bittrade.api.service.ITCurrencyOptionalService;
import com.bittrade.pojo.dto.TCurrencyOptionalDTO;
import com.bittrade.pojo.model.TCurrencyOptional;
import com.bittrade.pojo.vo.TCurrencyOptionalVO;
import com.core.framework.base.controller.BaseController;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tCurrencyOptional" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TCurrencyOptionalController extends BaseController<TCurrencyOptional, TCurrencyOptionalDTO, TCurrencyOptionalVO, IDefaultTCurrencyOptionalDAO, ITCurrencyOptionalService> {
    @Autowired
    private ITCurrencyOptionalService currencyOptionalService;

    @ApiOperation(value="查询用户自选的交易对",notes="传用户id")
    @RequestMapping(value="/findOptionalTradeByUserId/{userId}",method = RequestMethod.GET)
    @ResponseBody
    public List<TransactionPairVO> findOptionalByUserId(@PathVariable("userId") String userId) {
        return currencyOptionalService.findOptionalByUserId(userId);
    }

    @ApiOperation(value="添加自选",notes="传用户id、交易对id")
    @RequestMapping(value="/addOptional",method = RequestMethod.POST)
    @ResponseBody
    public ReturnDTO addOptional(@RequestBody TCurrencyOptionalDTO currencyOptionalDTO) {
        return currencyOptionalService.addOptional(currencyOptionalDTO);
    }

    @ApiOperation(value="删除自选",notes="传用户id、交易对id")
    @RequestMapping(value="/deleteOptional",method = RequestMethod.POST)
    @ResponseBody
    public ReturnDTO deleteOptional(@RequestBody TCurrencyOptionalDTO currencyOptionalDTO) {
        return currencyOptionalService.deleteOptional(currencyOptionalDTO);
    }
}
