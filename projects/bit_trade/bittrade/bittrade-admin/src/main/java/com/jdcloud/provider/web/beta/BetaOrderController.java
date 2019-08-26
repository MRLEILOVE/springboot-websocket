package com.jdcloud.provider.web.beta;


import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.provider.dto.BetaMaleOrderDto;
import com.jdcloud.provider.dto.BetaOrderDto;
import com.jdcloud.provider.pojo.ComboGroup;
import com.jdcloud.provider.pojo.vo.BetaMaleOrderVo;
import com.jdcloud.provider.pojo.vo.BetaOrderVo;
import com.jdcloud.provider.service.BetaOrderService;
import com.jdcloud.provider.service.ComboGroupService;
import com.jdcloud.provider.utils.ExcelUtil;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.BigDecimalUtil;
import com.jdcloud.util.annotation.Log;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 贝塔狗---订单表（每笔装让记录都会生成一条订单数据作为记录） 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2019-04-15
 */
@Controller
@RequestMapping("beta/betaOrder")
public class BetaOrderController extends BaseController {

    @Resource
    private BetaOrderService betaOrderService;
    @Resource
    private ComboGroupService comboGroupService;

    private String prefix = "beta/betaOrder";

    @RequiresPermissions("betaOrderInfo:view")
    @GetMapping()
    public String betaOrderInfo(ModelMap mmap) {
        List<ComboGroup> list = comboGroupService.selectList(null);
        for (int i = 0; i < list.size(); i++) {
            ComboGroup comboGroup = list.get(i);
            comboGroup.setEarningsRatio(BigDecimalUtil.turnDown(NumberUtil.mul(comboGroup.getEarningsRatio(), 100), 2));
            list.set(i, comboGroup);
        }
        mmap.put("list", list);
        return prefix + "/list";
    }

    /**
     * 订单列表
     */
    @RequiresPermissions("betaOrderListPage:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo betaOrderListPageAppeal(BetaMaleOrderDto betaMaleOrderDto) {
        Page<BetaMaleOrderVo> vo = betaOrderService.betaOrderListPageAppeal(getPage(), betaMaleOrderDto);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }

    /**
     * 订单详情接口
     */
    @RequiresPermissions("orderListDetails:edit")
    @GetMapping("/orderListDetails/{id}")
    public String orderListDetails(@PathVariable("id") Integer id, ModelMap mmap) {
        BetaMaleOrderVo betaMaleOrderVo = betaOrderService.orderListDetails(id);

        StringBuilder startTime = new StringBuilder(String.valueOf(betaMaleOrderVo.getStartTime()));
        betaMaleOrderVo.setStartTimeStr(startTime.insert(2, ":").toString());

        StringBuilder endTime = new StringBuilder(String.valueOf(betaMaleOrderVo.getEndTime()));
        betaMaleOrderVo.setEndTimeStr(endTime.insert(2, ":").toString());

        mmap.put("vo", betaMaleOrderVo);
        return prefix + "/edit";
    }

    /** 
     * @Description: 导出表格
     * @Author: senghor
     * @Date: 2019/5/16 0016 15:53
     */
    @Log(title = "导出订单", buinessType = AnnotationEnum.BusinessType.EXPORT)
    @RequiresPermissions("betaOrder:export")
    @PostMapping("/export")
    @ResponseBody
    public Wrapper<String> export(BetaMaleOrderDto betaMaleOrderDto) {
        List<BetaMaleOrderVo> vo = betaOrderService.exportBetaOrderList(betaMaleOrderDto);
        ExcelUtil<BetaMaleOrderVo> util = new ExcelUtil<BetaMaleOrderVo>(BetaMaleOrderVo.class);
        return util.exportExcel(vo, "list");
    }

    /**
     * 放狗
     */
    @Log(title = "放狗", buinessType = AnnotationEnum.BusinessType.GRANT)
    @RequiresPermissions("orderList:audit")
    @PostMapping("/audit")
    @ResponseBody
    public Wrapper auditRelease(Integer id, String remarks) {
        return betaOrderService.auditRelease(id, remarks);
    }

