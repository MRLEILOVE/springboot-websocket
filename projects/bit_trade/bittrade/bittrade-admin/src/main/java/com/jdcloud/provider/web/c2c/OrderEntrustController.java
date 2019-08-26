package com.jdcloud.provider.web.c2c;


import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.provider.dto.OrderEntrustPageDto;
import com.jdcloud.provider.dto.OrderUserListDto;
import com.jdcloud.provider.pojo.vo.OrderEntrustVo;
import com.jdcloud.provider.pojo.vo.OrderUserDetailsVo;
import com.jdcloud.provider.service.OrderEntrustService;
import com.jdcloud.provider.service.OrderUserService;
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

import java.util.List;

/**
 * <p>
 * 委托订单表 前端控制器
 * </p>
 *
 * @author helen
 * @since 2019-03-03
 */
@Controller
@RequestMapping("/c2c/orderEntrust")
public class OrderEntrustController extends BaseController {
    private String			prefix	= "c2c/orderEntrust";
    @Autowired
    private OrderEntrustService orderEntrustService;
    @Autowired
    private OrderUserService orderUserService;

    @RequiresPermissions("orderEntrust:view")
    @GetMapping()
    public String orderUser() {
        return prefix + "/orderEntrust";
    }

    /**
     * 委托单的列表
     * @return
     */
    @RequiresPermissions("orderEntrust:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo orderEntrustList(OrderEntrustPageDto orderEntrustPageDto){
        Page<OrderEntrustVo> orderEntrustPageVo = orderEntrustService.orderEntrustServiceList(getPage(),orderEntrustPageDto);
        return new TableDataInfo(orderEntrustPageVo.getRecords(), orderEntrustPageVo.getTotal() );
    }

    /**
     * 进入委托详情列表
     * @return
     */
    @RequiresPermissions("orderEntrust:orderUserDetail")
    @GetMapping("/orderUserDetail/{id}")
    public String orderUserDetail(@PathVariable("id")Integer id, ModelMap mmap) {
        mmap.put( "id",id);
        return prefix + "/orderEntrustDetail";
    }

    /**
     * 委托订单之购买出售的订单列表
     * @param
     */
    @RequiresPermissions("orderEntrust:detail")
    @PostMapping("/orderEntrustDetaillist")
    @ResponseBody
    public TableDataInfo orderUserList(OrderUserListDto orderUserListDto){
        Page<OrderUserDetailsVo>  orderUserListPage = orderUserService.selectOrderUserDetailsPage(getPage(),orderUserListDto);
        return new TableDataInfo( orderUserListPage.getRecords(), orderUserListPage.getTotal() );
    }

    /**
     * 购买出售订单详情
     */
    @RequiresPermissions("orderUser:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap mmap){
        mmap.put( "orderUser", orderUserService.selectOrderUserDetails(id));
        return prefix + "/detail";
    }

    /**
     * @Description: 委托单导出代码
     * @Author: senghor
     * @Date: 2019/5/13 0013 20:34
     */
    @Log(title = "导出委托单", buinessType = AnnotationEnum.BusinessType.EXPORT)
    @RequiresPermissions("orderUser:export")
    @RequestMapping("/export")
    @ResponseBody
    public Wrapper export(OrderEntrustPageDto orderEntrustPageDto) {
        List<OrderEntrustVo> orderEntrustPageVos = orderEntrustService.selectExportList(orderEntrustPageDto);
        ExcelUtil<OrderEntrustVo> util = new ExcelUtil<OrderEntrustVo>(OrderEntrustVo.class);
        return util.exportExcel(orderEntrustPageVos, "用户数据");
    }
}

