package com.bittrade.c2c.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.c2c.service.ITLegalCurrencyRecordService;
import com.bittrade.pojo.dto.AccountTypeDto;
import com.bittrade.pojo.dto.TLegalCurrencyRecordDTO;
import com.bittrade.pojo.model.TLegalCurrencyRecord;
import com.bittrade.pojo.vo.AssetRecordTypeVO;
import com.bittrade.pojo.vo.RecordVO;
import com.bittrade.pojo.vo.TLegalCurrencyRecordVO;
import com.core.common.DTO.ReturnDTO;
import com.core.common.annotation.ALoginUser;
import com.core.framework.base.controller.BaseController;
import com.core.web.constant.entity.LoginUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tLegalCurrencyRecord" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TLegalCurrencyRecordController extends BaseController<TLegalCurrencyRecord, TLegalCurrencyRecordDTO, TLegalCurrencyRecordVO, ITLegalCurrencyRecordService> {

    @Autowired
    private ITLegalCurrencyRecordService currencyRecordService;

    @ApiOperation(value="法币账户记录类型下拉框", notes="法币账户记录类型下拉框")
    @GetMapping(value = "/queryType")
    @ResponseBody
    public ReturnDTO<List<AssetRecordTypeVO>> queryTypeForFund(){
        List<AssetRecordTypeVO> list = new ArrayList<>();
        //1-转入 2-转出
        list.add(new AssetRecordTypeVO(1,"轉入"));
        list.add(new AssetRecordTypeVO(2,"轉出"));
        return ReturnDTO.ok(list);
    }

    @PostMapping("/queryAccountRecord")
    @ApiOperation(value="查詢法币账户划转记录")
    @ResponseBody
    public ReturnDTO<Page<RecordVO>> queryAccountRecord(@ALoginUser LoginUser user, @RequestBody AccountTypeDto accountTypeDto){
        Long userId = user == null ? null : user.getUser_id();
        if(userId == null){
            return ReturnDTO.ok(null);
        }
        Page<RecordVO> page = currencyRecordService.queryAccountRecord(userId,accountTypeDto);
        return ReturnDTO.ok(page);
    }


}
