package com.jdcloud.provider.web.userWallet;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.TWalletFundAccountDto;
import com.jdcloud.provider.dto.WalletFundDto;
import com.jdcloud.provider.pojo.TWalletFundAccount;
import com.jdcloud.provider.pojo.vo.TWalletFundAccountVo;
import com.jdcloud.provider.service.ITwalletFundAccountService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//用户资金详情
@Controller
@RequestMapping("/userWallet/account")
public class UserAccountController extends BaseController {
    private String prefix = "userWallet/account";

    @Autowired
    private ITwalletFundAccountService iTwalletFundAccountService;

    @RequiresPermissions("newWithDrawInfo:view")
    @GetMapping()
    public String newWithDrawInfo() {
        return prefix + "/list";
    }

    /**
     * 获取提币列表
     *
     * @param
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WalletFundDto walletFundDto) {
//        List<TWalletFundAccount> list=iTwalletFundAccountService.selectByParam(walletFundDto);
        Page<TWalletFundAccount> page = iTwalletFundAccountService.selectPage(getPage());
        return new TableDataInfo(page.getRecords(), page.getTotal());
    }

    /**
     * 提币列表新
     */
    @PostMapping("/lists")
    @ResponseBody
    public TableDataInfo getTWalletFundAccount(TWalletFundAccountDto tWalletFundAccountDto) {
        Page<TWalletFundAccountVo> page = iTwalletFundAccountService.getTWalletFundAccount(getPage(), tWalletFundAccountDto);
        return new TableDataInfo(page.getRecords(), page.getTotal());
    }


}
