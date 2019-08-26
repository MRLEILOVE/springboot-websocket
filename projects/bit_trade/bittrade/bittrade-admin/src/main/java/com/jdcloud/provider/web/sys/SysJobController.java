package com.jdcloud.provider.web.sys;


import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.provider.model.pojo.SysJob;
import com.jdcloud.provider.model.pojo.SysJobLog;
import com.jdcloud.provider.model.service.QuartzFeignApi;
import com.jdcloud.provider.utils.CronUtils;
import com.jdcloud.provider.utils.ShiroUtils;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.annotation.Log;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 定时任务调度表 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2019-06-11
 */
@Slf4j
@Controller
@RequestMapping("/sysJob")
public class SysJobController extends BaseController {
    private String prefix = "system/job";

    @Resource
    private QuartzFeignApi quartzFeignApi;

    /**
     * @Description: 任务页面
     * @Author: senghor
     * @Date: 2019/6/12 16:59
     */
    @RequiresPermissions("sysJob:job:view")
    @GetMapping()
    public String job() {
        return prefix + "/list";
    }

    /**
     * @Description: 任务数据
     * @Author: senghor
     * @Date: 2019/6/12 16:59
     */
    @RequiresPermissions("sysJob:job:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysJob job) {
        Wrapper wrapper = quartzFeignApi.list(getPage(), job);
        if (wrapper == null || wrapper.getCode() != 200) {
            log.error("获取列表失败");
            return new TableDataInfo();
        }
        Page<SysJob> page = (Page<SysJob>) wrapper.getResult();
        return new TableDataInfo(page.getRecords(), page.getTotal());
    }

    /**
     * @Description: 删除任务
     * @Author: senghor
     * @Date: 2019/6/12 16:59
     */
    @Log(title = "定时任务", buinessType = AnnotationEnum.BusinessType.DELETE)
    @RequiresPermissions("sysJob:job:remove")
    @PostMapping("/remove")
    @ResponseBody
    public Wrapper<String> remove(String ids) {
        Wrapper wrapper = quartzFeignApi.remove(ids);
        if (wrapper == null || wrapper.getCode() != 200) {
            log.error("删除任务失败");
            return error();
        }
        return success();
    }

    /**
     * @Description: 任务调度详情
     * @Author: senghor
     * @Date: 2019/6/12 16:59
     */
    @RequiresPermissions("sysJob:job:detail")
    @GetMapping("/detail/{jobId}")
    public String detail(@PathVariable("jobId") Long jobId, ModelMap mmap) {
        mmap.put("name", "job");
        Wrapper wrapper = quartzFeignApi.detail(jobId);
        if (wrapper == null || wrapper.getCode() != 200) {
            log.error("获取任务详情失败");
        } else {
            SysJob sysJob = (SysJob) wrapper.getResult();
            sysJob.setNextValidTime(CronUtil.getNextExecution(sysJob.getCronExpression()));
            mmap.put("job", sysJob);
        }
        return prefix + "/detail";
    }

    /**
     * @Description: 任务调度状态修改
     * @Author: senghor
     * @Date: 2019/6/12 16:59
     */
    @Log(title = "定时任务", buinessType = AnnotationEnum.BusinessType.UPDATE)
    @RequiresPermissions("sysJob:job:changeStatus")
    @PostMapping("/changeStatus")
    @ResponseBody
    public Wrapper<String> changeStatus(SysJob job) throws SchedulerException {
        Wrapper wrapper = quartzFeignApi.detail(job.getJobId());
        if (wrapper == null || wrapper.getCode() != 200) {
            log.error("获取任务详情失败");
            return error("获取任务详情失败");
        }

        SysJob newJob = (SysJob) wrapper.getResult();
        newJob.setStatus(job.getStatus());
        Wrapper wrapperRow = quartzFeignApi.changeStatus(newJob);
        if (wrapperRow == null || wrapperRow.getCode() != 200) {
            log.error("任务调度状态修改失败");
            return error("任务调度状态修改失败");
        }
        return toAjax((Integer) wrapperRow.getResult());
    }

    /**
     * @Description: 任务调度立即执行一次
     * @Author: senghor
     * @Date: 2019/6/12 16:59
     */
    @Log(title = "定时任务", buinessType = AnnotationEnum.BusinessType.UPDATE)
    @RequiresPermissions("sysJob:job:changeStatus")
    @PostMapping("/run")
    @ResponseBody
    public Wrapper<String> run(SysJob job) {
        Wrapper wrapperRow = quartzFeignApi.run(job);
        if (wrapperRow == null || wrapperRow.getCode() != 200) {
            log.error("任务调度立即执行失败");
            return error("任务调度立即执行失败");
        }
        return success();
    }

    /**
     * @Description: 新增调度
     * @Author: senghor
     * @Date: 2019/6/12 16:59
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * @Description: 新增保存调度
     * @Author: senghor
     * @Date: 2019/6/12 16:59
     */
    @Log(title = "定时任务", buinessType = AnnotationEnum.BusinessType.INSERT)
    @RequiresPermissions("sysJob:job:add")
    @PostMapping("/add")
    @ResponseBody
    public Wrapper<String> addSave(SysJob job) {
        job.setCreateBy(ShiroUtils.getLoginName());
        Wrapper wrapperRow = quartzFeignApi.addSave(job);
        if (wrapperRow == null || wrapperRow.getCode() != 200) {
            log.error("新增保存调度失败");
            return error("新增保存调度失败");
        }
        return toAjax((Integer) wrapperRow.getResult());
    }

    /**
     * @Description: 修改调度
     * @Author: senghor
     * @Date: 2019/6/12 16:59
     */
    @GetMapping("/edit/{jobId}")
    public String edit(@PathVariable("jobId") Long jobId, ModelMap mmap) {
        Wrapper wrapper = quartzFeignApi.detail(jobId);
        if (wrapper == null || wrapper.getCode() != 200) {
            log.error("获取任务详情失败");
        } else {
            mmap.put("job", wrapper.getResult());
        }
        return prefix + "/edit";
    }

    /**
     * @Description: 修改保存调度
     * @Author: senghor
     * @Date: 2019/6/12 16:59
     */
    @Log(title = "定时任务", buinessType = AnnotationEnum.BusinessType.UPDATE)
    @RequiresPermissions("sysJob:job:edit")
    @PostMapping("/edit")
    @ResponseBody
    public Wrapper<String> editSave(SysJob job) {
        job.setUpdateBy(ShiroUtils.getLoginName());
        Wrapper wrapper = quartzFeignApi.editSave(job);
        if (wrapper == null || wrapper.getCode() != 200) {
            log.error("获取任务详情失败");
            return error("获取任务详情失败");
        }
        return toAjax((Integer) wrapper.getResult());
    }

    /**
     * @Description: 校验cron表达式是否有效
     * @Author: senghor
     * @Date: 2019/6/12 16:59
     */
    @PostMapping("/checkCronExpressionIsValid")
    @ResponseBody
    public Boolean checkCronExpressionIsValid(SysJob job) {
        Wrapper wrapper = quartzFeignApi.checkCronExpressionIsValid(job.getCronExpression());
        if (wrapper == null || wrapper.getCode() != 200) {
            log.error("获取任务详情失败");
            return false;
        }
        return (Boolean) wrapper.getResult();
    }
}

