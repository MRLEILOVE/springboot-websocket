package com.jdcloud.provider.web.userWallet;


import com.jdcloud.provider.web.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 资金账户流水表 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2019-07-23
 */
@Controller
@RequestMapping("/userWallet/walletFundAccountRecord")
public class WalletFundAccountRecordController extends BaseController {

    private String	prefix	= "userWallet/walletFundAccount";
}

