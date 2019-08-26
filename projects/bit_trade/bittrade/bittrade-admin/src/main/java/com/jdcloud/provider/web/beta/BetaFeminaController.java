package com.jdcloud.provider.web.beta;


import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.provider.dto.BetaFeminaDto;
import com.jdcloud.provider.dto.BetaFeminaStatusDto;
import com.jdcloud.provider.pojo.BetaFemina;
import com.jdcloud.provider.pojo.vo.BetaFeminaVo;
import com.jdcloud.provider.service.BetaFeminaService;
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
 * 贝塔狗---母狗表 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2019-04-10
 */

@Controller
@RequestMapping("/beta/betaFemina")
public class BetaFeminaController extends BaseController {

    private String prefix = "beta/betaFemina";

    @Autowired
    private BetaFeminaService betaFeminaService;

    @RequiresPermissions("betaFemina:view")
    @GetMapping()
    public String betaFemina() {
        return prefix + "/list";
    }

    /**
     * 母狗列表
     * BetaFeminaDto
     * 2019-06-13
     */
    @RequiresPermissions("betaFemina:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo betaFeminaList(BetaFeminaDto betaFeminaDto) {
        Page<BetaFeminaVo> orderUserListPage = betaFeminaService.selectBetaFeminaPageNew(getPage(), betaFeminaDto);
        return new TableDataInfo(orderUserListPage.getRecords(), orderUserListPage.getTotal());
    }


    /**
     * 详情
     */
    @RequiresPermissions("betaFemina:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        BetaFeminaVo vo = betaFeminaService.selectbetaFeminaById(id);
        mmap.put( "vo", vo);
        return prefix + "/edit";
    }

    /**
     * 增加&&修改
     */
    @Log(title = "母狗", buinessType = AnnotationEnum.BusinessType.UPDATE)
    @RequiresPermissions("betaFemina:saveOrUpdate")
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public Wrapper<String> saveOrUpdate(BetaFemina betafemina) {
        return betaFeminaService.saveOrUpdate(betafemina);
    }

    /**
     * 母狗状态
     */
    @Log(title = "母狗状态", buinessType = AnnotationEnum.BusinessType.UPDATE)
    @RequiresPermissions("betaFemina:updateStatus")
    @PostMapping("/updateStatus")
    @ResponseBody
    public Wrapper<String> updateStatus(BetaFeminaStatusDto betaFeminaStatusDto) {
        return betaFeminaService.updateStatus(betaFeminaStatusDto);
    }

}

