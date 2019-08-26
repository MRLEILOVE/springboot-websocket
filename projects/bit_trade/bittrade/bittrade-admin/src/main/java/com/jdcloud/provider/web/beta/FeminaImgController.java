package com.jdcloud.provider.web.beta;


import com.jdcloud.provider.pojo.AccountConfig;
import com.jdcloud.provider.pojo.FeminaImg;
import com.jdcloud.provider.pojo.vo.FeminaImgVo;
import com.jdcloud.provider.service.AccountConfigService;
import com.jdcloud.provider.service.FeminaImgService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2019-07-29
 */
@Controller
@RequestMapping("/beta/feminaImg")
public class FeminaImgController extends BaseController {

    private String prefix = "beta/feminaImg";

    @Autowired
    private FeminaImgService feminaImgService;

    /**
     * @Description: 初始化页面
     * @param mmap :
     * @Author:
     * @Date: 2019/7/30 0:30
     */
    @RequiresPermissions("beta:feminaImg:edit")
    @GetMapping("/edit")
    public String edit(ModelMap mmap) {
        FeminaImg feminaImg = feminaImgService.selectOne(null);
        if(null==feminaImg){
            feminaImg=new FeminaImg();
        }
        mmap.put("dto", feminaImg);
        return prefix + "/edit";
    }

    /**
     * @Description: 新增/更新数据
     * @param feminaImgVo :
     * @Author:
     * @Date: 2019/7/30 0:30
     */
    @RequiresPermissions("beta:feminaImg:edit")
    @PostMapping(value = "/saveOrUpdate")
    @ResponseBody
    public Wrapper<String> saveOrUpdate(FeminaImgVo feminaImgVo) {
        return feminaImgService.saveOrUpdate(feminaImgVo);
    }
}

