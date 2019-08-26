package com.jdcloud.provider.web.betaHouse;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.HouseBackpackUseDto;
import com.jdcloud.provider.dto.HouseDto;
import com.jdcloud.provider.dto.HouseHistoryDto;
import com.jdcloud.provider.dto.HouseOrderDto;
import com.jdcloud.provider.pojo.HouseAccount;
import com.jdcloud.provider.pojo.HouseBackpack;
import com.jdcloud.provider.pojo.HouseBackpackUse;
import com.jdcloud.provider.pojo.HouseHistory;
import com.jdcloud.provider.pojo.vo.HouseBackpackUseVo;
import com.jdcloud.provider.pojo.vo.HouseBackpackVo;
import com.jdcloud.provider.pojo.vo.HouseOrderVo;
import com.jdcloud.provider.pojo.vo.HouseVo;
import com.jdcloud.provider.service.*;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import lombok.Value;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 狗窝表 前端控制器
 * </p>
 *
 * @author c
 * @since 2019-06-13
 */
@Controller
@RequestMapping("/betaHouse/house")
public class HouseController extends BaseController {

    @Autowired
    private HouseService  houseService;
    @Autowired
    private HouseHistoryService houseHistoryService;
    @Autowired
    private HouseAccountService houseAccountService;
    @Autowired
    private HouseBackpackUseService houseBackpackUseService;
    @Autowired
    private HouseBackpackService houseBackpackService;


    private String prefix = "betaHouse/house";

    /**
     * 狗窝初始化
     * @returnhouseInfo
     */
    @RequiresPermissions("house:view")
    @GetMapping()
    public String houseInfo() {
        return prefix + "/list";
    }

    /**
     * 狗窝列表
     * @param houseDto
     * @return
     */
    @RequiresPermissions("house:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo getHouse(HouseDto houseDto) {
        Page<HouseVo> vo = houseService.getHouse(getPage(), houseDto);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }

    /**
     * 狗窝_母狗列表
     */
    @RequiresPermissions("house:feminaList")
    @GetMapping("/feminaList/{id}")
    public String feminaList(@PathVariable("id") Integer id, ModelMap mmap){
        HouseVo vo = houseService.getDetail(id);
        mmap.put("vo", vo);
        return prefix + "/feminaList";
    }


    /**
     * 狗窝_公狗列表
     */
//    @RequiresPermissions("house:maleList")
    @GetMapping("/maleList/{id}")
    public String maleList(@PathVariable("id") Integer id, ModelMap mmap){
        HouseVo vo = houseService.getDetail(id);
        mmap.put("vo", vo);
        return prefix + "/maleList";
    }

    /**
     * 狗窝_小狗列表
     */
//    @RequiresPermissions("house:maleList")
    @GetMapping("/puppyList/{id}")
    public String puppyList(@PathVariable("id") Integer id, ModelMap mmap){
        HouseVo vo = houseService.getDetail(id);
        mmap.put("vo", vo);
        return prefix + "/puppyList";
    }

    /**
     * 狗窝_动态列表
     */
//    @RequiresPermissions("house:historyListInfo")
    @GetMapping("/historyListInfo/{id}")
    public String historyList(@PathVariable("id") Integer id, ModelMap mmap){
        HouseVo vo = houseService.getDetail(id);
        mmap.put("vo", vo);
        return prefix + "/historyList";
    }

    /**
     * 狗窝_动态列表
     * @param
     * @return
     */
//    @RequiresPermissions("house:getHistory")
    @PostMapping("/getHistoryList")
    @ResponseBody
    public TableDataInfo getHistoryList(HouseHistoryDto houseHistoryDto) {
        Page<HouseHistory> vo = houseHistoryService.getHistoryList(getPage(),houseHistoryDto);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }

    /**
     * 狗窝_挖矿列表初始化
     */
//    @RequiresPermissions("house:accountListInfo")
    @GetMapping("/accountListInfo/{id}")
    public String accountListInfo(@PathVariable("id") Integer id, ModelMap mmap){
        HouseVo vo = houseService.getDetail(id);
        mmap.put("vo", vo);
        return prefix + "/accountList";
    }

    /**
     * 狗窝_挖矿列表
     * @param houseAccount
     * @return
     */
//    @RequiresPermissions("house:getAccountList")
    @PostMapping("/getAccountList")
    @ResponseBody
    public TableDataInfo getAccountList(HouseAccount houseAccount) {
        EntityWrapper entity = new EntityWrapper();
        entity.eq("house_id",houseAccount.getId());
        if(houseAccount.getType()!=null){
            entity.eq("type",houseAccount.getType());
        }
        Page<HouseAccount> vo = houseAccountService.selectPage(getPage(),entity);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }

    /**
     * 背包使用记录（装备初始化）
     * cc
     * 2019-06-21
     */
//    @RequiresPermissions("house:backpackUseInfo")
    @GetMapping("/backpackUseInfo/{id}")
    public String backpackUseInfo(@PathVariable("id") Integer id, ModelMap mmap){
        HouseVo vo = houseService.getDetail(id);
        mmap.put("vo", vo);
        return prefix + "/backpackUseList";
    }

    /**
     * 狗窝_（背包使用记录）装备
     * cc
     * 2019-06-21
     */
//    @RequiresPermissions("house:getBackpackUseList")
    @PostMapping("/getBackpackUseList")
    @ResponseBody
    public TableDataInfo getBackpackUseList(HouseBackpackUseDto houseBackpackUseDto) {
        Page<HouseBackpackUseVo> vo = houseBackpackUseService.getBackpackUseList(getPage(),houseBackpackUseDto);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }

    /**
     * 狗窝_背包（背包初始化）
     * c
     * 2019-06-21
     */
//    @RequiresPermissions("house:backpackInfo")
    @GetMapping("/backpackInfo/{userId}")
    public String backpackInfo(@PathVariable("userId") Long userId, ModelMap mmap){
        mmap.put("userId", userId);
        return prefix + "/backpackList";
    }

    /**
     * 狗窝_（背包记录）
     * c
     * 2019-06-21
     */
    @RequiresPermissions("house:getBackpackList")
    @PostMapping("/getBackpackList")
    @ResponseBody
    public TableDataInfo getBackpackList(HouseBackpack houseBackpack) {
        EntityWrapper entity = new EntityWrapper();
        entity.eq("user_id",houseBackpack.getUserId());
        Page<HouseBackpack> vo = houseBackpackService.selectPage(getPage(),entity);
        return new TableDataInfo(vo.getRecords(), vo.getTotal());
    }

}

