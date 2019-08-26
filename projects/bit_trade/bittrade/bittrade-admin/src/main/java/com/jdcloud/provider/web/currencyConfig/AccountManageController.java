package com.jdcloud.provider.web.currencyConfig;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.base.enums.BetaEnum;
import com.jdcloud.base.enums.C2cEnum;
import com.jdcloud.base.enums.HouseEnum;
import com.jdcloud.provider.dto.AccountManageConfigDto;
import com.jdcloud.provider.dto.HouseDto;
import com.jdcloud.provider.dto.HouseProductImgDto;
import com.jdcloud.provider.pojo.AccountConfig;
import com.jdcloud.provider.pojo.AccountManage;
import com.jdcloud.provider.pojo.AccountManageConfig;
import com.jdcloud.provider.pojo.ComboGroup;
import com.jdcloud.provider.pojo.vo.AccountManageConfigVo;
import com.jdcloud.provider.pojo.vo.HouseVo;
import com.jdcloud.provider.service.AccountConfigService;
import com.jdcloud.provider.service.AccountManageConfigService;
import com.jdcloud.provider.service.AccountManageService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.annotation.Log;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p> 账户管理
 * 前端控制器
 * </p>
 *
 * @author c
 * @since 2019-07-24
 */
@Controller
@RequestMapping("/currencyConfig/accountManage")
public class AccountManageController extends BaseController {
    private String prefix = "currencyConfig/accountManage";

    @Autowired
    private AccountManageService accountManageService;

    @Autowired
    private AccountManageConfigService accountManageConfigService;

    @Autowired
    private AccountConfigService accountConfigService;

    /**
     * 初始化账户币种
     *
     * @return
     */
    @RequiresPermissions("accountManageInfo:view")
    @GetMapping()
    public String accountManageInfo() {
        return prefix + "/configList";
    }

    /**
     * 账户币种配置列表
     *
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo getAccountConfig(AccountManageConfigDto dto) {
        Page<AccountManageConfigVo> vo = accountManageConfigService.getAccountConfig(getPage(), dto);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }


    /**
     * 禁用账户币种
     */
    @Log(title = "禁用币种", buinessType = AnnotationEnum.BusinessType.UPDATE)
//    @RequiresPermissions("AccountConfig:updateAccountConfig")
    @PostMapping("/updateAccountConfig/{id}")
    @ResponseBody
    public Wrapper<String> updateAccountConfig(@PathVariable("id") Integer id) {
        AccountManageConfig config = accountManageConfigService.selectById(id);
        if (C2cEnum.status.NORMAL.getCode().equals(config.getStatus())) {
            config.setStatus(C2cEnum.status.DISABLE.getCode());
        } else {
            config.setStatus(C2cEnum.status.NORMAL.getCode());
        }
        boolean bo = accountManageConfigService.updateById(config);
        if (!bo) {
            return WrapMapper.error("修改失败");
        }
        return WrapMapper.ok("修改成功");
    }

    /**
     * 保存账户币种配置初始化
     *
     * @return
     */
    @RequiresPermissions("accountConfig:addInfo")
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Integer id, ModelMap mmap) {
        EntityWrapper entity = new EntityWrapper();
        entity.eq("top_up_coin", C2cEnum.topUpCoin.YES.getCode());
        entity.orderBy("sort", false);
        List<AccountConfig> list = accountConfigService.selectList(entity);
        mmap.put("id", id);
        mmap.put("accountConfigList", list);
        return prefix + "/add";
    }
    /**
     * 保存账户币种配置
     */
    @Log(title = "保存账户币种配置", buinessType = AnnotationEnum.BusinessType.INSERT)
//    @RequiresPermissions("accountConfig:addAccountConfig")
    @PostMapping("/addHouseProduct")
    @ResponseBody
    public Wrapper<String> addAccountConfig(AccountManageConfig accountManageConfig) {
        return accountManageConfigService.addAccountConfig(accountManageConfig);
    }


    /**
     * 初始化beta账户
     *
     * @return
     */
    @RequiresPermissions("betaAccountManageInfo:view")
    @GetMapping("/betaAccountManageInfo")
    public String betaAccountManageInfo() {
        return "currencyConfig/betaAccountManage/configList";
     }
    /**
     * 初始化资金账户
     *
     * @return
     */
    @RequiresPermissions("capitalAccountManageInfo:view")
    @GetMapping("/capitalAccountManageInfo")
    public String capitalAccountManageInfo() {
        return "currencyConfig/capitalAccountManage/configList";
    }

}

