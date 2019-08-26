package com.jdcloud.provider.web.finance;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.EnrichmentCountDto;
import com.jdcloud.provider.dto.Withdraw;
import com.jdcloud.provider.pojo.EnrichmentCount;
import com.jdcloud.provider.service.EnrichmentCountService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 统计币种金额 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2019-08-05
 */
@Controller
@RequestMapping("/finance/enrichmentCount")
public class EnrichmentCountController extends BaseController {

    private String	 prefix	= "finance/enrichmentCount";

    @Autowired
    private EnrichmentCountService enrichmentCountService;

    /**
     * 初始化币种统计金额
     * @return
     */
    @RequiresPermissions("finance:enrichmentCount:view")
    @GetMapping("/info/{type}")
    public String enrichmentCountInfo(@PathVariable("type") int type, ModelMap mmap) {
        mmap.put("vo",type);
        return prefix + "/list";
    }

    /**
     * 币种统计金额列表
     * C
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EnrichmentCountDto dto) {
        Page<EnrichmentCount> page = enrichmentCountService.getEnrichmentCount(getPage(),dto);
        return new TableDataInfo(page.getRecords(),page.getTotal() );
    }


}

