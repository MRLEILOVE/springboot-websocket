package com.jdcloud.provider.web.sys;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.gson.JsonObject;
import com.jdcloud.base.constant.GlobalConstant;
import com.jdcloud.base.enums.BetaEnum;
import com.jdcloud.provider.dto.IndexDataDto;
import com.jdcloud.provider.pojo.BetaOrder;
import com.jdcloud.provider.pojo.SysUser;
import com.jdcloud.provider.service.BetaOrderService;
import com.jdcloud.provider.service.StatisticsService;
import com.jdcloud.provider.service.SysMenuService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * .首页
 *
 * @author ourblue
 */
@Controller
public class SysIndexController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private BetaOrderService betaOrderService;

    @GetMapping("/index")
    public String index(ModelMap mmap) {
        SysUser user = getUser();
        // 根据用户Id取出拥有的菜单
        mmap.put("menus", sysMenuService.selectMenusByUser(user));
        user.setSex(getSex(user.getSex()));
        mmap.put("user", user);
        mmap.put("copyrightYear", GlobalConstant.CURRENT_YEAR);
        return "index";
    }

    // 系统介绍
    @GetMapping("/system/main")
    public String main(ModelMap mmap) {
        mmap.put("indexData", statisticsService.indexDataInit());
        return "main";
    }

    /**
     * @Description: 用户监控
     * @Author: senghor
     * @Date: 2019/5/27 0027 20:53
     */
    @RequiresPermissions("index:userMonitor")
    @RequestMapping("/index/userMonitor")
    @ResponseBody
    public Wrapper<IndexDataDto> userMonitor() {
        return statisticsService.userMonitor();
    }

    /**
     * @Description: 抢狗监控
     * @Author: senghor
     * @Date: 2019/5/27 0027 20:53
     */
    @RequiresPermissions("index:betaMonitor")
    @RequestMapping("/index/betaMonitor")
    @ResponseBody
    public Wrapper<IndexDataDto> betaMonitor() {
        return statisticsService.betaMonitor();
    }

    /**
     * @Description: 实名监控
     * @Author: senghor
     * @Date: 2019/5/27 0027 20:53
     */
    @RequiresPermissions("index:cardMonitor")
    @RequestMapping("/index/cardMonitor")
    @ResponseBody
    public Wrapper<IndexDataDto> cardMonitor() {
        return statisticsService.cardMonitor();
    }

    /**
     * @Description: 持仓监控
     * @Author: senghor
     * @Date: 2019/5/27 0027 20:53
     */
    @RequiresPermissions("index:accountMonitor")
    @RequestMapping("/index/accountMonitor")
    @ResponseBody
    public Wrapper<IndexDataDto> accountMonitor() {
        return statisticsService.accountMonitor();
    }

    /**
     * @Description: 持仓监控
     * @Author: senghor
     * @Date: 2019/5/27 0027 20:53
     */
    @RequestMapping("/index/getRegisterECharts")
    @ResponseBody
    public Wrapper getRegisterECharts() {
        JSONObject jsonObject = new JSONObject();
        int showRow = 5;
        String[] date = new String[showRow];
        Integer[] registerSum = new Integer[showRow];
        Integer[] authSum = new Integer[showRow];
        for (int i = 0; i < showRow; i++) {
            date[i] = DateUtils.format(new Date(), "HH:mm");
            registerSum[i] = 20 + i;
            authSum[i] = 20 + i;
        }
        jsonObject.put("date",date);
        jsonObject.put("registerSum",registerSum);
        jsonObject.put("authSum",authSum);
        return WrapMapper.ok(jsonObject);
    }

    /**
     * @Description: 打包出售审核数
     * @Author: zjun
     * @Date: 2019/8/14 14:53
     */
    @RequiresPermissions("betaOrder:auditList")
    @RequestMapping("/index/packAuditNum")
    @ResponseBody
    public Wrapper<String> packAuditNum() {
        BetaOrder betaOrder=new BetaOrder();
        betaOrder.setOrderStauts(BetaEnum.orderStauts.AUDIT.getCode());
        int num=betaOrderService.selectCount(new EntityWrapper<>(betaOrder));
        return WrapMapper.ok(num+"");
    }
}
