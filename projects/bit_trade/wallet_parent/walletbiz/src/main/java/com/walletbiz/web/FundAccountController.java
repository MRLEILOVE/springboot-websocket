package com.walletbiz.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.core.common.DTO.ReturnDTO;
import com.core.web.tool.WebUtil;
import com.walletbiz.dto.AccountTypeDto;
import com.walletbiz.dto.RecordDto;
import com.walletbiz.pojo.TWalletFundAccountRecord;
import com.walletbiz.service.ITwalletFundAccountRecordService;
import com.walletbiz.service.ITwalletFundAccountService;
import com.walletbiz.vo.RecordVO;

import io.swagger.annotations.ApiOperation;

/**
 * 资金账户
 */
@RestController
@RequestMapping("/fundAcoount")
public class FundAccountController {

    @Autowired
    private ITwalletFundAccountService walletFundAccountService;
    @Autowired
    private ITwalletFundAccountRecordService walletFundAccountRecordService;

    /*@ApiOperation(value="查询用户的资金账户", notes="查询用户的资金账户")
    @GetMapping(value = "/queryAccountByUserId/{userId}")
    @ResponseBody
    public Wrapper<AccountVO> queryAccountByUserId(@PathVariable("userId")Integer userId){
        return ReturnDTO.ok(walletFundAccountService.queryAccountByUserId(userId));
    }*/

    @PostMapping("/recordList")
    @ApiOperation(value="资金账户列表", notes="资金账户列表")
    @ResponseBody
    public ReturnDTO<IPage<TWalletFundAccountRecord>> recordList(@RequestBody RecordDto recordDto){
    	QueryWrapper<TWalletFundAccountRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",recordDto.getUserId()).eq("currency",recordDto.getCurrency());
        Page<TWalletFundAccountRecord> page = new Page<>(recordDto.getCurrent(), recordDto.getSize());
        IPage<TWalletFundAccountRecord> page1 = walletFundAccountRecordService.page(page, wrapper);
        return ReturnDTO.ok(page1);
    }

    @PostMapping("/queryCurrencyInfo")
    @ApiOperation(value="当前币种信息", notes="当前币种信息")
    @ResponseBody
    public ReturnDTO<IPage<TWalletFundAccountRecord>> queryCurrencyInfo(@RequestBody RecordDto recordDto){
        QueryWrapper<TWalletFundAccountRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",recordDto.getUserId()).eq("currency",recordDto.getCurrency());
        Page<TWalletFundAccountRecord> page = new Page<>(recordDto.getCurrent(), recordDto.getSize());
        IPage<TWalletFundAccountRecord> page1 = walletFundAccountRecordService.page(page, wrapper);
        return ReturnDTO.ok(page1);
    }

    @PostMapping("/queryFundAccountRecord")
    @ApiOperation(value="查詢资金账户记录")
    @ResponseBody
    public ReturnDTO<Page<RecordVO>> queryFundAccountRecord(@RequestBody AccountTypeDto accountTypeDto){
        if(WebUtil.getLoginUser()==null){
            return ReturnDTO.error("用户未登录");
        }
        Long userId = WebUtil.getLoginUser().getUser_id();
        Page<RecordVO> page = walletFundAccountService.queryFundAccountRecord(userId,accountTypeDto);
        ReturnDTO<Page<RecordVO>> ok = ReturnDTO.ok(page);
        return ok;
    }
}
