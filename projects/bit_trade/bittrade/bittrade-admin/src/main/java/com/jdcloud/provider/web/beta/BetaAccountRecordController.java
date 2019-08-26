package com.jdcloud.provider.web.beta;


import com.jdcloud.provider.web.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 我的beta资产流水表 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2019-04-24
 */
@Controller
@RequestMapping("/beta/betaAccountRecord")
public class BetaAccountRecordController extends BaseController {

    private String prefix = "beta/betaAccountRecord";

    @RequiresPermissions("betaAccountRecord:view")
    @GetMapping()
    public String list() {
        return prefix + "/list";
    }




}

