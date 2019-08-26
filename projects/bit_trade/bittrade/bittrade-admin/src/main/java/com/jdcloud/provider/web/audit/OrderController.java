package com.jdcloud.provider.web.audit;


import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.BetaMaleOrderDto;
import com.jdcloud.provider.dto.OrderDto;
import com.jdcloud.provider.pojo.Order;
import com.jdcloud.provider.pojo.vo.BetaMaleOrderVo;
import com.jdcloud.provider.pojo.vo.OrderVo;
import com.jdcloud.provider.service.OrderService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
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
 * @since 2019-07-16
 */
@Controller
@RequestMapping("/audit/order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    private String prefix = "audit/order";

    /**
     * 初始化
     * @return
     */
    @RequiresPermissions("orderInfo:view")
    @GetMapping()
    public String orderInfo(){
        return prefix + "/list";
    }

    /**
     * 订单列表
     */
    @RequiresPermissions("orderList:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo orderList(OrderDto orderDto) {
        Page<OrderVo> vo = orderService.orderList(getPage(),orderDto);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }

}

