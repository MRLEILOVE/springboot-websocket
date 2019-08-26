package com.jdcloud.provider.web.funds;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AuditEnum;
import com.jdcloud.provider.dto.ActionDto;
import com.jdcloud.provider.dto.TOrderDto;
import com.jdcloud.provider.pojo.TOrder;
import com.jdcloud.provider.pojo.TWalletFundAccount;
import com.jdcloud.provider.pojo.TWalletFundAccountRecord;
import com.jdcloud.provider.service.ITOrderService;
import com.jdcloud.provider.service.ITwalletFundAccountRecordService;
import com.jdcloud.provider.service.ITwalletFundAccountService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.StringUtils;
import com.jdcloud.util.wrapper.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//公司流水
@Controller
@RequestMapping("/funds/capitalPool")
public class FundsAccountController extends BaseController {


    private String					prefix	= "funds/capitalPool";

    @Autowired
    private ITwalletFundAccountService iTwalletFundAccountService;

    @Autowired
    private ITwalletFundAccountRecordService iTwalletFundAccountRecordService;

    /**
     * 资金池
     * @return
     */
    @RequiresPermissions("newWithDrawInfo:view")
    @GetMapping()
    public String newWithDrawInfo() {
        return prefix + "/list";
    }

    /**
     * 资金池统计
     * @param
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ActionDto actionDto) {
        EntityWrapper<TWalletFundAccountRecord>entityWrapper=new EntityWrapper<>();
    //    entityWrapper.eq("user_id",1).eq("user_id",2);
        if(StringUtil.isNotBlank(actionDto.getActionDate())){
            entityWrapper.ge("create_time",actionDto.getActionDate());
        }
        if(StringUtil.isNotBlank(actionDto.getEndDate())){
            entityWrapper.le("create_time",actionDto.getEndDate());
        }
        if(StringUtil.isNotBlank(actionDto.getName())){
            entityWrapper.eq("currency",actionDto.getName());
        }
        entityWrapper.eq("(user_id = {0}", 1);
        entityWrapper.or("user_id = {0})", 2);
        Page<TWalletFundAccountRecord> page = iTwalletFundAccountRecordService.selectPage(getPage(),entityWrapper);
        return new TableDataInfo(page.getRecords(), page.getTotal());
    }





}
