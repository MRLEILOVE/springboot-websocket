package com.bittrade.currency.controller;

import java.util.List;

import com.core.framework.DTO.ReturnDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.api.service.ITCurrencyService;
import com.bittrade.currency.dao.ITCurrencyDAO;
import com.bittrade.pojo.dto.TCurrencyDTO;
import com.bittrade.pojo.model.TCurrency;
import com.bittrade.pojo.vo.TCurrencyVO;
import com.core.framework.base.controller.BaseController;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tCurrency" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TCurrencyController extends BaseController<TCurrency, TCurrencyDTO, TCurrencyVO, ITCurrencyDAO, ITCurrencyService<ITCurrencyDAO>> {
    @Autowired
    private ITCurrencyService<ITCurrencyDAO> tCurrencyService;

    @ApiOperation(value = "查找所有法币")
    @RequestMapping(value = "/findAllLegalCurrency",method = RequestMethod.GET)
    @ResponseBody
    public ReturnDTO<List<TCurrency>> findAllLegalCurrency() {
        try{
            return ReturnDTO.ok(tCurrencyService.findAllLegalCurrency());
        }catch (Exception e){
            e.printStackTrace();
            return ReturnDTO.error("服务器异常");
        }

    }
}
