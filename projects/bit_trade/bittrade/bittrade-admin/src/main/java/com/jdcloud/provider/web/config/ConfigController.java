package com.jdcloud.provider.web.config;

import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.dto.Config;
import com.jdcloud.provider.pojo.ParameterConfig;
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
 * 参数设置
 * <p>
 *
 * @author yongheng
 * @since 2018/12/1
 */
@Controller
@RequestMapping(value = "/config/parameterConfig")
public class ConfigController extends BaseController {

	private String					prefix	= "config";

	@Autowired
	private ParameterConfigService	parameterConfigService;

	@RequiresPermissions("config:parameterConfig:view")
	@GetMapping()
	public String parameterConfig() {
		return prefix + "/parameterConfig";
	}

	/**
	 * 参数设置列表<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/28 11:01
	 */
	@RequiresPermissions("config:parameterConfig:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Config config) {
		this.paramsHandle( config );
		Page<ParameterConfig> page = parameterConfigService.selectConfigList( config );
		return new TableDataInfo( page.getRecords(), page.getTotal() );
	}

	@RequiresPermissions("config:parameterConfig:add")
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
	@RequiresPermissions("config:parameterConfig:saveOrUpdate")
	@PostMapping(value = "/saveOrUpdate", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public Wrapper<String> saveOrUpdate(ParameterConfig parameterConfig) {
		return parameterConfigService.saveOrUpdate( parameterConfig, this.getUser() );
	}

	@RequiresPermissions("config:parameterConfig:edit")
	@GetMapping("/edit/{dictionaryId}")
	public String edit(@PathVariable("dictionaryId") String dictionaryId, ModelMap mmap) {
		ParameterConfig parameterConfig = parameterConfigService.selectById( dictionaryId );
		mmap.put( "parameterConfig", parameterConfig );
		return prefix + "/edit";
	}

	/**
	 * 删除操作<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/1 18:07
	 */
	@RequiresPermissions("config:parameterConfig:del")
	@PostMapping("/remove")
	@ResponseBody
	public Wrapper<String> remove(String ids) {
		return parameterConfigService.deleteByIds(ids);
	}
}