package com.jdcloud.provider.web.audit;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.base.enums.AuditEnum;
import com.jdcloud.provider.dto.TOrderDto;
import com.jdcloud.provider.pojo.TOrder;
import com.jdcloud.provider.pojo.vo.TOrderVo;
import com.jdcloud.provider.service.ITOrderService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.annotation.Log;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/audit/customer")
public class CustomerController extends BaseController {
    private String prefix = "audit/withdraw";
    @Autowired
    private ITOrderService orderService;


    @RequiresPermissions("newWithDrawInfo:view")
    @GetMapping()
    public String newWithDrawInfo() {
        return prefix + "/customer";
    }

    /**
     * 客服审核列表
     *
     * @param
     * @return
     */
    @RequiresPermissions("customer:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo getCustomer(TOrderDto tOrderDto) {
        tOrderDto.setType(AuditEnum.orderType.NOTAUDITED.getCode().toString());
        Page<TOrderVo> page = orderService.getCustomerList(getPage(), tOrderDto);
        return new TableDataInfo(page.getRecords(), page.getTotal());
    }

    /**
     * 客服审核列表
     * C
     *  要求，先提交的审核在前面，时间排序
     */
    @PostMapping("/getCustomerList")
    @ResponseBody
    public TableDataInfo getCustomerList(TOrderDto tOrderDto) {
        Page<TOrderVo> page = orderService.getCustomerPage(getPage(), tOrderDto);
        return new TableDataInfo(page.getRecords(), page.getTotal());
    }


    /**
     * 客服审核
     * @param id
     * @return
     * @throws Exception
     */
    @RequiresPermissions("customer:audit")
    @PostMapping("/audit/{id}")
    @ResponseBody
    @Log(title = "客服拒绝",buinessType = AnnotationEnum.BusinessType.UPDATE)
    public Wrapper audit(@PathVariable Integer id) throws Exception {
        TOrder tOrder = orderService.selectById(id);
        if (tOrder.getType() != AuditEnum.orderType.NOTAUDITED.getCode()) {
            throw new Exception("审核异常");
        }
        tOrder.setType(AuditEnum.orderType.CSAUDITED.getCode());
        orderService.updateById(tOrder);
        return WrapMapper.ok("成功");
    }


    /**
     *  客服审核详情
     */
    @RequiresPermissions("customer:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap mmap) {
        TOrderVo TOrderVo = orderService.getCustomerDetail(id);
        mmap.put( "vo",TOrderVo);
        return prefix+"detail";
    }

    /**
     * 拒绝
     * @param id
     * @return
     * @throws Exception
     */
    @RequiresPermissions("customer:refuse")
    @PostMapping("/refuse/{id}")
    @ResponseBody
    public Wrapper refuse(@PathVariable Integer id) throws Exception {
        return orderService.refuse(id);

    }

    /**
     * 通过
     * @param ids
     * @return
     */
    @RequiresPermissions("customer:auditPass")
    @PostMapping(value = "/auditPass", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Wrapper<String> auditPass(@RequestParam("ids[]") String[] ids) {
        // return rechargeOrderService.auditPass( orderNos );
        EntityWrapper<TOrder> entityWrapper = new EntityWrapper<>();
        entityWrapper.in("id", ids);
        List<TOrder> tOrders = orderService.selectList(entityWrapper);
        System.out.println(tOrders);
        for (TOrder order : tOrders) {
            order.setType(2);
        }
        boolean b = orderService.updateBatchById(tOrders);
        if (b) {
            return WrapMapper.ok("成功");
        }
        return WrapMapper.ok("系统异常");
    }

    /**
     * 拒绝
     * @param ids
     * @return
     */
    @RequiresPermissions("customer:refuseAll")
    @PostMapping(value = "/refuseAll", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Wrapper<String> refuseAll(@RequestParam("ids[]") String[] ids) {

        // return rechargeOrderService.auditPass( orderNos );
        EntityWrapper<TOrder> entityWrapper = new EntityWrapper<>();
        entityWrapper.in("id", ids);
        List<TOrder> tOrders = orderService.selectList(entityWrapper);
        System.out.println(tOrders);
        for (TOrder order : tOrders) {
            order.setType(4);
        }
        boolean b = orderService.updateBatchById(tOrders);
        if (b) {
            return WrapMapper.ok("成功");
        }
        return WrapMapper.ok("系统异常");
    }


    //提现列表
//    @GetMapping("/customer")
//    @ApiOperation(value="提现列表", notes="提现列表")
//    @ResponseBody
//    public Tab withdraw(TOrderDto order){
//
//        Page<TOrder> page =  orderService.selectTOrderList(order);
//
//        return prefix+"/withdraw1";
//
//    }


    /**
     * 提现详情<br>
     *
     * @return:
     * @Author: yongheng
     * @Date: 2018/11/26 19:56
     */
  /*  @RequiresPermissions("audit:withdraw:audit")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, ModelMap mmap) {
        TOrder tOrder = orderService.selectById(id);
        mmap.put( "detail",tOrder );
        return  prefix+"/audit";
    }*/
    /*@RequiresPermissions("audit:withdraw:audit")*/


}
