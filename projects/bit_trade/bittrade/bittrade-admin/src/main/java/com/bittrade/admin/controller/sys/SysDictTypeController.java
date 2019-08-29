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
import com.bittrade.admin.service.sys.SysDictTypeService;
import com.bittrade.admin.util.ExcelUtil;
import com.bittrade.admin.util.ShiroUtil;
import com.bittrade.admin.wrapper.TableDataInfo;
import com.bittrade.admin.wrapper.Wrapper;
import com.bittrade.pojo.model.SysDictType;

/**
 * 数据字典信息
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/dict")
public class SysDictTypeController extends BaseController {
	
	private String				prefix	= "system/dict/type";

	@Autowired
	private SysDictTypeService	dictTypeService;

	@RequiresPermissions("system:dict:view")
	@GetMapping()
	public String dictType() {
		return prefix + "/type";
	}

	@PostMapping("/list")
	@RequiresPermissions("system:dict:list")
	@ResponseBody
	public TableDataInfo list(SysDictType dictType) {
		Page<SysDictType> list = dictTypeService.selectDictTypeList( dictType, getPage() );
		return new TableDataInfo( list.getRecords(), list.getTotal() );
	}

	@RequiresPermissions("system:dict:export")
	@PostMapping("/export")
	@ResponseBody
	public Wrapper<String> export(SysDictType dictType) {
		List<SysDictType> list = dictTypeService.list( new QueryWrapper<SysDictType>( dictType ) );
		ExcelUtil<SysDictType> util = new ExcelUtil<SysDictType>( SysDictType.class );
		return util.exportExcel( list, "dictType" );
	}

	/**
	 * 新增字典类型
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存字典类型
	 */
	@RequiresPermissions("system:dict:add")
	@PostMapping("/add")
	@ResponseBody
	public Wrapper<String> addSave(SysDictType dict) {
		dict.setCreateBy( ShiroUtil.getLoginName() );
		return toAjax( dictTypeService.insertDictType( dict ) );
	}

	/**
	 * 修改字典类型
	 */
	@GetMapping("/edit/{dictId}")
	public String edit(@PathVariable("dictId") Integer dictId, ModelMap mmap) {
		mmap.put( "dict", dictTypeService.selectDictTypeById( dictId ) );
		return prefix + "/edit";
	}

	/**
	 * 修改保存字典类型
	 */
	@RequiresPermissions("system:dict:edit")
	@PostMapping("/edit")
	@ResponseBody
	public Wrapper<String> editSave(SysDictType dict) {
		dict.setUpdateBy( ShiroUtil.getLoginName() );
		return toAjax( dictTypeService.updateDictType( dict ) );
	}

	@RequiresPermissions("system:dict:remove")
	@PostMapping("/remove")
	@ResponseBody
	public Wrapper<String> remove(String ids) {
		try {
			return toAjax( dictTypeService.deleteDictTypeByIds( ids ) );
		} catch (Exception e) {
			return error( e.getMessage() );
		}
	}

	/**
	 * 查询字典详细
	 */
	@RequiresPermissions("system:dict:list")
	@GetMapping("/detail/{dictId}")
	public String detail(@PathVariable("dictId") Integer dictId, ModelMap mmap) {
		mmap.put( "dict", dictTypeService.selectDictTypeById( dictId ) );
		mmap.put( "dictList", dictTypeService.selectDictTypeAll() );
		return "system/dict/data/data";
	}

	/**
	 * 校验字典类型
	 */
	@PostMapping("/checkDictTypeUnique")
	@ResponseBody
	public String checkDictTypeUnique(SysDictType dictType) {
		return dictTypeService.checkDictTypeUnique( dictType );
	}
}
