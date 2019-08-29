package com.bittrade.admin.controller.sys;

import java.io.IOException;
import java.time.LocalDateTime;
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
import com.bittrade.admin.service.sys.SysUserService;
import com.bittrade.admin.shiro.service.PasswordService;
import com.bittrade.admin.util.ExcelUtil;
import com.bittrade.admin.util.ShiroUtil;
import com.bittrade.admin.wrapper.TableDataInfo;
import com.bittrade.admin.wrapper.Wrapper;
import com.bittrade.pojo.dto.SysUserDTO;
import com.bittrade.pojo.model.SysUser;
import com.core.common.constant.GlobalConstant.Sys;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author who ?
 * @since 2018-11-03
 */
@Controller
@RequestMapping("/system/user")
public class SysUserController extends BaseController {

	private String			prefix	= "system/user";

	@Autowired
	private SysUserService	sysUserService;
	@Autowired
	private PasswordService	passwordService;
	@Autowired
	private SysRoleService	sysRoleService;

	@RequiresPermissions("system:user:view")
	@GetMapping()
	public String user() {
		return prefix + "/user";
	}

	@RequiresPermissions("system:user:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SysUser user) {
		Page<SysUser> users = sysUserService.selectUserList( user, getPage() );
		return new TableDataInfo( users.getRecords(), users.getTotal() );
	}

	@Log(title = "用户管理", buinessType = BusinessType.EXPORT)
	@RequiresPermissions("system:user:export")
	@PostMapping("/export")
	@ResponseBody
	public Wrapper<String> export(SysUser user) throws IOException {
		List<SysUser> users = sysUserService.list( new QueryWrapper<SysUser>( user ) );
		ExcelUtil<SysUser> util = new ExcelUtil<SysUser>( SysUser.class );
		return util.exportExcel( users, "user" );
	}

	@GetMapping("/add")
	public String add(ModelMap mmap) {
		mmap.put( "roles", sysRoleService.list(null) );
		return prefix + "/add";
	}

	@Log(title = "用户管理", buinessType = BusinessType.INSERT)
	@RequiresPermissions("system:user:add")
	@PostMapping("/add")
	@Transactional(rollbackFor = Exception.class)
	@ResponseBody
	public Wrapper<String> addSave(SysUserDTO userDTO) {
		if (userDTO.getLoginName().equals( Sys.ADMIN )) {
			return error( "不允许修改超级管理员" );
		}
		userDTO.setSalt( ShiroUtil.randomSalt() );
		userDTO.setPassword( passwordService.encryptPassword( userDTO.getLoginName(), userDTO.getPassword(), userDTO.getSalt() ) );
		userDTO.setCreateBy( ShiroUtil.getLoginName() );
		userDTO.setCreateTime( LocalDateTime.now() );
		return toAjax( sysUserService.insertUser( userDTO ) );
	}

	@GetMapping("/edit/{userId}")
	public String edit(@PathVariable("userId") Integer userId, ModelMap mmap) {
		mmap.put( "user", sysUserService.selectUserById( userId ) );
		mmap.put( "roles", sysRoleService.selectRolesByUserId( userId ) );
		return prefix + "/edit";
	}

	@Log(title = "用户管理", buinessType = BusinessType.UPDATE)
	@RequiresPermissions("system:user:edit")
	@PostMapping("/edit")
	@Transactional(rollbackFor = Exception.class)
	@ResponseBody
	public Wrapper<String> editSave(SysUserDTO userDTO) {
		userDTO.setUpdateBy( ShiroUtil.getLoginName() );
		return toAjax( sysUserService.updateUser( userDTO ) );
	}

	@RequiresPermissions("system:user:resetPwd")
	@GetMapping("/resetPwd/{userId}")
	public String resetPwd(@PathVariable("userId") Integer userId, ModelMap mmap) {
		mmap.put( "user", sysUserService.selectUserById( userId ) );
		return prefix + "/resetPwd";
	}

	@Log(title = "重置密码", buinessType = BusinessType.UPDATE)
	@RequiresPermissions("system:user:resetPwd")
	@PostMapping("/resetPwd")
	@ResponseBody
	public Wrapper<String> resetPwdSave(SysUser user) {
		user.setSalt( ShiroUtil.randomSalt() );
		user.setPassword( passwordService.encryptPassword( user.getLoginName(), user.getPassword(), user.getSalt() ) );
		sysUserService.updateById( user );
		return success();
	}

	@Log(title = "用户管理", buinessType = BusinessType.DELETE)
	@RequiresPermissions("system:user:remove")
	@PostMapping("/remove")
	@ResponseBody
	public Wrapper<String> remove(String ids) {
		try {
			return toAjax( sysUserService.deleteUserByIds( ids ) );
		} catch (Exception e) {
			return error( "删除失败" );
		}
	}

	@PostMapping("/checkLoginNameUnique")
	@ResponseBody
	public String checkLoginNameUnique(SysUser user) {
		SysUser sysUser = getUser();
		if (sysUser.getLoginName().equals(user.getLoginName())) {
			return "0";
		}
		return sysUserService.count( new QueryWrapper<SysUser>( user ) ) > 0 ? "1" : "0";
	}

	@PostMapping("/checkPhoneUnique")
	@ResponseBody
	public String checkPhoneUnique(SysUser user) {
		SysUser sysUser = getUser();
		if (sysUser.getPhonenumber().equals(user.getPhonenumber())) {
			return "0";
		}
		return sysUserService.count( new QueryWrapper<SysUser>( user ) ) > 0 ? "1" : "0";
	}

	@PostMapping("/checkEmailUnique")
	@ResponseBody
	public String checkEmailUnique(SysUser user) {
		SysUser sysUser = getUser();
		if (sysUser.getEmail().equals(user.getEmail())) {
			return "0";
		}
		return sysUserService.count( new QueryWrapper<SysUser>( user ) ) > 0 ? "1" : "0";
	}

}