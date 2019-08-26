package com.jdcloud.provider.web.sys;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.enums.AnnotationEnum.BusinessType;
import com.jdcloud.provider.pojo.SysRole;
import com.jdcloud.provider.service.SysRoleService;
import com.jdcloud.provider.utils.ExcelUtil;
import com.jdcloud.provider.utils.ShiroUtils;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.annotation.Log;
import com.jdcloud.util.wrapper.TableDataInfo;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ourblue
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
		List<SysRole> list = sysRoleService.selectList( new EntityWrapper<SysRole>( role ) );
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
	public Wrapper<String> addSave(SysRole role) {
		role.setCreateBy( ShiroUtils.getLoginName() );
		ShiroUtils.clearCachedAuthorizationInfo();
		return toAjax( sysRoleService.insertRole( role ) );
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
	public Wrapper<String> editSave(SysRole role) {
		role.setUpdateBy( ShiroUtils.getLoginName() );
		ShiroUtils.clearCachedAuthorizationInfo();
		return toAjax( sysRoleService.updateRole( role ) );
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
	public Wrapper<String> ruleSave(SysRole role) {
		role.setUpdateBy( ShiroUtils.getLoginName() );
		return toAjax( sysRoleService.updateRule( role ) );
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