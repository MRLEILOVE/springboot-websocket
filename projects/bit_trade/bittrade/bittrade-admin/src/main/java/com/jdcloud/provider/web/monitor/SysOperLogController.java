package com.jdcloud.provider.web.monitor;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum.BusinessType;
import com.jdcloud.provider.pojo.SysOperLog;
import com.jdcloud.provider.service.SysOperLogService;
import com.jdcloud.provider.utils.ExcelUtil;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.annotation.Log;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 操作日志记录
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/operlog")
public class SysOperLogController extends BaseController {

	private String				prefix	= "monitor/operlog";

	@Autowired
	private SysOperLogService	operLogService;

	@RequiresPermissions("monitor:operlog:view")
	@GetMapping()
	public String operlog() {
		return prefix + "/operlog";
	}

	@RequiresPermissions("monitor:operlog:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SysOperLog operLog) {
		Page<SysOperLog> users = operLogService.selectOperLogList( getPage(), operLog );
		return new TableDataInfo( users.getRecords(), users.getTotal() );
	}

	@Log(title = "操作日志", buinessType = BusinessType.EXPORT)
	@RequiresPermissions("monitor:operlog:export")
	@PostMapping("/export")
	@ResponseBody
	public Wrapper<String> export(SysOperLog operLog) {
		List<SysOperLog> list = operLogService.selectList( new EntityWrapper<SysOperLog>( operLog ) );
		ExcelUtil<SysOperLog> util = new ExcelUtil<SysOperLog>( SysOperLog.class );
		return util.exportExcel( list, "operLog" );
	}

	@RequiresPermissions("monitor:operlog:remove")
	@PostMapping("/remove")
	@ResponseBody
	public Wrapper<String> remove(String ids) {
		return toAjax( operLogService.deleteOperLogByIds( ids ) );
	}

	@RequiresPermissions("monitor:operlog:detail")
	@GetMapping("/detail/{operId}")
	public String detail(@PathVariable("operId") Integer operId, ModelMap mmap) {
		mmap.put( "operLog", operLogService.selectOperLogById( operId ) );
		return prefix + "/detail";
	}

	@Log(title = "操作日志", buinessType = BusinessType.CLEAN)
	@RequiresPermissions("monitor:operlog:remove")
	@PostMapping("/clean")
	@ResponseBody
	public Wrapper<String> clean() {
		operLogService.cleanOperLog();
		return success();
	}
}