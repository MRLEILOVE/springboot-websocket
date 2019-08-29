package com.bittrade.admin.controller.sys;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.admin.annotation.Log;
import com.bittrade.admin.controller.base.BaseController;
import com.bittrade.admin.enums.AnnotationEnum.BusinessType;
import com.bittrade.admin.service.sys.SysRoleService;
import com.bittrade.admin.util.ExcelUtil;
import com.bittrade.admin.util.ShiroUtil;
import com.bittrade.admin.wrapper.TableDataInfo;
import com.bittrade.admin.wrapper.Wrapper;
import com.bittrade.pojo.dto.SysRoleDTO;
import com.bittrade.pojo.model.SysRole;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author who ?
 * @since 2018-11-03
 */
@Controller
@RequestMapping("/system/role")
public class SysRoleController extends BaseController {

	private String			prefix	= "system/role";

	@Autowired
	private SysRoleService	sysRoleService;

	@RequiresPermissions("system:role:view")
	@GetMapping
	public String role() {
		return prefix + "/role";
	}

	@RequiresPermissions("system:role:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SysRole sysRole) {
		Page<SysRole> roles = sysRoleService.selectRoleList( sysRole, getPage() );
		return new TableDataInfo( roles.getRecords(), roles.getTotal() );
	}

	@Log(title = "角色管理", buinessType = BusinessType.EXPORT)
	@RequiresPermissions("system:role:export")
	@PostMapping("/export")
	@ResponseBody
	public Wrapper<String> export(SysRole role) {
		List<SysRole> list = sysRoleService.list( new QueryWrapper<SysRole>( role ) );
		ExcelUtil<SysRole> util = new ExcelUtil<>( SysRole.class );
		return util.exportExcel( list, "role" );
	}

	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	@RequiresPermissions("system:role:add")
	@Log(title = "角色管理", buinessType = BusinessType.INSERT)
	@PostMapping("/add")
	@Transactional(rollbackFor = Exception.class)
	@ResponseBody
	public Wrapper<String> addSave(SysRoleDTO roleDTO) {
		roleDTO.setCreateBy( ShiroUtil.getLoginName() );
		ShiroUtil.clearCachedAuthorizationInfo();
		return toAjax( sysRoleService.insertRole( roleDTO ) );
	}

	@GetMapping("/edit/{roleId}")
	public String edit(@PathVariable("roleId") Integer roleId, ModelMap mmap) {
		mmap.put( "role", sysRoleService.selectRoleById( roleId ) );
		return prefix + "/edit";
	}

	/**
	 * 修改保存角色
	 */
	@RequiresPermissions("system:role:edit")
	@Log(title = "角色管理", buinessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@Transactional(rollbackFor = Exception.class)
	@ResponseBody
	public Wrapper<String> editSave(SysRoleDTO roleDTO) {
		roleDTO.setUpdateBy( ShiroUtil.getLoginName() );
		ShiroUtil.clearCachedAuthorizationInfo();
		return toAjax( sysRoleService.updateRole( roleDTO ) );
	}

	/**
	 * 新增数据权限
	 */
	@GetMapping("/rule/{roleId}")
	public String rule(@PathVariable("roleId") Integer roleId, ModelMap mmap) {
		mmap.put( "role", sysRoleService.selectRoleById( roleId ) );
		return prefix + "/rule";
	}

	/**
	 * 修改保存数据权限
	 */
	@RequiresPermissions("system:role:edit")
	@Log(title = "角色管理", buinessType = BusinessType.UPDATE)
	@PostMapping("/rule")
	@Transactional(rollbackFor = Exception.class)
	@ResponseBody
	public Wrapper<String> ruleSave(SysRoleDTO roleDTO) {
		roleDTO.setUpdateBy( ShiroUtil.getLoginName() );
		return toAjax( sysRoleService.updateRule( roleDTO ) );
	}

	@RequiresPermissions("system:role:remove")
	@Log(title = "角色管理", buinessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public Wrapper<String> remove(String ids) {
		try {
			return toAjax( sysRoleService.deleteRoleByIds( ids ) );
		} catch (Exception e) {
			return error( e.getMessage() );
		}
	}

	/**
	 * 校验角色名称
	 */
	@PostMapping("/checkRoleNameUnique")
	@ResponseBody
	public String checkRoleNameUnique(SysRole role) {
		return sysRoleService.checkRoleNameUnique( role );
	}

	/**
	 * 校验角色权限
	 */
	@PostMapping("/checkRoleKeyUnique")
	@ResponseBody
	public String checkRoleKeyUnique(SysRole role) {
		return sysRoleService.checkRoleKeyUnique( role );
	}

	/**
	 * 选择菜单树
	 */
	@GetMapping("/selectMenuTree")
	public String selectMenuTree() {
		return prefix + "/tree";
	}

}