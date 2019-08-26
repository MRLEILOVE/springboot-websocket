package com.jdcloud.provider.web.finance;


import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.BetaEnum;
import com.jdcloud.provider.dto.Withdraw;
import com.jdcloud.provider.dto.WithdrawStatistics;
import com.jdcloud.provider.dto.WithdrawWalletBillDto;
import com.jdcloud.provider.pojo.vo.MentionMoney;
import com.jdcloud.provider.pojo.vo.WithdrawWalletBillVo;
import com.jdcloud.provider.service.IWUserWalletBillService;
import com.jdcloud.provider.service.WithdrawWalletBillService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 平台提币钱包账单 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2019-07-31
 */
@Controller
@RequestMapping("/finance/withdrawWalletBill")
public class WithdrawWalletBillController extends BaseController {

    private String		prefix	= "finance/withdrawWalletBill";

    @Autowired
    private WithdrawWalletBillService withdrawWalletBillService;
    @Autowired
    private IWUserWalletBillService iWUserWalletBillService;


    /**
     * 平台提币 初始化
     * @param mmap
     * @return
     */
    @RequiresPermissions("finance:withdrawWalletBill:view")
    @GetMapping()
    public String withdraw(ModelMap mmap) {
        MentionMoney money =   withdrawWalletBillService.getMentionMoneyCount();
        mmap.put("withdrawStatistics",money);
        return prefix + "/list";
    }

    /**
     * 平台提现记录列表<br>
     * C
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WithdrawWalletBillDto dto){
        dto.setType(2); // 出账类型
        dto.setUserId(5); // 接口约定 小于5 的都是平台
        Page<WithdrawWalletBillVo> page = withdrawWalletBillService.selectWithdrawWalletBill(getPage(),dto);
        return new TableDataInfo(page.getRecords(), page.getTotal());
    }

    /**
     * 提币记录 详情
     */
    @RequiresPermissions("finance:withdrawWalletBill:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap) {
        WithdrawWalletBillVo vo =  withdrawWalletBillService.selectWithdrawWalletBillDetail(id);
        mmap.put("vo", vo);
        return prefix + "/detail";
    }


    /**
     * 平台充值 初始化
     * @param mmap
     * @return
     */
    @RequiresPermissions("finance:withdrawRecharge:view")
    @GetMapping("/withdrawRechargeListInfo")
    public String withdrawRecharge(ModelMap mmap) {
        MentionMoney money =   withdrawWalletBillService.getCoinCount();
        mmap.put("withdrawStatistics",money);
        return prefix + "/withdrawRechargeList";
    }


    /**
     * 平台充值记录列表<br>
     * C
     */
    @PostMapping("/withdrawRechargeList")
    @ResponseBody
    public TableDataInfo getwithdrawRechargeList(WithdrawWalletBillDto dto){
        dto.setType(1); // 出账类型
        dto.setUserId(5); // 接口约定 小于5 的都是平台
        Page<WithdrawWalletBillVo> page = withdrawWalletBillService.selectWithdrawWalletBill(getPage(),dto);
        return new TableDataInfo(page.getRecords(), page.getTotal());
    }

    /**
     * 平台充值记录
     * @param dto
     * @return
     * C
     */
    @PostMapping("/withdrawRechargeListNew")
    @ResponseBody
    public TableDataInfo getwithdrawRechargeListNew(WithdrawWalletBillDto dto){
        dto.setType(1); // 出账类型
        dto.setUserId(5); // 接口约定 小于5 的都是平台
        Page<WithdrawWalletBillVo> page =  iWUserWalletBillService.getwithdrawRechargeListNew(getPage(),dto);
        return new TableDataInfo(page.getRecords(), page.getTotal());
    }

    /**
     * 平台充值详情
     */
    @RequiresPermissions("finance:withdrawRecharge:detail")
    @GetMapping("/getWithdrawRechargeDetail/{id}")
    public String getWithdrawRechargeDetail(@PathVariable("id") Long id, ModelMap mmap)
    {
        WithdrawWalletBillVo vo =  iWUserWalletBillService.getWithdrawRechargeDetail(id);
        mmap.put("vo", vo);
        return prefix + "/withdrawRechargeDetail";
    }

}

