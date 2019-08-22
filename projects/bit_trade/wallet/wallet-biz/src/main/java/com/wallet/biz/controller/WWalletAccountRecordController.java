package com.wallet.biz.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.pojo.dto.AccountTypeDto;
import com.bittrade.pojo.vo.AssetRecordTypeVO;
import com.bittrade.pojo.vo.RecordVO;
import com.core.common.DTO.ReturnDTO;
import com.core.common.annotation.ALoginUser;
import com.core.web.constant.entity.LoginUser;
import com.wallet.biz.api.service.IWWalletAccountRecordService;
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
@RequestMapping(value = { "/wWalletAccountRecord" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WWalletAccountRecordController {
    @Autowired
    private IWWalletAccountRecordService walletAccountRecordService;

    @ApiOperation(value="资金账户记录类型下拉框", notes="资金账户记录类型下拉框")
    @GetMapping(value = "/queryTypeForFund")
    @ResponseBody
    public ReturnDTO<List<AssetRecordTypeVO>> queryTypeForFund(){
        List<AssetRecordTypeVO> list = new ArrayList<>();
        //1.提币  2.充币  3.转入 4.转出
        list.add(new AssetRecordTypeVO(1,"提幣"));
        list.add(new AssetRecordTypeVO(2,"充幣"));
        list.add(new AssetRecordTypeVO(3,"轉入"));
        list.add(new AssetRecordTypeVO(4,"轉出"));
        return ReturnDTO.ok(list);
    }

    @PostMapping("/queryFundAccountRecord")
    @ApiOperation(value="查詢资金账户记录")
    @ResponseBody
    public ReturnDTO<Page<RecordVO>> queryFundAccountRecord(@ALoginUser LoginUser user,@RequestBody AccountTypeDto accountTypeDto){
        Long userId = user == null ? null : user.getUser_id();
        if(userId == null){
            return ReturnDTO.ok(null);
        }
        Page<RecordVO> page = walletAccountRecordService.queryFundAccountRecord(userId,accountTypeDto);
        return ReturnDTO.ok(page);
    }
}
