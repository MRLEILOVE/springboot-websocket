package com.bittrade.currency.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
