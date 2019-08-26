package com.jdcloud.provider.web.betaHouse;


import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.provider.pojo.HouseAccountSet;
import com.jdcloud.provider.pojo.HouseProduct;
import com.jdcloud.provider.pojo.vo.HouseAccountSetVo;
import com.jdcloud.provider.service.AccountConfigService;
import com.jdcloud.provider.service.HouseAccountSetService;
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
 * 挖矿配置 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2019-07-03
 */
@Controller
@RequestMapping("/betaHouse/houseAccountSet")
public class HouseAccountSetController extends BaseController {

    @Autowired
    private HouseAccountSetService houseAccountSetService;
    @Autowired
    AccountConfigService accountConfigService;

    private String prefix = "betaHouse/houseAccountSet";

    /**
     * 挖矿配置初始化
     * @returnhouseInfo
     */
    @RequiresPermissions("houseAccountSet:view")
    @GetMapping()
    public String houseAccountSetInfo() {
        return prefix + "/list";
    }

    /**
     * 挖矿配置列表
     * @param
     * @return
     */
    @RequiresPermissions("houseAccountSet:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo getHouseAccountSet(){
        Page<HouseAccountSetVo> vo = houseAccountSetService.selectHouseAccountSetPage(getPage());
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }

    /**
     * 初始化增加
     * @return
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        mmap.put("accountConfigList", accountConfigService.selectList(null));
        return prefix + "/add";
    }

    /**
     * 增加挖矿配置
     * c
     */
    @Log(title = "挖矿配置", buinessType = AnnotationEnum.BusinessType.INSERT)
    @RequiresPermissions("houseAccountSet:saveHouseAccountSet")
    @PostMapping("/saveHouseAccountSet")
    @ResponseBody
    public Wrapper<String> saveHouseAccountSet(HouseAccountSet houseAccountSet) {
        return houseAccountSetService.saveHouseProduct(houseAccountSet);
    }

    /**
     * 配置详情
     * C
     */
    @RequiresPermissions("houseAccountSet:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        HouseAccountSet houseAccountSet = houseAccountSetService.selectById(id);
        mmap.put("dto", houseAccountSet);
        mmap.put("accountConfigList", accountConfigService.selectList(null));
        return prefix + "/edit";
    }

    /**
     * 修改挖矿配置
     * c
     */
    @Log(title = "挖矿配置", buinessType = AnnotationEnum.BusinessType.UPDATE)
    @RequiresPermissions("houseAccountSet:updateHouseAccountSet")
    @PostMapping("/updateHouseAccountSet")
    @ResponseBody
    public Wrapper<String> updateHouseAccountSet(HouseAccountSet houseAccountSet) {
        return houseAccountSetService.updateHouseAccountSet(houseAccountSet);
    }

}

