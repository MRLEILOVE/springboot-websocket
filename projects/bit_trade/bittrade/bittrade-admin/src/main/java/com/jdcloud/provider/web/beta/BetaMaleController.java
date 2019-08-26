package com.jdcloud.provider.web.beta;


import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.provider.dto.BetaMaleAddDto;
import com.jdcloud.provider.dto.BetaMaleDto;
import com.jdcloud.provider.dto.BetaMaleImportRecordDto;
import com.jdcloud.provider.pojo.BetaMale;
import com.jdcloud.provider.pojo.BetaOrder;
import com.jdcloud.provider.pojo.ComboGroup;
import com.jdcloud.provider.pojo.PersonalCard;
import com.jdcloud.provider.pojo.vo.BetaMaleImportRecordVo;
import com.jdcloud.provider.pojo.vo.BetaMaleOrderVo;
import com.jdcloud.provider.pojo.vo.BetaMaleVo;
import com.jdcloud.provider.service.*;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.BigDecimalUtil;
import com.jdcloud.util.annotation.Log;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 贝塔狗---公狗表 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2019-04-10
 */
@Controller
@RequestMapping("/beta/betaMale")
public class BetaMaleController extends BaseController {

    @Autowired
    private BetaMaleService betaMaleService;
    @Autowired
    private ComboGroupService comboGroupService;
    @Autowired
    private BetaOrderService betaOrderService;
    @Resource
    private PersonalCardService personalCardService;
    @Resource
    private BetaMaleImportRecordService betaMaleImportRecordService;
    private String prefix = "beta/betaMale";

    @RequiresPermissions("betaMale:view")
    @GetMapping()
    public String orderUser(ModelMap mmap) {
        List<ComboGroup> list = comboGroupService.selectList(null);
        for (int i = 0; i < list.size(); i++) {
            ComboGroup comboGroup = list.get(i);
            comboGroup.setEarningsRatio(BigDecimalUtil.turnDown(NumberUtil.mul(comboGroup.getEarningsRatio(), 100), 2));
            list.set(i, comboGroup);
        }
        mmap.put("list", list);
        return prefix + "/list";
    }

    @GetMapping("/add")
    @RequiresPermissions("betaMale:add")
    public String add(ModelMap mmap) {
        return prefix + "/add";
    }

    /**
     * 列表
     */
    @RequiresPermissions("betaMaleList:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo selectBetaMaleListPage(BetaMaleDto betaMaleDto) {
        Page<BetaMaleVo> vo = betaMaleService.selectBetaMaleListPage(getPage(), betaMaleDto);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }

    /**
     * 增加(放狗)
     * llkflsd
     */
    @Log(title = "增加(放狗)", buinessType = AnnotationEnum.BusinessType.INSERT)
    @RequiresPermissions("betaMaleList:saveOrUpdate")
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public Wrapper<String> saveOrUpdate(BetaMaleAddDto betaMaleAddDto) {
        return betaMaleService.saveOrUpdate(betaMaleAddDto);
    }

    /**
     * 获取到套餐的信息
     */
    @PostMapping("/listAil")
    @ResponseBody
    public List<ComboGroup> listAil(BetaMaleAddDto betaMaleAddDto) {
        List<ComboGroup> list = comboGroupService.selectList(null);
        return list;
    }

    /**
     * 详情
     */
    @RequiresPermissions("betaMaleList:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        BetaMaleVo vo = betaMaleService.selectBetaMale(id);
        List<ComboGroup> list = comboGroupService.selectList(null);
        mmap.put("list", list);
        mmap.put("vo", vo);
        return prefix + "/edit";
    }

    /**
     * 修改预售时间
     */
    @Log(title = "预售时间", buinessType = AnnotationEnum.BusinessType.UPDATE)
    @RequiresPermissions("betaMaleList:updatePresellTime")
    @PostMapping("/updatePresellTime")
    @ResponseBody
    public Wrapper<String> updatePresellTime(BetaMaleAddDto betaMaleAddDto) {
        return betaMaleService.updatePresellTime(betaMaleAddDto);
    }


    /**
     * 回收详情
     */
    @RequiresPermissions("betaMaleList:editRecovery")
    @GetMapping("/editRecovery/{id}")
    public String editRecovery(@PathVariable("id") Integer id, ModelMap mmap) {
        BetaMaleVo vo = betaMaleService.selectBetaMale(id);
        List<ComboGroup> list = comboGroupService.selectList(null);
        if (vo.getUserId() != null) {
            PersonalCard card = personalCardService.selectDetail(vo.getUserId());
            vo.setName(card.getName());
            vo.setBankName(card.getBankName());
            vo.setAlipay(card.getAlipay());
            vo.setAlipayNumber(card.getAlipayNumber());
            vo.setWechat(card.getWechat());
            vo.setWechatNumber(card.getWechatNumber());
        }
        mmap.put("list", list);
        mmap.put("vo", vo);
        return prefix + "/editRecovery";
    }

    /**
     * 激活
     */
    @RequiresPermissions("betaMaleList:saveOrUpdate")
    @PostMapping("/updateEditRecovery")
    @ResponseBody
    public Wrapper<String> updateEditRecovery(BetaMaleAddDto betaMaleAddDto) {
        return betaMaleService.updateEditRecovery(betaMaleAddDto);
    }

