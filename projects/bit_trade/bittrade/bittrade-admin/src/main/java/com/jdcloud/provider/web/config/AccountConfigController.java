package com.jdcloud.provider.web.config;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.AccountConfigDto;
import com.jdcloud.provider.pojo.AccountConfig;
import com.jdcloud.provider.service.AccountConfigService;
import com.jdcloud.provider.web.base.BaseController;
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
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2019-05-20
 */
@Controller
@RequestMapping(value = "/config/accountConfig")
public class AccountConfigController extends BaseController {

    private String prefix = "config/accountConfig";

    @Autowired
    private AccountConfigService accountConfigService;

    @RequiresPermissions("config:accountConfig:view")
    @GetMapping()
    public String parameterConfig() {
        return prefix + "/accountConfig";
    }

    /**
     * @Description: 钱包币种配置列表页面
     * @Author: senghor
     * @Date: 2019/5/20 0020 9:25
     */
    @RequiresPermissions("config:accountConfig:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AccountConfigDto accountConfigDto) {
        Page<AccountConfig> page = accountConfigService.selectAccountConfigPageList(getPage(), accountConfigDto);
        return new TableDataInfo(page.getRecords(), page.getTotal());
    }

    /**
     * @Description: 增加页面
     * @Author: senghor
     * @Date: 2019/5/20 0020 9:29
     */
    @RequiresPermissions("config:accountConfig:add")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @RequiresPermissions("config:accountConfig:edit")
    @GetMapping("/edit/{accountConfigId}")
    public String edit(@PathVariable("accountConfigId") String accountConfigId, ModelMap mmap) {
        AccountConfig accountConfig = accountConfigService.selectById(accountConfigId);
        mmap.put("accountConfig", accountConfig);
        return prefix + "/edit";
    }

    /**
     * @Description: 新增/更新数据
     * @Author: senghor
     * @Date: 2019/5/20 0020 9:30
     */
    @RequiresPermissions("config:accountConfig:saveOrUpdate")
    @PostMapping(value = "/saveOrUpdate")
    @ResponseBody
    public Wrapper<String> saveOrUpdate(AccountConfig accountConfig) {
        return accountConfigService.saveOrUpdate(accountConfig);
    }

    /**
     * @Description: 删除国家区域码
     * @Author: senghor
     * @Date: 2019/5/20 0020 9:36
     */
    @RequiresPermissions("config:accountConfig:remove")
    @PostMapping("/remove")
    @ResponseBody
    public Wrapper<String> remove(String ids) {
        return toAjax(accountConfigService.deleteByIds(ids));
    }

    /**
     * @Description: 获取可以充币的钱包类型
     * @Author: senghor
     * @Date: 2019/5/20 0020 19:25
     */
    @RequestMapping("/topUpCoinList")
    @ResponseBody
    public Wrapper<List<AccountConfig>> topUpCoinList() {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("top_up_coin", 1);
        List<AccountConfig> list = accountConfigService.selectList(entityWrapper);
        return WrapMapper.ok(list);
    }
}

