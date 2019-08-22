package com.bittrade.currency.controller;

import com.bittrade.pojo.vo.AssetRecordTypeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.currency.api.service.ITWalletRecordService;
import com.bittrade.pojo.dto.AccountTypeDto;
import com.bittrade.pojo.dto.TWalletRecordDTO;
import com.bittrade.pojo.model.TWalletRecord;
import com.bittrade.pojo.vo.RecordVO;
import com.bittrade.pojo.vo.TWalletRecordVO;
import com.core.common.DTO.ReturnDTO;
import com.core.common.annotation.ALoginUser;
import com.core.framework.base.controller.BaseController;
import com.core.web.constant.entity.LoginUser;

import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@ResponseBody
@RequestMapping(value = { "/tWalletRecord" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TWalletRecordController extends BaseController<TWalletRecord, TWalletRecordDTO, TWalletRecordVO, ITWalletRecordService> {
    @Autowired
    private ITWalletRecordService walletRecordService;

    @ApiOperation(value="币币账户记录类型下拉框", notes="币币账户记录类型下拉框")
    @GetMapping(value = "/queryTypeForFund")
    @ResponseBody
    public ReturnDTO<List<AssetRecordTypeVO>> queryTypeForFund(){
        List<AssetRecordTypeVO> list = new ArrayList<>();
        //1-转入 2-转出 3-买入 4-卖出
        list.add(new AssetRecordTypeVO(1,"轉入"));
        list.add(new AssetRecordTypeVO(2,"轉出"));
        list.add(new AssetRecordTypeVO(3,"買入"));
        list.add(new AssetRecordTypeVO(4,"賣出"));
        return ReturnDTO.ok(list);
    }

    /**
     * 币币账户资产记录
     */
    @ApiOperation(value="币币账户资产记录", notes="币币账户资产记录")
    @PostMapping(value = "/queryBiBiAccountRecord")
    @ResponseBody
    public ReturnDTO<Page<RecordVO>> queryBiBiAccountRecord(@ALoginUser LoginUser user, @RequestBody AccountTypeDto dto){
        if(user == null){
            return ReturnDTO.ok(null);
        }
        return ReturnDTO.ok(walletRecordService.queryBiBiAccountRecord(user.getUser_id(),dto));
    }

}
