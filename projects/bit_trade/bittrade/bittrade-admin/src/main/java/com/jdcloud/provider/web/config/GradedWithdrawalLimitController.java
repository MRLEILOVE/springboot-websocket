package com.jdcloud.provider.web.config;


import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.provider.pojo.GradedWithdrawalLimit;
import com.jdcloud.provider.pojo.vo.GradedWithdrawalLimitVo;
import com.jdcloud.provider.service.AccountConfigService;
import com.jdcloud.provider.service.GradedWithdrawalLimitService;
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
 *  等级提币限额配置前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2019-07-25
 */
@Controller
@RequestMapping("/config/gradedWithdrawalLimit")
public class GradedWithdrawalLimitController extends BaseController {

    @Autowired
    private GradedWithdrawalLimitService gradedWithdrawalLimitService;
    @Autowired
    private AccountConfigService accountConfigService;

    private String prefix = "config/gradedWithdrawalLimit";

    /**
     * 等级提币限额配置初始化
     *
     * @returnhouseInfo
     */
    @RequiresPermissions("config:gradedWithdrawalLimit:view")
    @GetMapping()
    public String gradedWithdrawalLimitInfo() {
        return prefix + "/list";
    }

    /**
     * 等级提币限额配置列表
     *
     * @param
     * @return
     */
    @RequiresPermissions("config:gradedWithdrawalLimit:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo getGradedWithdrawalLimit() {
        Page<GradedWithdrawalLimitVo> vo = gradedWithdrawalLimitService.selectGradedWithdrawalLimitPage(getPage());
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }

    /**
     * 初始化增加
     *
     * @return
     */
    @RequiresPermissions("config:gradedWithdrawalLimit:add")
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        mmap.put("accountConfigList", accountConfigService.selectList(null));
        return prefix + "/add";
    }

    /**
     * 增加等级提币限额配置
     * c
     */
    @Log(title = "等级提币限额配置", buinessType = AnnotationEnum.BusinessType.INSERT)
    @RequiresPermissions("config:gradedWithdrawalLimit:add")
    @PostMapping("/save")
    @ResponseBody
    public Wrapper<String> saveGradedWithdrawalLimit(GradedWithdrawalLimit gradedWithdrawalLimit) {
        return gradedWithdrawalLimitService.saveOrUpdate(gradedWithdrawalLimit);
    }

    /**
     * 等级提币限额配置详情
     * C
     */
    @RequiresPermissions("config:gradedWithdrawalLimit:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        GradedWithdrawalLimit gradedWithdrawalLimit = gradedWithdrawalLimitService.selectById(id);
        mmap.put("dto", gradedWithdrawalLimit);
        mmap.put("accountConfigList", accountConfigService.selectList(null));
        return prefix + "/edit";
    }

    /**
     * 修改等级提币限额
     * c
     */
    @Log(title = "等级提币限额配置", buinessType = AnnotationEnum.BusinessType.UPDATE)
    @RequiresPermissions("config:gradedWithdrawalLimit:edit")
    @PostMapping("/update")
    @ResponseBody
    public Wrapper<String> updateGradedWithdrawalLimit(GradedWithdrawalLimit gradedWithdrawalLimit) {
        return gradedWithdrawalLimitService.saveOrUpdate(gradedWithdrawalLimit);
    }

    /**
     * @Description: 删除等级提币限额配置
     * @param ids :
     * @Author: zjun
     * @Date: 2019/7/25 14:37
     */
    @RequiresPermissions("config:gradedWithdrawalLimit:remove")
    @PostMapping("/remove")
    @ResponseBody
    public Wrapper<String> remove(String ids) {
        return toAjax(gradedWithdrawalLimitService.deleteByIds(ids));
    }
}

