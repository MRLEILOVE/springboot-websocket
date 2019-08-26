package com.jdcloud.provider.web.config;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.base.enums.C2cEnum;
import com.jdcloud.provider.pojo.AccountConfig;
import com.jdcloud.provider.pojo.TradingOn;
import com.jdcloud.provider.service.AccountConfigService;
import com.jdcloud.provider.service.TradingOnService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.StringUtils;
import com.jdcloud.util.annotation.Log;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 交易对管理 前端控制器
 * </p>
 *
 * @author
 * @since 2019-07-26
 */
@Controller
@RequestMapping("/config/tradingOnConfig")
public class TradingOnController extends BaseController {
    private String prefix = "config/tradingOnConfig";

    @Autowired
    private TradingOnService tradingOnService;
    @Autowired
    private AccountConfigService accountConfigService;

    @RequiresPermissions("config:tradingOn:view")
    @GetMapping()
    public String tradingOnInfo() {
        return prefix + "/list";
    }

    /**
     * 交易对列表
     *
     * @param tradingOn
     * @return
     */
    @RequiresPermissions("config:tradingOn:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TradingOn tradingOn) {
        EntityWrapper entity = new EntityWrapper();
        if (StringUtil.isNotBlank(tradingOn.getNmuberName())) {
            entity.eq("nmuber_name", tradingOn.getNmuberName());
        }
        Page<TradingOn> page = tradingOnService.selectPage(getPage(), entity);
        return new TableDataInfo(page.getRecords(), page.getTotal());
    }


    /**
     * 交易对币种配置初始化
     * @return
     */
    @RequiresPermissions("tradingOnInfo:addInfo")
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        EntityWrapper entity= new EntityWrapper();
        entity.eq("top_up_coin", C2cEnum.topUpCoin.YES.getCode());
        entity.orderBy("sort",false);
        List<AccountConfig> list = accountConfigService.selectList(entity);
        mmap.put("accountConfigList", list);
        return prefix + "/add";
    }


    /**
     * 保存币种配置
     */
    @Log(title = "保存账户币种配置", buinessType = AnnotationEnum.BusinessType.INSERT)
    @RequiresPermissions("accountConfig:addAccountConfig")
    @PostMapping("/saveTradingOn")
    @ResponseBody
    public Wrapper<String> saveTradingOn(TradingOn tradingOn) {
        return  tradingOnService.saveTradingOn(tradingOn);
    }


    /**
     * 修改交易对
     */
    @Log(title = "禁用交易对", buinessType = AnnotationEnum.BusinessType.UPDATE)
    @RequiresPermissions("updateTradingOn:update")
    @PostMapping("/updateTradingOn/{id}")
    @ResponseBody
    public Wrapper<String> updateTransferDirection(@PathVariable("id") Integer id) {
        TradingOn config = tradingOnService.selectById(id);
        if(C2cEnum.status.NORMAL.getCode().equals(config.getStatus())){
            config.setStatus(C2cEnum.status.DISABLE.getCode());
        }else {
            config.setStatus(C2cEnum.status.NORMAL.getCode());
        }
        boolean bo =  tradingOnService.updateById(config);
        if(!bo){
            return WrapMapper.error( "修改失败" );
        }
        return WrapMapper.ok( "修改成功" );
    }

    /**
     * 交易对 编辑初始化
     * @param id
     * @param mmap
     * @return
     */
    @RequiresPermissions("config:parameterConfig:editInfo")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        TradingOn tradingOn = tradingOnService.selectById(id);
        mmap.put( "tradingOn", tradingOn);
        return prefix + "/edit";
    }

    /**
     * 保存交易对
     */
    @RequiresPermissions("config:parameterConfig:updateTradingOns")
    @PostMapping(value = "/updateTradingOns")
    @ResponseBody
    public Wrapper<String> updateTradingOns(TradingOn tradingOn) {
        return tradingOnService.updateTradingOns(tradingOn);
    }
}

