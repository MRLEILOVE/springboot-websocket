package com.jdcloud.provider.web.c2c;


import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.core.redis.RedisTemplate;
import com.jdcloud.provider.dto.OrderUserDto;
import com.jdcloud.provider.dto.OrderUserListDto;
import com.jdcloud.provider.pojo.OrderUser;
import com.jdcloud.provider.pojo.vo.OrderUserDetailsVo;
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

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author helen
 */
@Controller
@RequestMapping("/c2c/orderUser")
public class OrderUserController extends BaseController {

    private String prefix = "c2c/orderUser";
    @Autowired
    private OrderUserService orderUserService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequiresPermissions("orderUser:view")
    @GetMapping()
    public String orderUser() {
        return prefix + "/orderUser";
    }

    /**
     * 获取订单列表
     *
     * @return
     */
    @RequiresPermissions("orderUser:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo orderUserList(OrderUserListDto orderUserListDto) {
        Page<OrderUserDetailsVo> orderUserListPage = orderUserService.selectOrderUserDetailsPage(getPage(), orderUserListDto);
        return new TableDataInfo(orderUserListPage.getRecords(), orderUserListPage.getTotal());
    }

    /**
     * 导出购买出售订单
     *
     * @param orderUserListDto
     * @return
     * @throws IOException
     */
    @Log(title = "导出法币订单", buinessType = AnnotationEnum.BusinessType.EXPORT)
    @RequiresPermissions("orderUser:export")
    @PostMapping("/export")
    @ResponseBody
    public Wrapper<String> export(OrderUserListDto orderUserListDto) throws IOException {
        List<OrderUserDetailsVo> orderUserExcelList = orderUserService.OrderUserExcelList(orderUserListDto);
        ExcelUtil<OrderUserDetailsVo> util = new ExcelUtil<OrderUserDetailsVo>(OrderUserDetailsVo.class);
        return util.exportExcel(orderUserExcelList, "orderUserExcelList");
    }


    /**
     * 出售购买详情
     *
     * @param id
     * @param mmap
     * @return
     */
    @RequiresPermissions("orderUser:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap mmap) {
        mmap.put("orderUser", orderUserService.selectOrderUserDetails(id));
        return prefix + "/detail";
    }

    /**
     * 放币接口
     *
     * @param orderUserDto
     * @return
     */
    @Log(title = "法币/放币", buinessType = AnnotationEnum.BusinessType.PUTMONEY)
    @RequiresPermissions("orderUser:updateOrderUserCoinRelease")
    @RequestMapping("/updateOrderUserCoinRelease")
    @ResponseBody
    public Wrapper updateOrderUserCoinRelease(OrderUserDto orderUserDto) {
        return orderUserService.updateOrderUserCoinRelease(orderUserDto);
    }

    /**
     * 取消订单
     */
    @Log(title = "法币/取消放币", buinessType = AnnotationEnum.BusinessType.CANCELPUTMONEY)
    @RequiresPermissions("orderUser:updateOrderUserCoinCancel")
    @RequestMapping("/updateOrderUserCoinCancel")
    @ResponseBody
    public Wrapper updateOrderUserCoinCancel(OrderUserDto orderUserDto) {
        return orderUserService.updateOrderUserCoinCancel(orderUserDto);
    }

    /**
     * 查询凭证
     */
    @RequiresPermissions("orderUser:selectCredentials")
    @GetMapping("/selectCredentialss/{id}")
    public String selectCredentials(@PathVariable("id") Integer id, ModelMap mmap) {
        OrderUser orderUser= orderUserService.selectById(id);
        mmap.put("orderUser", orderUser.getVoucher());
        mmap.put("userId", orderUser.getUserId());
        mmap.put("orderEntrustUserId", orderUser.getOrderEntrustUserId());
        mmap.put("id", orderUser.getId());
        return prefix + "/selectCredentials";
    }

}

