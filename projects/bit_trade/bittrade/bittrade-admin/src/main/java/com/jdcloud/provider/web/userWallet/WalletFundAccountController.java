package com.jdcloud.provider.web.userWallet;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.C2cEnum;
import com.jdcloud.provider.dto.WUserWalletBillDto;
import com.jdcloud.provider.dto.WalletFundAccountDto;
import com.jdcloud.provider.pojo.AccountConfig;
import com.jdcloud.provider.pojo.WalletFundAccount;
import com.jdcloud.provider.pojo.WalletFundAccountRecord;
import com.jdcloud.provider.pojo.vo.WWithdrawWalletBillVo;
import com.jdcloud.provider.pojo.vo.WalletFundAccountRecordVo;
import com.jdcloud.provider.pojo.vo.WalletFundAccountVo;
import com.jdcloud.provider.service.AccountConfigService;
import com.jdcloud.provider.service.WalletFundAccountRecordService;
import com.jdcloud.provider.service.WalletFundAccountService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 资金账户表 前端控制器
 * </p>
 *
 * @author c
 * @since 2019-07-23
 */
@Controller
@RequestMapping("/userWallet/walletFundAccount")
public class WalletFundAccountController extends BaseController {

    private String					prefix	= "userWallet/walletFundAccount";

    @Autowired
    private WalletFundAccountService walletFundAccountService;
    @Autowired
    private AccountConfigService accountConfigService;
    @Autowired
    private WalletFundAccountRecordService walletFundAccountRecordService;

    /**
     * 页面初始化
     * @param mmap
     * @return
     */
    @RequiresPermissions("walletFundAccountInfo:view")
    @GetMapping()
    public String walletFundAccountInfoInfo(ModelMap mmap) {
        // 查询充币有哪些
        EntityWrapper entity = new EntityWrapper();
        entity.eq("top_up_coin", C2cEnum.drawCoin.YES.getCode());
        entity.orderBy("sort", false);
        List<AccountConfig> list = accountConfigService.selectList(entity);
        mmap.put("list", list);
        return prefix + "/list";
    }

    /**
     * 用户资产
     * @param dto
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo getWalletFundAccount(WalletFundAccountDto dto) {
        Page<WalletFundAccountVo> page = walletFundAccountService.getWithdrawWalletBill(getPage(),dto);
        return new TableDataInfo(page.getRecords(), page.getTotal());
    }


    /**
     * 初始化用户资产流水
     * c
     * 2019-7-23
     */
    @RequiresPermissions("walletFundAccountInfo:view")
    @GetMapping("/walletFundAccountRecordInfo/{id}")
    public String walletFundAccountRecordInfo(@PathVariable("id") Integer id,ModelMap mmap) {
        WalletFundAccount account = walletFundAccountService.selectById(id);
        mmap.put("vo", account);
        return  prefix +"/recordList";
    }

    /**
     * 查询流水
     * @param dto
     * @return
     */
    @PostMapping("/recordList")
    @ResponseBody
    public TableDataInfo getWalletFundAccountRecord(WalletFundAccountDto dto) {
        Page<WalletFundAccountRecordVo> page = walletFundAccountRecordService.getWalletFundAccountRecord(getPage(),dto);
        return new TableDataInfo(page.getRecords(), page.getTotal());
    }





}

