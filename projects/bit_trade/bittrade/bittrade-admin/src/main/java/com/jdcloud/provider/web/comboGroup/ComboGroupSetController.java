package com.jdcloud.provider.web.comboGroup;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.provider.dto.ComboGroupDto;
import com.jdcloud.provider.model.pojo.vo.ComboCountVo;
import com.jdcloud.provider.pojo.ComboGroup;
import com.jdcloud.provider.pojo.vo.ComboGroupVo;
import com.jdcloud.provider.service.ComboGroupService;
import com.jdcloud.provider.utils.RandomUtils;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.annotation.Log;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 套餐拆狗设置 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2019-04-07
 */
@Controller
@RequestMapping("/beta/comboGroupSet")
public class ComboGroupSetController extends BaseController {


    @Autowired
    private ComboGroupService comboGroupService;

    private String prefix = "beta/comboGroupSet";

    @RequiresPermissions("comboGroupSet:view")
    @GetMapping()
    public String orderUser() {
        return prefix + "/list";
    }

    @GetMapping("/add")
    @RequiresPermissions("comboGroupSet:add")
    public String add(ModelMap mmap) {
        return prefix + "/add";
    }

    /**
     * 列表
     */
    @RequiresPermissions("comboGroupSet:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo comboGroupList() {
        List<ComboCountVo> comboCountVos = comboGroupService.selectListComboVo();
        return new TableDataInfo(comboCountVos, comboCountVos.size());
    }

    /**
     * 查询套餐详情
     *
     * @param id
     * @param mmap
     * @return
     */
    @RequiresPermissions("comboGroupSet:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        ComboGroup comboGroup = comboGroupService.selectById(id);
        mmap.put("comboGroup", comboGroup);
        return prefix + "/edit";
    }

    /**
     * 保存或更新<br>
     */
    @Log(title = "更新拆狗设置", buinessType = AnnotationEnum.BusinessType.UPDATE)
    @RequiresPermissions("comboGroupSet:saveOrUpdate")
    @PostMapping("/updateById")
    @ResponseBody
    public Wrapper<String> updateById(ComboGroup comboGroup) {
        comboGroup.setSplitSum(comboGroup.getSplitSum());
        if (comboGroup.getMeet() != null) {
            comboGroup.setMeet(NumberUtil.div(comboGroup.getMeet(), new BigDecimal(100), 2));
        }
        if (comboGroupService.updateById(comboGroup)) {
            return WrapMapper.ok();
        } else {
            return WrapMapper.error();
        }
    }

    /**
     * 获取明天之前需要拆分的狗最高和最低价格
     */
    @PostMapping("/selectTomorrowMaxMin")
    @ResponseBody
    public Wrapper<ComboGroup> selectTomorrowMaxMin() {
        return comboGroupService.selectTomorrowMaxMin();
    }

}

