package com.jdcloud.provider.web.sys;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.provider.pojo.SysDictType;
import com.jdcloud.provider.service.SysDictTypeService;
import com.jdcloud.provider.utils.ExcelUtil;
import com.jdcloud.provider.utils.ShiroUtils;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
		List<SysDictType> list = dictTypeService.selectList( new EntityWrapper<SysDictType>( dictType ) );
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
		dict.setCreateBy( ShiroUtils.getLoginName() );
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
		dict.setUpdateBy( ShiroUtils.getLoginName() );
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
