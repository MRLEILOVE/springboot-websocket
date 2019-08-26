package com.jdcloud.provider.web.config;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.CountryCodeDto;
import com.jdcloud.provider.pojo.CountryCode;
import com.jdcloud.provider.service.CountryCodeService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 国家区域码控制器
 * @Author: senghor
 * @Date: 2019/5/20 0020 9:25
 */
@Controller
@RequestMapping(value = "/config/countryCode")
public class CountryCodeController extends BaseController {

    private String prefix = "config/countryCode";

    @Autowired
    private CountryCodeService countryCodeService;

    @RequiresPermissions("config:countryCode:view")
    @GetMapping()
    public String parameterConfig() {
        return prefix + "/countryCode";
    }

    /**
     * @Description: 国家区域码列表页面
     * @Author: senghor
     * @Date: 2019/5/20 0020 9:25
     */
    @RequiresPermissions("config:countryCode:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CountryCodeDto countryCodeDto) {
        Page<CountryCode> page = countryCodeService.selectCountryCodePageList(getPage(), countryCodeDto);
        return new TableDataInfo(page.getRecords(), page.getTotal());
    }

    /**
     * @Description: 增加页面
     * @Author: senghor
     * @Date: 2019/5/20 0020 9:29
     */
    @RequiresPermissions("config:countryCode:add")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @RequiresPermissions("config:countryCode:edit")
    @GetMapping("/edit/{countryCodeId}")
    public String edit(@PathVariable("countryCodeId") String countryCodeId, ModelMap mmap) {
        CountryCode countryCode = countryCodeService.selectById(countryCodeId);
        mmap.put("countryCodeModel", countryCode);
        return prefix + "/edit";
    }

    /**
     * @Description: 新增/更新数据
     * @Author: senghor
     * @Date: 2019/5/20 0020 9:30
     */
    @RequiresPermissions("config:countryCode:saveOrUpdate")
    @PostMapping(value = "/saveOrUpdate")
    @ResponseBody
    public Wrapper<String> saveOrUpdate(CountryCode countryCode) {
        return countryCodeService.saveOrUpdate(countryCode);
    }

    /**
     * @Description: 删除国家区域码
     * @Author: senghor
     * @Date: 2019/5/20 0020 9:36
     */
    @RequiresPermissions("config:countryCode:remove")
    @PostMapping("/remove")
    @ResponseBody
    public Wrapper<String> remove(String ids) {
        return toAjax(countryCodeService.deleteByIds(ids));
    }

    /**
     * @Description: 批量修改国家区域码
     * @Author: senghor
     * @Date: 2019/5/21 0021 16:32
     */
    @RequiresPermissions("config:countryCode:updateAll")
    @PostMapping("/updateAll")
    @ResponseBody
    public Wrapper<String> updateAll(String ids, Integer useType) {
        return toAjax(countryCodeService.updateUseTypeAll(ids, useType));
    }
}