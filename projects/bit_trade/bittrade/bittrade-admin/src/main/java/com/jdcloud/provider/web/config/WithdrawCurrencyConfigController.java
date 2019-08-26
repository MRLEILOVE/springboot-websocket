package com.jdcloud.provider.web.config;


import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.provider.pojo.WithdrawCurrencyConfig;
import com.jdcloud.provider.pojo.vo.WithdrawCurrencyConfigVo;
import com.jdcloud.provider.service.AccountConfigService;
import com.jdcloud.provider.service.WithdrawCurrencyConfigService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.annotation.Log;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 单币种提币配置前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2019-07-25
 */
@Controller
@RequestMapping("/config/withdrawCurrencyConfig")
public class WithdrawCurrencyConfigController extends BaseController {

    @Autowired
    private WithdrawCurrencyConfigService withdrawCurrencyConfigService;
    @Autowired
    private AccountConfigService accountConfigService;

    private String prefix = "config/withdrawCurrencyConfig";

    /**
     * 单币种提币配置初始化
     *
     * @returnhouseInfo
     */
    @RequiresPermissions("config:withdrawCurrencyConfig:view")
    @GetMapping()
    public String withdrawCurrencyConfigInfo() {
        return prefix + "/list";
    }

    /**
     * 单币种提币配置列表
     *
     * @param
     * @return
     */
    @RequiresPermissions("config:withdrawCurrencyConfig:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo getWithdrawCurrencyConfig() {
        Page<WithdrawCurrencyConfigVo> vo = withdrawCurrencyConfigService.selectWithdrawCurrencyConfigPage(getPage());
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }

    /**
     * 初始化增加
     *
     * @return
     */
    @GetMapping("/add")
    @RequiresPermissions("config:withdrawCurrencyConfig:add")
    public String add(ModelMap mmap) {
        mmap.put("accountConfigList", accountConfigService.selectList(null));
        return prefix + "/add";
    }

    /**
     * 增加单币种提币配置
     * c
     */
    @Log(title = "单币种提币配置", buinessType = AnnotationEnum.BusinessType.INSERT)
    @RequiresPermissions("config:withdrawCurrencyConfig:add")
    @PostMapping("/save")
    @ResponseBody
    public Wrapper<String> saveWithdrawCurrencyConfig(WithdrawCurrencyConfig withdrawCurrencyConfig) {
        return withdrawCurrencyConfigService.saveOrUpdate(withdrawCurrencyConfig);
    }

    /**
     * 配置详情
     * C
     */
    @RequiresPermissions("config:withdrawCurrencyConfig:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        WithdrawCurrencyConfig withdrawCurrencyConfig = withdrawCurrencyConfigService.selectById(id);
        mmap.put("dto", withdrawCurrencyConfig);
        mmap.put("accountConfigList", accountConfigService.selectList(null));
        return prefix + "/edit";
    }

    /**
     * 修改单币种提币配置
     * c
     */
    @Log(title = "单币种提币配置", buinessType = AnnotationEnum.BusinessType.UPDATE)
    @RequiresPermissions("config:withdrawCurrencyConfig:edit")
    @PostMapping("/update")
    @ResponseBody
    public Wrapper<String> updateWithdrawCurrencyConfig(WithdrawCurrencyConfig withdrawCurrencyConfig) {
        return withdrawCurrencyConfigService.saveOrUpdate(withdrawCurrencyConfig);
    }

    /**
     * @Description: 删除单币种提币配置
     * @param ids :
     * @Author: zjun
     * @Date: 2019/7/25 14:37
     */
    @RequiresPermissions("config:withdrawCurrencyConfig:remove")
    @PostMapping("/remove")
    @ResponseBody
    public Wrapper<String> remove(String ids) {
        return toAjax(withdrawCurrencyConfigService.deleteByIds(ids));
    }
}

