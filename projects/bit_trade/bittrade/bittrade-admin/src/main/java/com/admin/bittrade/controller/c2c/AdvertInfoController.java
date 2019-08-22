package com.admin.bittrade.controller.c2c;

import com.admin.bittrade.service.c2c.AdvertInfoService;
import com.admin.bittrade.vo.PageParam;
import com.admin.bittrade.vo.c2c.AdvertInfoScreeningParameterVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bittrade.pojo.model.TAdvertInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author xzc
 * @date 2019-08-22 11:08
 * @description 广告controller 筛选参数
 */
@Controller
@RequestMapping("/advert_info")
public class AdvertInfoController {

    @Autowired
    private AdvertInfoService advertInfoService;

    /**
     * 显示tab
     */
    @GetMapping("displayPage")
    private String displayPage() {
        return "";
    }

    @ResponseBody
    @PostMapping("/findList")
    private IPage<TAdvertInfo> findList(@RequestBody PageParam pageParam, AdvertInfoScreeningParameterVo parameterVo) {
        IPage<TAdvertInfo> page = advertInfoService.findList(pageParam, parameterVo);
        return page;
    }


}
