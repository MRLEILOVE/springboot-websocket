package com.jdcloud.provider.web.sys;


import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum;
import com.jdcloud.provider.model.pojo.SysJobLog;
import com.jdcloud.provider.model.service.QuartzFeignApi;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.annotation.Log;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 定时任务调度日志表 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2019-06-11
 */
@Slf4j
@Controller
@RequestMapping("/sysJobLog")
public class SysJobLogController extends BaseController {
    private String prefix = "system/job";

    @Resource
    private QuartzFeignApi quartzFeignApi;

    /**
     * @Description: 日志页面
     * @Author: senghor
     * @Date: 2019/6/12 16:59
     */
    @RequiresPermissions("sysJobLog:job:view")
    @GetMapping()
    public String jobLog() {
        return prefix + "/logList";
    }

    /**
     * @Description: 日志分页数据
     * @Author: senghor
     * @Date: 2019/6/12 16:59
     */
    @RequiresPermissions("sysJobLog:job:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysJobLog jobLog) {
        Wrapper wrapper = quartzFeignApi.selectJobLogList(getPage(), jobLog);
        if (wrapper == null || wrapper.getCode() != 200) {
            log.error("获取列表失败");
            return new TableDataInfo();
        }
        Page<SysJobLog> page = (Page<SysJobLog>) wrapper.getResult();
        return new TableDataInfo(page.getRecords(), page.getTotal());
    }

    /**
     * @Description: 删除单个日志
     * @Author: senghor
     * @Date: 2019/6/12 16:59
     */
    @Log(title = "调度日志", buinessType = AnnotationEnum.BusinessType.DELETE)
    @RequiresPermissions("sysJobLog:job:remove")
    @PostMapping("/remove")
    @ResponseBody
    public Wrapper<String> remove(String ids) {
        Wrapper wrapper = quartzFeignApi.deleteJobLogByIds(ids);
        if (wrapper == null || wrapper.getCode() != 200) {
            log.error("删除日志失败");
            return error();
        }
        return success();
    }

    /**
     * @Description: 详情页面
     * @Author: senghor
     * @Date: 2019/6/12 16:59
     */
    @RequiresPermissions("sysJobLog:job:detail")
    @GetMapping("/detail/{jobLogId}")
    public String detail(@PathVariable("jobLogId") Long jobLogId, ModelMap mmap) {
        mmap.put("name", "jobLog");
        Wrapper wrapper = quartzFeignApi.selectJobLogById(jobLogId);
        if (wrapper == null || wrapper.getCode() != 200) {
            log.error("删除日志失败");
        } else {
            mmap.put("jobLog", wrapper.getResult());
        }
        return prefix + "/detail";
    }

    /**
     * @Description: 清空所有日志
     * @Author: senghor
     * @Date: 2019/6/12 16:59
     */
    @Log(title = "调度日志", buinessType = AnnotationEnum.BusinessType.CLEAN)
    @RequiresPermissions("sysJobLog:job:remove")
    @PostMapping("/clean")
    @ResponseBody
    public Wrapper<String> clean() {
        quartzFeignApi.cleanJobLog();
        return success();
    }

}

