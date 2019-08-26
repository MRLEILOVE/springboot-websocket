package com.jdcloud.provider.web.beta;


import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.base.enums.AnnotationEnum.BusinessType;
import com.jdcloud.provider.dto.*;
import com.jdcloud.provider.pojo.BetaAccountImportRecord;
import com.jdcloud.provider.pojo.vo.*;
import com.jdcloud.provider.service.BetaAccountImportRecordService;
import com.jdcloud.provider.service.BetaAccountRecordService;
import com.jdcloud.provider.service.BetaAccountService;
import com.jdcloud.provider.service.BetaOrderService;
import com.jdcloud.provider.utils.ExcelUtil;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.annotation.Log;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * <p>
 * 我的beta资产表 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2019-04-13
 */

@Controller
@RequestMapping("/beta/betaAccount")
public class BetaAccountController extends BaseController {

    @Autowired
    private BetaAccountRecordService betaAccountRecordService;
    @Autowired
    private BetaAccountService betaAccountService;
    @Autowired
    private BetaAccountImportRecordService betaAccountImportRecordService;
    @Resource
    private BetaOrderService betaOrderService;

    private String prefix = "beta/betaAccount";

    @RequiresPermissions("betaAccount:view")
    @GetMapping()
    public String betaAccount(ModelMap mmap) {
        RechargeRecordVo vo = betaAccountService.selectRechargeRecord();
        BigDecimal sumDifferential  = betaOrderService.getSumDifferential();
        vo.setTotalconsume(sumDifferential.setScale(4, RoundingMode.DOWN));
        vo.setConsume(vo.getConsume().setScale(4, RoundingMode.DOWN));
        vo.setRegistrationGift(vo.getRegistrationGift().setScale(4, RoundingMode.DOWN));
        mmap.put("vo",vo);
//      mmap.put("sumDifferential",sumDifferential);
        return prefix + "/list";
    }

    /**
     * 列表
     */
    @RequiresPermissions("betaAccount:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo betaAccountList(BetaAccountDto dto) {
        Page<BetaAccountVo> list = betaAccountService.betaAccountList(getPage(), dto);
        return new TableDataInfo(list.getRecords(), list.getTotal());
    }


    /**
     * 初始化资产流水页面
     *
     * @param id
     * @param mmap
     * @return
     */
    @RequiresPermissions("betaAccountRecordListInfo:view")
    @GetMapping("/recordList/{id}")
    public String recordListInfo(@PathVariable("id") Integer id, ModelMap mmap) {
        mmap.put("id", id);
        return prefix + "/listRecord";
    }

    /**
     * 资产流水表列表
     *
     * @return /beta/comboGroup/betaAccount/betaAccountRecordList
     */
    @RequiresPermissions("betaAccountRecordInfo:view")
    @RequestMapping("/betaAccountRecordList/{id}")
    @ResponseBody
    public TableDataInfo betaAccountRecord(@PathVariable("id") Integer id, BetaAccountRecordDto dto) {
        Page<BetaAccountRecordVo> list = betaAccountRecordService.betaAccountRecord(getPage(), id, dto);
        return new TableDataInfo(list.getRecords(), list.getTotal());
    }


    /**
     * 资产详情
     */
    @RequiresPermissions("betaAccount:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        mmap.put("dto", id);
        return prefix + "/edit";
    }

    /**
     * 充值
     */
    @Log(title = "Beta资产充值", buinessType = BusinessType.RECHARGE)
    @RequiresPermissions("betaAccount:saveOrUpdate")
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public Wrapper<String> saveOrUpdate(BetaAccountRecordDto betaAccountRecordDto) {
        return betaAccountService.saveOrUpdate(betaAccountRecordDto);
    }

    /**
     * 导出EXCL文档
     */
    @Log(title = "资产导出", buinessType = AnnotationEnum.BusinessType.EXPORT)
    @RequiresPermissions("betaAccount:export")
    @PostMapping("/export")
    @ResponseBody
    public Wrapper<String> export(BetaAccountDto dto) throws IOException {
        List<BetaAccountVo> list = betaAccountService.betaAccountExcelList(dto);
        ExcelUtil<BetaAccountVo> util = new ExcelUtil<BetaAccountVo>(BetaAccountVo.class);
        return util.exportExcel(list, "BetaAccountExcelList");
    }

    /**
     * @Description: 资产导入表格
     * @Author: senghor
     * @Date: 2019/8/15 21:13
     */
    @Log(title = "资产导入", buinessType = AnnotationEnum.BusinessType.IMPORT)
    @RequiresPermissions("betaAccount:importExcel")
    @RequestMapping("/importExcel")
    @ResponseBody
    public Wrapper<String> importExcel(MultipartFile file) {
        return betaAccountService.importExcel(file);
    }

    /**
     * 初始化 导入记录页面
     * @param mmap
     * @return
     */
    @RequiresPermissions("betaAccount:importRecordList")
    @GetMapping("/importRecord")
    public String importRecord(ModelMap mmap) {
        return prefix + "/importRecord";
    }

    /**
     * @Description: 资金导入记录
     * @Author: senghor
     * @Date: 2019/8/16 9:39
     */
    @RequiresPermissions("betaAccount:importRecordListList")
    @PostMapping("/importRecordList")
    @ResponseBody
    public TableDataInfo importRecordList(BetaAccountImportRecordDto betaAccountImportRecordDto) {
        Page<BetaAccountImportRecord> vo = betaAccountImportRecordService.selectBetaAccountImportRecordListPage(getPage(), betaAccountImportRecordDto);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }
}

