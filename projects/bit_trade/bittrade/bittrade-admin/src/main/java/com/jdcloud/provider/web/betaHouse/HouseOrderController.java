package com.jdcloud.provider.web.betaHouse;


import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.HouseOrderDto;
import com.jdcloud.provider.dto.HouseProductDto;
import com.jdcloud.provider.pojo.HouseProduct;
import com.jdcloud.provider.pojo.vo.HouseOrderVo;
import com.jdcloud.provider.service.HouseOrderService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 商品订单表 前端控制器
 * </p>
 * @author cc
 * @since 2019-06-19
 */
@Controller
@RequestMapping("/betaHouse/houseOrder")
public class HouseOrderController extends BaseController {

   @Autowired
   private HouseOrderService houseOrderService;

    private String prefix = "betaHouse/houseOrder";
    /**
     * 商品订单初始化
     * cc
     * 2019-06-13
     */
    @RequiresPermissions("houseOrder:view")
    @GetMapping()
    public String houseOrderInfo() {
        return prefix + "/list";
    }

    /**
     * 商品订单列表
     * @param houseOrderDto
     * @return
     */
    @RequiresPermissions("houseOrder:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo getHouseOrder(HouseOrderDto houseOrderDto) {
        Page<HouseOrderVo> vo = houseOrderService.getHouseOrder(getPage(), houseOrderDto);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }

}

