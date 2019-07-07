package com.bittrade.currency.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.api.service.ITCurrencyOptionalService;
import com.bittrade.currency.dao.ITCurrencyOptionalDAO;
import com.bittrade.pojo.dto.TCurrencyOptionalDTO;
import com.bittrade.pojo.model.TCurrencyOptional;
import com.bittrade.pojo.vo.TCurrencyOptionalVO;
import com.bittrade.pojo.vo.TransactionPairVO;
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
@RequestMapping(value = { "/tCurrencyOptional" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TCurrencyOptionalController extends BaseController<TCurrencyOptional, TCurrencyOptionalDTO, TCurrencyOptionalVO, ITCurrencyOptionalDAO, ITCurrencyOptionalService<TCurrencyOptional, TCurrencyOptionalDTO, TCurrencyOptionalVO, ITCurrencyOptionalDAO>> {
    @Autowired
    private ITCurrencyOptionalService<TCurrencyOptional, TCurrencyOptionalDTO, TCurrencyOptionalVO, ITCurrencyOptionalDAO> currencyOptionalService;

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