    /**
     * 拒绝放狗
     */
    @Log(title = "拒绝放狗", buinessType = AnnotationEnum.BusinessType.GRANT)
    @RequiresPermissions("orderList:auditRefuseDogo")
    @PostMapping("/auditRefuseDogo")
    @ResponseBody
    public Wrapper auditRefuseDogo(Integer id, String remarks) {
        return betaOrderService.auditRefuseDogo(id, remarks);
    }

    /**
     * 查询订单历史初始化页面
     */
    @RequiresPermissions("betaOrderListInfo:list")
    @GetMapping("/betaOrderListInfo/{id}")
    public String betaOrderListInfo(@PathVariable("id") Integer id, ModelMap mmap) {
        mmap.put("id", id);
        System.out.println(id);
        return prefix + "/betaOrderListInfo";
    }

    /**
     * @Description: 跳转至持仓数据监控
     * @Author: senghor
     * @Date: 2019/5/14 0014 17:39
     */
    @RequiresPermissions("selectCountUserBeta:view")
    @RequestMapping("/countOrder/view")
    public String selectCountUserBeta(ModelMap mmap) {
        return prefix + "/countOrderList";
    }

    /**
     * 持仓数据监控列表
     */
    @RequiresPermissions("selectCountUserBeta:list")
    @RequestMapping("/countOrder/list")
    @ResponseBody
    public TableDataInfo selectCountUserBeta(BetaOrderDto betaOrderDto) {
        Page<BetaOrderVo> vo = betaOrderService.selectCountUserBeta(getPage(),betaOrderDto);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }

    /**
     * @Description: 一键客诉
     * @param ids :
     * @Author: zjun
     * @Date: 2019/8/6 15:38
     */
    @RequiresPermissions("orderList:auditRefuseDogo")
    @PostMapping("/customerComplaint")
    @ResponseBody
    public Wrapper<String> customerComplaint(String ids) {
//        return betaOrderService.customerComplaint();
        return WrapMapper.ok();
    }

    /**
     * @Description: 一键拒绝放狗
     * @Author: zjun
     * @Date: 2019/8/6 17:26
     */
    @Log(title = "拒绝放狗", buinessType = AnnotationEnum.BusinessType.GRANT)
    @RequiresPermissions("orderList:auditRefuseDogo")
    @PostMapping("/auditRefuseDogoAll")
    @ResponseBody
    public Wrapper auditRefuseDogoAll() {
//        betaOrderService.auditRefuseDogoAll();
        return WrapMapper.ok("一键拒绝放狗成功");
    }

    /**
     * 初始化审核列表页面
     */
    @RequiresPermissions("betaOrder:auditList")
    @GetMapping("/auditListView")
    public String auditListView(ModelMap mmap) {
        List<ComboGroup> list = comboGroupService.selectList(null);
        for (int i = 0; i < list.size(); i++) {
            ComboGroup comboGroup = list.get(i);
            comboGroup.setEarningsRatio(BigDecimalUtil.turnDown(NumberUtil.mul(comboGroup.getEarningsRatio(), 100), 2));
            list.set(i, comboGroup);
        }
        mmap.put("list", list);
        return prefix + "/auditListView";
    }

    /**
     * 打包审核列表
     */
    @RequiresPermissions("betaOrder:auditList")
    @PostMapping("/auditListData")
    @ResponseBody
    public TableDataInfo auditList(BetaMaleOrderDto betaMaleOrderDto) {
        Page<BetaMaleOrderVo> vo = betaOrderService.betaOrderAuditListPage(getPage(), betaMaleOrderDto);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }

    /**
     * 打包审核
     */
    @Log(title = "打包审核", buinessType = AnnotationEnum.BusinessType.GRANT)
    @RequiresPermissions("betaOrder:packAudit")
    @PostMapping("/packAudit")
    @ResponseBody
    public Wrapper packAudit(Integer id, Integer type) {
        return betaOrderService.packAudit(id,type);
    }

}

