package com.bittrade.currency.controller;

import com.bittrade.currency.api.service.ITTransferDirectionService;
import com.bittrade.pojo.dto.TTransferDirectionDTO;
import com.bittrade.pojo.model.TTransferDirection;
import com.bittrade.pojo.vo.DirectionVO;
import com.bittrade.pojo.vo.TTransferDirectionVO;
import com.core.common.DTO.ReturnDTO;
import com.core.framework.base.controller.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tTransferDirection" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TTransferDirectionController extends BaseController<TTransferDirection, TTransferDirectionDTO, TTransferDirectionVO, ITTransferDirectionService> {

    @Autowired
    private ITTransferDirectionService transferDirectionService;

    @ApiOperation(value = "资金划转方向")
    @GetMapping(value = "/direction")
    @ResponseBody
    public ReturnDTO<List<DirectionVO>> direction() {
        return ReturnDTO.ok(transferDirectionService.direction());
    }
}
