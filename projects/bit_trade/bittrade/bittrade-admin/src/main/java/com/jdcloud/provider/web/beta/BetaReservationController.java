package com.jdcloud.provider.web.beta;


import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.provider.dto.BetaReservationDto;
import com.jdcloud.provider.pojo.BetaReservation;
import com.jdcloud.provider.pojo.ComboGroup;
import com.jdcloud.provider.pojo.vo.BetaReservationVo;
import com.jdcloud.provider.service.BetaReservationService;
import com.jdcloud.provider.service.ComboGroupService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.BigDecimalUtil;
import com.jdcloud.util.annotation.Log;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


/**
 * <p>
 * 贝塔狗---预约表 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2019-04-24
 */
@Controller
@RequestMapping("/beta/betaReservation")
public class BetaReservationController extends BaseController {

    @Autowired
    private BetaReservationService betaReservationService;

    @Autowired
    private ComboGroupService comboGroupService;

    private String prefix = "beta/betaReservation";

    @RequiresPermissions("betaReservation:view")
    @GetMapping()
    public String list(ModelMap mmap) {
        List<ComboGroup> list= comboGroupService.selectList(null);
        for (int i = 0; i < list.size(); i++) {
            ComboGroup comboGroup = list.get(i);
            comboGroup.setEarningsRatio(BigDecimalUtil.turnDown(NumberUtil.mul(comboGroup.getEarningsRatio(), 100), 2));
            list.set(i, comboGroup);
        }
        mmap.put( "list", list);
        return prefix + "/list";
    }

    /**
     * 列表
     */
    @RequiresPermissions("betaReservation:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo selectBetaReservationListPage(BetaReservationDto dto) {
        Page<BetaReservationVo> vo = betaReservationService.selectBetaReservationListPage(getPage(), dto);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }

     /**
     * 详情
     */
    @RequiresPermissions("betaMaleList:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        BetaReservationVo vo = betaReservationService.selectBetaReservation(id);
        vo.setStartTime(new StringBuilder(vo.getStartTime()).insert(2, ":").toString());
        vo.setEndTime(new StringBuilder(vo.getEndTime()).insert(2, ":").toString());
        mmap.put( "vo", vo);
        return prefix + "/edit";
    }


    /**
     * 增加或者是修改
     */
    @Log(title = "预约增加修改", buinessType = AnnotationEnum.BusinessType.UPDATE)
    @RequiresPermissions("betaHeader:saveOrUpdate")
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public Wrapper<String> saveOrUpdate(BetaReservation beta) {
        return betaReservationService.saveOrUpdate(beta);
    }

    /**
     * 删除
     */
    @Log(title = "预约删除", buinessType = AnnotationEnum.BusinessType.DELETE)
    @RequiresPermissions("betaReservation:delete")
    @PostMapping("/remove")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Wrapper<String> comboGroupdelete(String ids) {
        return toAjax( betaReservationService.deleteByIds(ids));
    }

    /**
     * @Description: 刷新缓存中的预约数据
     * @Author: senghor
     * @Date: 2019/6/8 13:30
     */
    @RequiresPermissions("betaReservation:refreshRedis")
    @PostMapping("/refreshRedis")
    @ResponseBody
    public Wrapper<String> refreshRedis() {
        return betaReservationService.refreshRedis();
    }


}

