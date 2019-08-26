package com.jdcloud.provider.web.beta;


import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.provider.dto.ActionDto;
import com.jdcloud.provider.dto.BetaFreezeAccountDto;
import com.jdcloud.provider.pojo.vo.BetaFreezeAccountVo;
import com.jdcloud.provider.pojo.vo.BetaLockedAccountVo;
import com.jdcloud.provider.service.BetaLockedAccountService;
import com.jdcloud.provider.utils.ExcelUtil;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.annotation.Log;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;


/**
 * <p>
 *  我的资产表
 * </p>
 * @author c
 * @since 2019-08-16
 */
@Controller
@RequestMapping("/beta/betaLockedAccount")
public class BetaLockedAccountController  extends BaseController {

    private String prefix = "beta/betaLockedAccount";

    @Autowired
    private BetaLockedAccountService betaLockedAccountService;

    /**
     * 我的资产表
     * @param mmap
     * @return
     */
    @RequiresPermissions("betaLockedAccountInfo:view")
    @GetMapping()
    public String Info(ModelMap mmap) {
        return prefix + "/list";
    }

    /**
     * 我的资产列表
     * @param dto
     * @return
     */
    @RequiresPermissions("betaLockedAccount:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo getBetaLockedAccount(ActionDto dto) {
        Page<BetaLockedAccountVo> vo = betaLockedAccountService.getBetaLockedAccount(getPage(),dto);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }


    /**
     * 导出EXCL文档
     */
    @Log(title = "宠物资产导出", buinessType = AnnotationEnum.BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public Wrapper<String> export(ActionDto dto) throws IOException {
        List<BetaLockedAccountVo> list = betaLockedAccountService.betaLockedAccountExcelList(dto);
        ExcelUtil<BetaLockedAccountVo> util = new ExcelUtil<BetaLockedAccountVo>(BetaLockedAccountVo.class);
        return util.exportExcel(list, "betaLockedAccountExcelList");
    }

}

