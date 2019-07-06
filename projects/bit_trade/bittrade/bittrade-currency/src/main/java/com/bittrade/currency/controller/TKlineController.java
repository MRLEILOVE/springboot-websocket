package com.bittrade.currency.controller;

import com.bittrade.pojo.dto.QueryKLineDto;
import com.bittrade.pojo.vo.QueryKLineVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.api.dao.ITKlineDAO;
import com.bittrade.pojo.dto.TKlineDTO;
import com.bittrade.pojo.vo.TKlineVO;
import com.bittrade.pojo.model.TKline;
import com.bittrade.api.service.ITKlineService;
import com.core.framework.base.controller.BaseController;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tKline" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TKlineController extends BaseController<TKline, TKlineDTO, TKlineVO, ITKlineDAO, ITKlineService> {

    @Autowired
    private ITKlineService tKlineService;

    /**
     * k线查询
     */
    @RequestMapping(value = "/queryKLine",method = RequestMethod.POST)
    @ResponseBody
    public List<QueryKLineVO> queryKLine(@RequestBody QueryKLineDto queryKLineDto) {
        return tKlineService.queryKLine(queryKLineDto);
    }
}
