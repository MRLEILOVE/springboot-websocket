package com.bittrade.admin.controller.sys;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.admin.controller.base.BaseController;
import com.bittrade.admin.model.domain.SysDictData;
import com.bittrade.admin.service.sys.SysDictDataService;
import com.bittrade.admin.util.ExcelUtil;
import com.bittrade.admin.util.ShiroUtil;
import com.bittrade.admin.wrapper.TableDataInfo;
import com.bittrade.admin.wrapper.Wrapper;

/**
 * 数据字典信息
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/dict/data")
public class SysDictDataController extends BaseController {

	private String				prefix	= "system/dict/data";

	@Autowired
	private SysDictDataService	dictDataService;

	@RequiresPermissions("system:dict:view")
	@GetMapping()
	public String dictData() {
		return prefix + "/data";
	}

	@PostMapping("/list")
	@RequiresPermissions("system:dict:list")
	@ResponseBody
	public TableDataInfo list(SysDictData dictData) {
		Page<SysDictData> list = dictDataService.selectDictDataList( dictData, getPage() );
		return new TableDataInfo( list.getRecords(), list.getTotal() );
	}

	 @RequiresPermissions("system:dict:export")
	 @PostMapping("/export")
	 @ResponseBody
	 public Wrapper<String> export(SysDictData dictData) {
	 List<SysDictData> list = dictDataService.list( new QueryWrapper<SysDictData>( dictData ) );
	 ExcelUtil<SysDictData> util = new ExcelUtil<SysDictData>(SysDictData.class );
	 return util.exportExcel( list, "dictData" );
	 }

	/**
	 * 新增字段类型
	 * 
	 * @param dictType
	 * @param mmap
	 * @return
	 */
	@GetMapping("/add/{dictType}")
	public String add(@PathVariable("dictType") String dictType, ModelMap mmap) {
		mmap.put( "dictType", dictType );
		return prefix + "/add";
	}

	/**
	 * 新增保存字典类型
	 * 
	 * @param dict
	 * @return
	 */
	@RequiresPermissions("system:dict:add")
	@PostMapping("/add")
	@ResponseBody
	public Wrapper<String> addSave(SysDictData dict) {
		dict.setCreateBy( ShiroUtil.getLoginName() );
		return toAjax( dictDataService.insertDictData( dict ) );
	}

	/**
	 * 修改字典类型
	 * 
	 * @param dictCode
	 * @param mmap
	 * @return
	 */
	@GetMapping("/edit/{dictCode}")
	public String edit(@PathVariable("dictCode") Integer dictCode, ModelMap mmap) {
		mmap.put( "dict", dictDataService.selectDictDataById( dictCode ) );
		return prefix + "/edit";
	}

	/**
	 * 修改保存字典类型
	 * 
	 * @param dict
	 * @return
	 */
	@RequiresPermissions("system:dict:edit")
	@PostMapping("/edit")
	@ResponseBody
	public Wrapper<String> editSave(SysDictData dict) {
		dict.setUpdateBy( ShiroUtil.getLoginName() );
		return toAjax( dictDataService.updateDictData( dict ) );
	}

	@RequiresPermissions("system:dict:remove")
	@PostMapping("/remove")
	@ResponseBody
	public Wrapper<String> remove(String ids) {
		return toAjax( dictDataService.deleteDictDataByIds( ids ) );
	}
}
