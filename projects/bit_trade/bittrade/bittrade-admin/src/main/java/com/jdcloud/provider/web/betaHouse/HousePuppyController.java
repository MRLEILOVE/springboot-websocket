package com.jdcloud.provider.web.betaHouse;


import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.HousePuppyDto;
import com.jdcloud.provider.pojo.ComboGroup;
import com.jdcloud.provider.pojo.vo.HousePuppyVo;
import com.jdcloud.provider.service.ComboGroupService;
import com.jdcloud.provider.service.HousePuppyService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 小狗表 前端控制器
 * </p>
 * @author cc
 * @since 2019-06-13
 */
@Controller
@RequestMapping("/betaHouse/housePuppy")
public class HousePuppyController extends BaseController {
    private String prefix = "betaHouse/housePuppy";

    @Autowired
    private ComboGroupService comboGroupService;
    @Autowired
    private HousePuppyService housePuppyService;

    /**
     * 初始化小狗
     * @param mmap
     * @return
     */
    @RequiresPermissions("housePuppy:view")
    @GetMapping()
    public String housePuppyInfo(ModelMap mmap) {
        List<ComboGroup> list = comboGroupService.selectList(null);
        mmap.put("list", list);
        return prefix + "/list";
    }

    /**
     * 小狗列表
     * @param housePuppyDto
     * @return
     */
    @RequiresPermissions("housePuppy:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo getHousePuppy(HousePuppyDto housePuppyDto) {
        Page<HousePuppyVo> vo = housePuppyService.getHousePuppy(getPage(), housePuppyDto);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }






}

