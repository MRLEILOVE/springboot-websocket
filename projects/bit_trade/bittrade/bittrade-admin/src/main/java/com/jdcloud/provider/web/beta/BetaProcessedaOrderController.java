package com.jdcloud.provider.web.beta;


import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.provider.dto.BetaProcessedaOrderDto;
import com.jdcloud.provider.pojo.vo.BetaProcessedaOrderVo;
import com.jdcloud.provider.service.BetaProcessedaOrderService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.annotation.Log;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * beta订单处理表  前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2019-04-24
 */
@Controller
@RequestMapping("/beta/betaProcessedaOrder")
public class BetaProcessedaOrderController extends BaseController {

    private String		prefix	= "beta/betaProcessedaOrder";

    @Autowired
    private BetaProcessedaOrderService betaProcessedaOrderService;

    /**
     * 初始化 处理订单列表
     * @return
     */
    @RequiresPermissions("betaProcessedaOrder:info")
    @GetMapping()
    public String listInfo() {
        return prefix + "/list";
    }

    /**
     * 查询处理订单表
     * @param betaProcessedaOrderDto
     * @return
     */
    @RequiresPermissions("betaProcessedaOrder:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BetaProcessedaOrderDto betaProcessedaOrderDto) {
        Page<BetaProcessedaOrderVo> list =  betaProcessedaOrderService.betaProcessedaOrderListPage(getPage(),betaProcessedaOrderDto);
        return new TableDataInfo( list.getRecords(), list.getTotal() );
    }

    /**
     * 订单详情接口
     */
    @RequiresPermissions("betaProcessedaOrder:edit")
    @GetMapping("/edit/{id}")
    public String orderListDetails(@PathVariable("id") Integer id, ModelMap mmap) {
        BetaProcessedaOrderVo vo= betaProcessedaOrderService.betaProcessedaOrderDetails(id);
        mmap.put( "vo", vo);
        return prefix + "/edit";
    }


    /**
     * 删除处理的订单
     */
    @Log(title = "删除处理的订单", buinessType = AnnotationEnum.BusinessType.DELETE)
    @RequiresPermissions("betaProcessedaOrderdelete:delete")
    @PostMapping("/remove")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Wrapper<String> betaProcessedaOrderdelete(String ids) {
        return toAjax( betaProcessedaOrderService.betaProcessedaOrderdeletes( ids ) );
    }

    /**
     * 拒绝放狗（撤回功能）
     *C
     *2019-7-21
     */
    @Log(title = "拒绝放狗（撤回）", buinessType = AnnotationEnum.BusinessType.UPDATE)
   @RequiresPermissions("processedaOrder:updateRecall")
    @PostMapping("/updateRefuseDogoRecall/{id}")
    @ResponseBody
    public Wrapper updateRefuseDogoRecall(@PathVariable("id") Integer id) {
        return  betaProcessedaOrderService.updateRefuseDogoRecall(id);
    }

    /**
     * 放狗（撤回）
     * C
     * 2019-7-21
     */
    @Log(title = "放狗（撤回）", buinessType = AnnotationEnum.BusinessType.UPDATE)
    @RequiresPermissions("processedaOrder:updateputTheDog")
    @PostMapping("/updateputTheDog/{id}")
    @ResponseBody
    public Wrapper updateputTheDog(@PathVariable("id") Integer id) {
        return  betaProcessedaOrderService.updateputTheDog(id);
    }

}

