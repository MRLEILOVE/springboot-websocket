package com.jdcloud.provider.web.beta;


import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.provider.pojo.BetaTeamConfig;
import com.jdcloud.provider.service.BetaTeamConfigService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.annotation.Log;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 贝塔狗-推广团队配置表 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2019-05-09
 */
@Controller
@RequestMapping("/beta/betaTeamConfig")
public class BetaTeamConfigController extends BaseController {

    @Autowired
    private BetaTeamConfigService betaTeamConfigService;

    private String prefix = "beta/betaTeamConfig";

    @RequiresPermissions("betaTeamConfig:view")
    @GetMapping()
    public String betaTeamConfig() {
        return prefix + "/list";
    }

    /**
     * 列表
     */
    @RequiresPermissions("betaTeamConfig:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo selectBetaMaleListPage() {
        Page<BetaTeamConfig> perpetuals = betaTeamConfigService.selectPage(getPage());
        return new TableDataInfo(perpetuals.getRecords(), perpetuals.getTotal());
    }

    /**
     * 新增页面
     */
    @GetMapping("/add")
    @RequiresPermissions("betaTeamConfig:add")
    public String add(ModelMap mmap) {
        return prefix + "/add";
    }

    /**
     * 增加/编辑
     */
    @Log(title = "增加/编辑", buinessType = AnnotationEnum.BusinessType.INSERT)
    @RequiresPermissions("betaTeamConfig:saveOrUpdate")
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public Wrapper<String> saveOrUpdate(BetaTeamConfig betaTeamConfig) {
        return betaTeamConfigService.saveOrUpdate(betaTeamConfig);
    }

    /**
     * 编辑页面
     */
    @RequiresPermissions("betaTeamConfig:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        BetaTeamConfig betaTeamConfig = betaTeamConfigService.selectById(id);
        mmap.put("betaTeamConfig", betaTeamConfig);
        return prefix + "/edit";
    }

    /**
     * 删除
     */
    @Log(title = "删除团队配置", buinessType = AnnotationEnum.BusinessType.DELETE)
    @RequiresPermissions("betaTeamConfig:delete")
    @PostMapping("/remove")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Wrapper<String> remove(String ids) {
        return toAjax(betaTeamConfigService.deleteByIds(ids));
    }

}

