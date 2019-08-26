package com.jdcloud.provider.web.betaHouse;


import com.jdcloud.provider.service.HouseComboSetDetailService;
import com.jdcloud.provider.web.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 套餐设置详细表 前端控制器
 * </p>
 *
 * @author CC
 * @since 2019-06-18
 */
@Controller
@RequestMapping("/betaHouse/comboSetDetail")
public class HouseComboSetDetailController extends BaseController {

    @Autowired
    private HouseComboSetDetailService houseComboSetDetailService;

    private String prefix = "betaHouse/comboSetDetail";

    /**
     * 套餐设置表初始化
     * c
     * 2019-06-18
     */
    @RequiresPermissions("comboSetDetailInfo:view")
    @GetMapping()
    public String comboSetDetailInfo(){
        return prefix + "/list";
    }

}

