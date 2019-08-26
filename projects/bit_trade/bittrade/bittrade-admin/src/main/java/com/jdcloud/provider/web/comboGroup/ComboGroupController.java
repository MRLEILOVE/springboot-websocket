package com.jdcloud.provider.web.comboGroup;


import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.constant.GlobalConstant;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.core.redis.RedisKeyUtil;
import com.jdcloud.core.redis.RedisTemplate;
import com.jdcloud.provider.dto.ComboGroupDto;
import com.jdcloud.provider.pojo.ComboGroup;
import com.jdcloud.provider.pojo.vo.ComboGroupVo;
import com.jdcloud.provider.service.ComboGroupService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.annotation.Log;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  套餐 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2019-04-07
 */
@Controller
@RequestMapping("/beta/comboGroup")
public class ComboGroupController extends BaseController {


    @Autowired
    private ComboGroupService comboGroupService;

    @Autowired
    private RedisTemplate redisTemplate;

    private String prefix = "beta/comboGroup";

    @RequiresPermissions("comboGroup:view")
    @GetMapping()
    public String list() {
        return prefix + "/list";
    }

    @GetMapping("/add")
    @RequiresPermissions("comboGroup:add")
    public String add(ModelMap mmap) {
        return prefix + "/add";
    }

    /**
     * 列表
     */
    @RequiresPermissions("comboGroup:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo comboGroupList() {
        Page<ComboGroup> orderUserListPage = comboGroupService.selectComboGroupListPage(getPage());
        return new TableDataInfo(orderUserListPage.getRecords(), orderUserListPage.getTotal());
    }

    /**
     * 删除
     */
    @Log(title = "删除套餐", buinessType = AnnotationEnum.BusinessType.DELETE)
    @RequiresPermissions("comboGroup:delete")
    @PostMapping("/remove")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public Wrapper<String> comboGroupdelete(String ids) {
        return toAjax( comboGroupService.deleteComboGroupdeleteByIds( ids ) );
    }

    /**
     * 查询套餐详情
     * @param id
     * @param mmap
     *      * @return
     */
    @RequiresPermissions("comboGroup:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        ComboGroup comboGroup = comboGroupService.selectById(id);
        ComboGroupVo dto  = new ComboGroupVo();
        BeanUtils.copyProperties(comboGroup, dto );
        dto.setDifferential(comboGroup.getDifferential());

        StringBuilder startTime = new StringBuilder(comboGroup.getStartTime().toString());
        startTime.insert(2, ":");
        dto.setStartTime(startTime.toString());

        StringBuilder endTime = new StringBuilder(comboGroup.getEndTime().toString());
        endTime.insert(2, ":");
        dto.setEndTime(endTime.toString());
        mmap.put( "dto", dto );
        return prefix + "/edit";
    }


    /**
     * 保存或更新<br>
     *
     */
    @Log(title = "更新套餐", buinessType = AnnotationEnum.BusinessType.UPDATE)
    @RequiresPermissions("comboGroup:saveOrUpdate")
    @PostMapping("/saveOrUpdate")
    @ResponseBody
    public Wrapper<String> saveOrUpdate(ComboGroupDto comboGroupDto) {
        return comboGroupService.saveOrUpdate(comboGroupDto);
    }

    /**
     * @Description: 刷新缓存中的套餐数据
     * @Author: senghor
     * @Date: 2019/6/8 13:30
     */
    @RequiresPermissions("comboGroup:refreshRedis")
    @PostMapping("/refreshRedis")
    @ResponseBody
    public Wrapper<String> refreshRedis() {
        return comboGroupService.refreshRedis();
    }


    /**
     * 抢狗概率初始化
     * @param id
     * @param mmap
     */
    @RequiresPermissions("comboGroupEdit:info")
    @GetMapping("/comboGroupEdit/{id}")
    public String comboGroupEdit(@PathVariable("id") Integer id, ModelMap mmap) {
        ComboGroup comboGroup = comboGroupService.selectById(id);
        mmap.put( "dto",comboGroup);
        return prefix + "/comboGroupEdit";
    }

    /**
     * 新的修改概率
     * c
     * 2019-7-11
     */
    @Log(title = "更新套餐", buinessType = AnnotationEnum.BusinessType.UPDATE)
    @RequiresPermissions("comboGroup:saveOrUpdate")
    @PostMapping("/updateComboGroup")
    @ResponseBody
    public Wrapper<String> updateComboGroup(ComboGroup comboGroup) {
        Boolean bo=  comboGroupService.updateById(comboGroup);
        if(!bo){
            return WrapMapper.error();
        }else{
            return WrapMapper.ok();
        }
    }

    /**
     * 套餐停用
     */
    @Log(title = "套餐停止", buinessType = AnnotationEnum.BusinessType.UPDATE)
    @RequiresPermissions("updateComboGroupStop:update")
    @PostMapping("/updateComboGroupStop")
    @ResponseBody
    public Wrapper<String> updateComboGroupStop(ComboGroup comboGroup){
        boolean bo =  redisTemplate.set(RedisKeyUtil.MALE_REMAIN + comboGroup.getId(),comboGroup.getType());
        if (!bo) {
            return WrapMapper.error("修改失败！");
        }
        return WrapMapper.ok("修改成功！");
    }


//    /**
//     * 套餐启用
//     */
//    @Log(title = "套餐启用", buinessType = AnnotationEnum.BusinessType.UPDATE)
//    @RequiresPermissions("updateComboGroupStart:update")
//    @PostMapping("/updateComboGroupStart/{id}/")
//    @ResponseBody
//    public Wrapper<String> updateComboGroupEnable(@PathVariable("id") Integer id){
//        boolean bo =  redisTemplate.set(RedisKeyUtil.MALE_REMAIN + id, 1);
//        if (!bo) {
//            return WrapMapper.error("修改失败！");
//        }
//        return  WrapMapper.ok("设置成功");
//    }



}

