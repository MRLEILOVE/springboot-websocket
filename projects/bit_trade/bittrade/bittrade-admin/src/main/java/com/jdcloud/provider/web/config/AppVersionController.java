package com.jdcloud.provider.web.config;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.AppVersionDto;
import com.jdcloud.provider.pojo.AppVersion;
import com.jdcloud.provider.service.AppVersionService;
import com.jdcloud.provider.service.ParameterConfigService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 版本管理
 * <p>
 *
 * @author yongheng
 * @since 2018/12/1
 */
@Controller
@RequestMapping(value = "/config/appVersion")
public class AppVersionController extends BaseController {

	private String					prefix	= "config/appVersion";

	@Autowired
	private AppVersionService		appVersionService;

	@Autowired
	private ParameterConfigService	parameterConfigService;

	@RequiresPermissions("config:appVersion:view")
	@GetMapping()
	public String parameterConfig() {
		return prefix + "/appVersion";
	}

	/**
	 * 版本管理列表<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/28 11:01
	 */
	@RequiresPermissions("config:appVersion:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AppVersionDto appVersionDto) {
		Page<AppVersion> page = appVersionService.selectAppVersionList( getPage(), appVersionDto );
		return new TableDataInfo( page.getRecords(), page.getTotal() );
	}

	@RequiresPermissions("config:appVersion:add")
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 保存或更新<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/24 15:34
	 */
	@RequiresPermissions("config:appVersion:saveOrUpdate")
	@PostMapping(value = "/saveOrUpdate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Wrapper<String> saveOrUpdate(AppVersionDto appVersionDto) {
		return appVersionService.saveOrUpdate( appVersionDto, this.getUser() );
	}

	@RequiresPermissions("config:appVersion:edit")
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
		AppVersion appVersion = appVersionService.selectById( id );
		mmap.put( "appVersion", appVersion );
		return prefix + "/edit";
	}

	/**
	 * 删除操作<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/1 18:07
	 */
	@RequiresPermissions("config:appVersion:del")
	@PostMapping("/remove")
	@ResponseBody
	public Wrapper<String> remove(String ids) {
		return parameterConfigService.deleteByIds( ids );
	}
}