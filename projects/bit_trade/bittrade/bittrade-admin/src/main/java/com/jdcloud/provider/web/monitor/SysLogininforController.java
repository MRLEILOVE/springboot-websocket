package com.jdcloud.provider.web.monitor;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum.BusinessType;
import com.jdcloud.provider.pojo.SysLogininfor;
import com.jdcloud.provider.service.SysLogininforService;
import com.jdcloud.provider.utils.ExcelUtil;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.annotation.Log;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 系统访问记录
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/logininfor")
public class SysLogininforController extends BaseController {

	private String					prefix	= "monitor/logininfor";

	@Autowired
	private SysLogininforService	logininforService;

	@RequiresPermissions("monitor:logininfor:view")
	@GetMapping()
	public String logininfor() {
		return prefix + "/logininfor";
	}

	@RequiresPermissions("monitor:logininfor:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SysLogininfor logininfor) {
		Page<SysLogininfor> users = logininforService.selectLogininforList( getPage(), logininfor );
		return new TableDataInfo( users.getRecords(), users.getTotal() );
	}

	@Log(title = "登录日志", buinessType = BusinessType.EXPORT)
	@RequiresPermissions("monitor:logininfor:export")
	@PostMapping("/export")
	@ResponseBody
	public Wrapper<String> export(SysLogininfor logininfor) {
		List<SysLogininfor> list = logininforService.selectList( new EntityWrapper<SysLogininfor>( logininfor ) );
		ExcelUtil<SysLogininfor> util = new ExcelUtil<SysLogininfor>( SysLogininfor.class );
		return util.exportExcel( list, "logininfor" );
	}

	@RequiresPermissions("monitor:logininfor:remove")
	@Log(title = "登录日志", buinessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public Wrapper<String> remove(String ids) {
		return toAjax( logininforService.deleteLogininforByIds( ids ) );
	}

	@RequiresPermissions("monitor:logininfor:remove")
	@Log(title = "登录日志", buinessType = BusinessType.CLEAN)
	@PostMapping("/clean")
	@ResponseBody
	public Wrapper<String> clean() {
		logininforService.cleanLogininfor();
		return success();
	}
}
