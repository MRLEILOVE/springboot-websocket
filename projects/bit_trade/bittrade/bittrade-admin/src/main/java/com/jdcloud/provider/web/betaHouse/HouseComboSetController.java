package com.jdcloud.provider.web.betaHouse;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.provider.dto.HouseComboSetDto;
import com.jdcloud.provider.dto.HouseProductImgDto;
import com.jdcloud.provider.pojo.ComboGroup;
import com.jdcloud.provider.pojo.HouseComboSet;
import com.jdcloud.provider.pojo.HouseComboSetDetail;
import com.jdcloud.provider.pojo.vo.HouseComboSetVo;
import com.jdcloud.provider.service.ComboGroupService;
import com.jdcloud.provider.service.HouseComboSetDetailService;
import com.jdcloud.provider.service.HouseComboSetService;
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

import java.util.List;

/**
 * <p>
 * 套餐设置表 前端控制器
 * </p>
 *
 * @author cc
 * @since 2019-06-18
 */
@Controller
@RequestMapping("/betaHouse/houseComboSet")
public class HouseComboSetController extends BaseController {

    @Autowired
    private HouseComboSetService houseComboSetService;
    @Autowired
    private ComboGroupService comboGroupService;
    @Autowired
    private HouseComboSetDetailService houseComboSetDetailService;

    private String prefix = "betaHouse/houseComboSet";


    /**
     * 套餐设置表初始化
     * c
     * 2019-06-18
     */
    @RequiresPermissions("comboSetInfo:view")
    @GetMapping()
    public String comboSetInfo() {
        return prefix + "/list";
    }

    /**
     * 套餐设置列表
     *
     * @param
     * @return
     */
    @RequiresPermissions("houseComboSet:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo getComboSet() {
        Page<HouseComboSetVo> vo = houseComboSetService.getComboSet(getPage());
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }

    /**
     * 狗窝套餐设置（增加初始化）
     *
     * @return
     */
    @RequiresPermissions("houseComboSet:addInfo")
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        List<ComboGroup> list = comboGroupService.selectList(null);
        mmap.put("list", list);
        return prefix + "/add";
    }

    /**
     * 增加套餐设置
     * 2019-6-19
     */
    @Log(title = "增加狗窝套餐设置", buinessType = AnnotationEnum.BusinessType.INSERT)
    @RequiresPermissions("houseComboSet:saveHouseComboSet")
    @PostMapping("/saveHouseComboSet")
    @ResponseBody
    public Wrapper<String> saveHouseComboSet(@RequestBody HouseComboSetDto houseComboSetDto) {
        return houseComboSetService.saveHouseComboSet(houseComboSetDto);
    }


    /**
     * 套餐设置详情
     * 2019-6-19
     * cc
     */
    @RequiresPermissions("houseComboSet:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        HouseComboSet  houseComboSet = houseComboSetService.selectById(id);
        ComboGroup group = comboGroupService.selectById(houseComboSet.getComboId());
        EntityWrapper entity = new EntityWrapper();
        entity.eq("combo_set_id",houseComboSet.getId());
        List<HouseComboSetDetail> setDetail = houseComboSetDetailService.selectList(entity);
        mmap.put("dto", houseComboSet);
        mmap.put("group", group);
        mmap.put("setDetail", setDetail);
        return prefix + "/edit";
    }


    /**
     * 修改
     * 2019-6-19
     */
    @Log(title = "增加狗窝套餐修改", buinessType = AnnotationEnum.BusinessType.UPDATE)
    @RequiresPermissions("houseComboSet:updateHouseProduct")
    @PostMapping("/updateHouseProduct")
    @ResponseBody
    public Wrapper<String> updateHouseProduct(@RequestBody HouseComboSetDto houseComboSetDto) {
        return houseComboSetService.updateHouseProduct(houseComboSetDto);
    }

    /**
     * 删除狗窝套餐
     */
    @Log(title = "删除狗窝套餐", buinessType = AnnotationEnum.BusinessType.DELETE)
    @RequiresPermissions("houseComboSet:delete")
    @PostMapping("/remove/{ids}")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Wrapper<String> remove(@PathVariable("ids") Integer ids) {
        return houseComboSetService.deleteByIds(ids);
    }

}