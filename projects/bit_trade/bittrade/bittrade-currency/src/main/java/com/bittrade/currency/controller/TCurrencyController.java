package com.bittrade.currency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.api.dao.ITCurrencyDAO;
import com.bittrade.pojo.dto.TCurrencyDTO;
import com.bittrade.pojo.vo.TCurrencyVO;
import com.bittrade.pojo.model.TCurrency;
import com.bittrade.api.service.ITCurrencyService;
import com.core.framework.base.controller.BaseController;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tCurrency" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TCurrencyController extends BaseController<TCurrency, TCurrencyDTO, TCurrencyVO, ITCurrencyDAO, ITCurrencyService> {
    @Autowired
    private ITCurrencyService tCurrencyService;

    /**
     * 查找所有法币
     */
    @RequestMapping(value = "/findAllLegalCurrency",method = RequestMethod.GET)
    @ResponseBody
    public List<TCurrencyVO> findAllLegalCurrency() {
        return tCurrencyService.findAllLegalCurrency();
    }
}
