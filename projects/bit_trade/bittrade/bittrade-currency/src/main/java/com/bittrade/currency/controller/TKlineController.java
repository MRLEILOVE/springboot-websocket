package com.bittrade.currency.controller;

import java.util.List;

import com.core.framework.DTO.ReturnDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.currency.api.service.ITKlineService;
import com.bittrade.currency.dao.ITKlineDAO;
import com.bittrade.pojo.dto.QueryKLineDto;
import com.bittrade.pojo.dto.TKlineDTO;
import com.bittrade.pojo.model.TKline;
import com.bittrade.pojo.vo.QueryKLineVO;
import com.bittrade.pojo.vo.TKlineVO;
import com.core.framework.base.controller.BaseController;

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
    @ApiOperation(value = "k线查询",notes = "k线查询")
    @RequestMapping(value = "/queryKLine",method = RequestMethod.POST)
    @ResponseBody
    public ReturnDTO<List<QueryKLineVO>> queryKLine(@RequestBody QueryKLineDto queryKLineDto) {
        try{
            return ReturnDTO.ok(tKlineService.queryKLine(queryKLineDto));
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnDTO.error("服务器异常");
        }

    }
}
