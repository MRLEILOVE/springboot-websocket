package com.bittrade.admin.controller.wallet;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bittrade.admin.annotation.Log;
import com.bittrade.admin.controller.base.BaseController;
import com.bittrade.admin.enums.AnnotationEnum.BusinessType;
import com.bittrade.admin.service.wallet.IWCoinConfigService;
import com.bittrade.admin.wrapper.TableDataInfo;
import com.bittrade.admin.wrapper.Wrapper;
import com.bittrade.pojo.model.WCoinConfig;

import cn.hutool.db.Page;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = { "/wallet" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WCoinConfigController extends BaseController {

	@Autowired
	private IWCoinConfigService	coinConfigService;

	// @Autowired
	// private IWCoinService configService;

	private String				prefix	= "wallet";

	@GetMapping("/view")
	@RequiresPermissions("wallet:view")
	public String view() {
		return prefix + "/cold/cold";
	}

	@RequiresPermissions("wallet:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list() {
		List<WCoinConfig> coinConfigs = coinConfigService.list();
		Page<WCoinConfig> page = new Page<WCoinConfig>();
		page.setRecords( coinConfigs );
		page.setTotal( page.getSize() );
		return new TableDataInfo( page.getRecords(), page.getTotal() );
	}

	@GetMapping("/add")
	public String add(ModelMap modelMap) {
		modelMap.put( "tokens", coinConfigService.list() );
		return prefix + "/cold/add";
	}

	@Log(title = "添加冷钱包地址", buinessType = BusinessType.INSERT)
	@RequiresPermissions("system:user:add")
	@PostMapping("/add")
	@Transactional(rollbackFor = Exception.class)
	@ResponseBody
	public Wrapper<String> addSave(WCoinConfig model) {
		QueryWrapper<WCoinConfig> coinConfigQueryWrapper = new QueryWrapper<WCoinConfig>();
		coinConfigQueryWrapper.eq( WCoinConfig.FieldNames.TOKEN, model.getToken() );
		int row = coinConfigService.count( coinConfigQueryWrapper );
		if (row > 0) {
			return error( "请勿重复添加" );
		}
		model.setContract( "" );
		model.setCreateTime( LocalDateTime.now() );
		model.setUpdateTime( LocalDateTime.now() );
		return toAjax( coinConfigService.add( model ) );
	}

	@Log(title = "删除冷钱包地址", buinessType = BusinessType.DELETE)
	@RequiresPermissions("system:user:remove")
	@PostMapping("/remove/{id}")
	@ResponseBody
	public Wrapper<String> remove(@PathVariable("id") Integer id) {
		if (coinConfigService.removeById( id )) {
			return toAjax( 1 );
		} else {
			return error( "删除失败" );
		}
	}

}
