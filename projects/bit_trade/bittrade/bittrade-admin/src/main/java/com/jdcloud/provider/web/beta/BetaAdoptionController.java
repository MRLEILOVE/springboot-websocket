package com.jdcloud.provider.web.beta;


import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.BetaAdoptionLogDto;
import com.jdcloud.provider.pojo.ComboGroup;
import com.jdcloud.provider.pojo.vo.BetaAdoptionLogVo;
import com.jdcloud.provider.service.BetaAdoptionLogService;
import com.jdcloud.provider.service.ComboGroupService;
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
 * @Description: 贝塔狗---行为分析表（一个套餐一个用户一天产生一天记录） 前端控制器
 * @Author: senghor
 * @Date: 2019/5/16 0016 10:37
 */
@Controller
@RequestMapping("/beta/betaAdoption")
public class BetaAdoptionController extends BaseController {

    @Autowired
    private BetaAdoptionLogService betaAdoptionLogService;
    @Autowired
    private ComboGroupService comboGroupService;

    private String prefix = "beta/betaAdoption";

    @RequiresPermissions("betaAdoption:view")
    @GetMapping()
    public String betaOrderInfo(ModelMap mmap) {
        List<ComboGroup> list = comboGroupService.selectList(null);
        mmap.put("list", list);
        return prefix + "/list";
    }

    /**
     * 分析列表
     */
    @RequiresPermissions("betaAdoption:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo betaOrderListPageAppeal(BetaAdoptionLogDto betaAdoptionLogDto) {
        Page<BetaAdoptionLogVo> vo = betaAdoptionLogService.selectPageList(getPage(), betaAdoptionLogDto);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }

}

