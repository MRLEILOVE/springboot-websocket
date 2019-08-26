package com.jdcloud.provider.web.beta;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.provider.dto.ImgLogDto;
import com.jdcloud.provider.pojo.vo.BetaImgLogVo;
import com.jdcloud.provider.service.BetaImgLogService;
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
 * 图片日志
 */
@Controller
@RequestMapping("/beta/imgLog")
public class BetaImgLogController extends BaseController {

    @Autowired
    private BetaImgLogService betaImgLogService;

    private String prefix = "beta/imgLog";

    @RequiresPermissions("imgLogInfo:view")
    @GetMapping()
    public String imgLogInfo(){
        return prefix + "/list";
    }


    /**
     * 订单列表
     */
    @RequiresPermissions("imgLog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ImgLogDto imgLogDto)
    {
        Page<BetaImgLogVo> vo = betaImgLogService.selectImgLog(getPage(), imgLogDto);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }

    /**
     * 删除图片功能
     * @param ids
     * @return
     */
    @Log(title = "图片日志", buinessType = AnnotationEnum.BusinessType.DELETE)
    @RequiresPermissions("imgLog:remove")
    @PostMapping("/remove")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Wrapper<String> comboGroupdelete(String ids)
    {
        return toAjax( betaImgLogService.deleteByIds(ids));
    }

    /**
     * 详情
     */
    @RequiresPermissions("imgLog:edit")
    @GetMapping("/edit/{id}")
    public String details(@PathVariable("id") Integer id, ModelMap mmap)
    {
        BetaImgLogVo vo = betaImgLogService.selectImgLogDetails(id);
        mmap.put("vo",vo);
        return prefix + "/edit";
    }
}
