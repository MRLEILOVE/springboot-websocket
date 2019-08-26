package com.jdcloud.provider.web.beta;


import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.provider.dto.BetaAccountDto;
import com.jdcloud.provider.dto.BetaAccountRecordDto;
import com.jdcloud.provider.dto.BetaFreezeAccountDto;
import com.jdcloud.provider.pojo.BetaFreezeAccount;
import com.jdcloud.provider.pojo.BetaFreezeAccountRecord;
import com.jdcloud.provider.pojo.vo.*;
import com.jdcloud.provider.service.BetaFreezeAccountRecordService;
import com.jdcloud.provider.service.BetaFreezeAccountService;
import com.jdcloud.provider.service.BetaOrderService;
import com.jdcloud.provider.service.PersonalCardService;
import com.jdcloud.provider.utils.ExcelUtil;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.annotation.Log;
import com.jdcloud.util.date.DateTimeUtils;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2019-08-08
 */
@Controller
@RequestMapping("/beta/betaFreezeAccount")
public class BetaFreezeAccountController extends BaseController {

    @Resource
    private BetaFreezeAccountService betaFreezeAccountService;

    @Resource
    private BetaFreezeAccountRecordService betaFreezeAccountRecordService;


    private String prefix = "beta/betaFreezeAccount";

    @RequiresPermissions("betaFreezeAccount:view")
    @GetMapping()
    public String betaAccount(ModelMap mmap) {
        String today= DateUtils.today()+" 00:00:00";
        mmap.put("betaFreezeAccountSumVo",betaFreezeAccountService.getBetaFreezeAccountSum(today));
        return prefix + "/list";
    }

    /**
     * 列表
     */
    @RequiresPermissions("betaFreezeAccount:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo betaAccountList(BetaFreezeAccountDto dto) {
        Page<BetaFreezeAccountVo> list = betaFreezeAccountService.betaFreezeAccountList(getPage(), dto);
        return new TableDataInfo(list.getRecords(), list.getTotal());
    }


    /**
     * 初始化冻结资金流水
     *
     */
    @RequiresPermissions("betaFreezeAccountRecord:view")
    @GetMapping("/recordList/{userId}")
    public String recordListInfo(@PathVariable("userId") Long userId, ModelMap mmap) {
        mmap.put("userId", userId);
        return prefix + "/listRecord";
    }

    /**
     * 资金流水列表
     * @param
     * @param dto
     * @return
     */
    @RequiresPermissions("betaAccountRecordList:view")
    @RequestMapping("/betaAccountRecordList")
    @ResponseBody
    public TableDataInfo betaAccountRecord(BetaFreezeAccountDto dto) {
        Page<BetaFreezeAccountRecordVo> list = betaFreezeAccountRecordService.getBetaAccountRecord(getPage(),dto);
        return new TableDataInfo(list.getRecords(), list.getTotal());
    }

    /**
     * 导出EXCL文档
     */
    @Log(title = "冻结资产导出", buinessType = AnnotationEnum.BusinessType.EXPORT)
    @RequiresPermissions("betaFreezeAccount:export")
    @PostMapping("/export")
    @ResponseBody
    public Wrapper<String> export(BetaFreezeAccountDto dto) throws IOException {
        List<BetaFreezeAccountVo> list = betaFreezeAccountService.betaFreezeAccountExcelList(dto);
        ExcelUtil<BetaFreezeAccountVo> util = new ExcelUtil<BetaFreezeAccountVo>(BetaFreezeAccountVo.class);
        return util.exportExcel(list, "BetaFreezeAccountExcelList");
    }
}

