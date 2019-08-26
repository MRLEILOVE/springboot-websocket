package com.jdcloud.provider.web.betaHouse;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.base.enums.HouseEnum;
import com.jdcloud.provider.dto.HouseProductDto;
import com.jdcloud.provider.dto.HouseProductImgDto;
import com.jdcloud.provider.pojo.HouseProduct;
import com.jdcloud.provider.service.AccountConfigService;
import com.jdcloud.provider.service.HouseProductService;
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
 * 商品表 前端控制器
 * </p>
 * @author cc
 * @since 2019-06-23
 */
@Controller
@RequestMapping("/betaHouse/houseProduct")
public class HouseProductController extends BaseController {

    @Autowired
    private HouseProductService houseProductService;
    @Autowired
    private AccountConfigService accountConfigService;

    private String prefix = "betaHouse/houseProduct";
    /**
     * 商品母狗商品初始化
     * c
     * 2019-06-13
     */
    @RequiresPermissions("houseProductInfo:view")
    @GetMapping()
    public String houseProductInfo(ModelMap mmap) {
        return prefix + "/list";
    }

    /**
     * 商品小狗商品初始化
     * c
     * 2019-06-13
     */
    @RequiresPermissions("housePuppyInfo:view")
    @GetMapping("/housePuppyInfo")
    public String housePuppyInfo(ModelMap mmap) {
        return prefix + "/puppyList";
    }

    /**
     * 商品列表
     * @param houseProductDto
     * @return
     */
    @RequiresPermissions("houseProduct:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo getHouseProduct(HouseProductDto houseProductDto) {
        Page<HouseProduct> vo = houseProductService.getHouseProduct(getPage(), houseProductDto);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }

    /**
     * 增加母狗商品
     * @return
     */
    @GetMapping("/add")
    public String add( ModelMap mmap) {
        mmap.put("accountConfigList", accountConfigService.selectList(null));
        return prefix + "/add";
    }
    /**
     * 增加商品
     * cc
     */
    @Log(title = "增加商品", buinessType = AnnotationEnum.BusinessType.INSERT)
    @RequiresPermissions("houseProduct:saveHouseProduct")
    @PostMapping("/saveHouseProduct")
    @ResponseBody
    public Wrapper<String> saveHouseProduct(HouseProductImgDto houseProductImgDto) {
        return houseProductService.saveHouseProduct(houseProductImgDto);
    }

    /**
     * 母狗商品详情
     * cc
     * 2019-06-14
     */
    @RequiresPermissions("houseProduct:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        HouseProduct houseProduct = houseProductService.selectById(id);
        mmap.put("dto", houseProduct);
        mmap.put("accountConfigList", accountConfigService.selectList(null));
        return prefix + "/edit";
    }

    /**
     * 修改商品
     */
    @Log(title = "修改商品状态", buinessType = AnnotationEnum.BusinessType.UPDATE)
    @RequiresPermissions("houseProduct:updateHouseProduct")
    @PostMapping("/updateHouseProduct")
    @ResponseBody
    public Wrapper<String> updateHouseProduct(HouseProductImgDto houseProductImgDto) {
        return houseProductService.updateHouseProduct(houseProductImgDto);
    }

    /**
     * 删除商品
     * @param ids
     * @return
     */
    @Log(title = "删除商品", buinessType = AnnotationEnum.BusinessType.DELETE)
    @RequiresPermissions("houseProduct:delete")
    @PostMapping("/remove")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Wrapper<String> remove(Integer ids) {
        return houseProductService.deleteHouseProduct(ids);
    }

    /**
     * 增加小公狗商品
     * cc
     */
    @Log(title = "增加小公狗商品", buinessType = AnnotationEnum.BusinessType.INSERT)
    @RequiresPermissions("houseProduct:saveHouseProduct")
    @PostMapping("/addHouseProduct")
    @ResponseBody
    public Wrapper<String> addHouseProduct(HouseProductImgDto houseProductImgDto) {
        houseProductImgDto.setBeSuitable(HouseEnum.beSuitable.PUPPY.getCode());
        return houseProductService.addHouseProduct(houseProductImgDto);
    }


    /**
     * 增加小狗商品
     *
     * @return
     */
    @GetMapping("/puppyAdd")
    public String puppyAdd(ModelMap mmap) {
        mmap.put("accountConfigList", accountConfigService.selectList(null));
        return prefix + "/puppyAdd";
    }

    /**
     * 小狗商品详情
     * cc
     * 2019-06-14
     */
    @RequiresPermissions("houseProductPuppy:editFull")
    @GetMapping("/editFull/{id}")
    public String editPuppy(@PathVariable("id") Integer id, ModelMap mmap) {
        HouseProduct houseProduct = houseProductService.selectById(id);
        mmap.put("dto", houseProduct);
        mmap.put("accountConfigList", accountConfigService.selectList(null));
        return prefix + "/puppyEdit";
    }

    /**
     * 小狗商品修改
     */
    @Log(title = "小狗商品修改", buinessType = AnnotationEnum.BusinessType.UPDATE)
    @RequiresPermissions("houseProduct:updatePuppy")
    @PostMapping("/updatePuppy")
    @ResponseBody
    public Wrapper<String> updatePuppy(HouseProductImgDto houseProductImgDto) {
        return houseProductService.updatePuppy(houseProductImgDto);
    }


}