    /**
     * 初始化 订单页面
     *
     * @param id @param mmap @return
     */
    @RequiresPermissions("betaOrderInfo:view")
    @GetMapping("/betaOrderInfoList/{id}")
    public String recordListInfo(@PathVariable("id") Integer id, ModelMap mmap) {
        mmap.put("id", id);
        return prefix + "/betaOrderList";
    }

    /**
     * 查询 狗狗订单列表
     */
    @RequiresPermissions("betaOrderListPage:list")
    @PostMapping("/betaOrderListPage/{id}")
    @ResponseBody
    public TableDataInfo betaOrderListPage(@PathVariable("id") Integer id) {
        Page<BetaOrder> vo = betaOrderService.betaOrderListPage(getPage(), id);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }

    /**
     * 订单详情接口
     */
    @RequiresPermissions("orderListDetails:edit")
    @GetMapping("/orderListDetails/{id}")
    public String orderListDetails(@PathVariable("id") Integer id, ModelMap mmap) {
        BetaMaleOrderVo betaMaleOrderVo = betaMaleService.orderListDetails(id);
        mmap.put("vo", betaMaleOrderVo);
        return prefix + "/betaOrderAdetails";
    }

    /**
     * 删除
     */
    @Log(title = "删除公狗", buinessType = AnnotationEnum.BusinessType.DELETE)
    @RequiresPermissions("orderList:delete")
    @PostMapping("/remove")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Wrapper<String> remove(String ids) {
        return toAjax(betaMaleService.deleteByIds(ids));
    }

    /**
     * 删除公狗
     * 2019-7-1
     * C
     */
    @Log(title = "删除公狗", buinessType = AnnotationEnum.BusinessType.DELETE)
    @RequiresPermissions("orderList:remove")
    @PostMapping("/removeNew/{id}")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Wrapper<String> updatePresellTime(@PathVariable("id") Integer id) {
        return betaMaleService.deleteNew(id);
    }

    /**
     * 查询订单列表
     */
    @RequiresPermissions("orderList:list")
    @GetMapping("/betaOrader/{id}")
    public String betaOraderDetail(@PathVariable("id") Integer id, ModelMap mmap) {
        BetaMale betaMale = betaMaleService.selectById(id);
        mmap.put("id", betaMale.getId());
        return prefix + "/betaOrderListInfo";
    }

    /**
     * @Description: 公狗导入表格模板下载
     * @Author: senghor
     * @Date: 2019/5/10 0010 14:34
     */
    @RequestMapping("/templateFile")
    @ResponseBody
    public Wrapper<String> templateFile(MultipartFile file) {
        return WrapMapper.ok("/templateFile/公狗模板.xlsx");
    }

    /**
     * @Description: 公狗导入表格
     * @Author: senghor
     * @Date: 2019/5/10 0010 14:34
     */
    @Log(title = "公狗导入", buinessType = AnnotationEnum.BusinessType.IMPORT)
    @RequiresPermissions("orderList:importExcel")
    @RequestMapping("/importExcel")
    @ResponseBody
    public Wrapper<String> importExcel(MultipartFile file) {
        return betaMaleService.importExcel(file);
    }

    /**
     * @Description: 刷新缓存中的剩余公狗数据
     * @Author: senghor
     * @Date: 2019/6/8 13:30
     */
    @RequiresPermissions("betaMale:refreshRedis")
    @PostMapping("/refreshRedis")
    @ResponseBody
    public Wrapper<String> refreshRedis() {
        return betaMaleService.refreshRedis();
    }


    /**
     * 初始化 导入记录页面
     *
     * @param mmap
     * @return
     */
    @RequiresPermissions("betaMaleImportRecord:list")
    @GetMapping("/importRecord")
    public String importRecord(ModelMap mmap) {
        return prefix + "/importRecord";
    }

    /**
     * 导入记录列表
     */
    @RequiresPermissions("betaMaleImportRecord:list")
    @PostMapping("/importRecordList")
    @ResponseBody
    public TableDataInfo importRecordList(BetaMaleImportRecordDto betaMaleImportRecordDto) {
        Page<BetaMaleImportRecordVo> vo = betaMaleImportRecordService.selectBetaMaleImportRecordListPage(getPage(), betaMaleImportRecordDto);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }

    /**
     * @Description: 设置公狗优先级
     * @Author: senghor
     * @Date: 2019/8/17 23:23
     */
    @Log(title = "公狗优先级导入", buinessType = AnnotationEnum.BusinessType.IMPORT)
    @RequiresPermissions("betaMale:importExcelWaitSum")
    @RequestMapping("/importExcelWaitSum")
    @ResponseBody
    public Wrapper<String> importExcelWaitSum(MultipartFile fileWaitsum) {
        return betaMaleService.importExcelWaitSum(fileWaitsum);
    }

    /**
     * @Description: 清除所有公狗优先级
     * @Author: senghor
     * @Date: 2019/8/18 0:50
     */
    @Log(title = "清除所有公狗优先级", buinessType = AnnotationEnum.BusinessType.UPDATE)
    @RequiresPermissions("betaMale:clearWaitSum")
    @RequestMapping("/clearWaitSum")
    @ResponseBody
    public Wrapper<String> clearWaitSum() {
        // 清空之前数据的优先级
        betaMaleService.clearWaitSum();
        return WrapMapper.ok();
    }
}

