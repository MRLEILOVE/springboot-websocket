package com.jdcloud.provider.web.sys;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jdcloud.base.constant.GlobalConstant.Sys;
import com.jdcloud.base.enums.AnnotationEnum.BusinessType;
import com.jdcloud.provider.pojo.SysUser;
import com.jdcloud.provider.service.SysRoleService;
import com.jdcloud.provider.service.SysUserService;
import com.jdcloud.provider.shiro.service.PasswordService;
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

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author ourblue
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
		List<SysUser> users = sysUserService.selectList( new EntityWrapper<SysUser>( user ) );
		ExcelUtil<SysUser> util = new ExcelUtil<SysUser>( SysUser.class );
		return util.exportExcel( users, "user" );
	}

	@GetMapping("/add")
	public String add(ModelMap mmap) {
		mmap.put( "roles", sysRoleService.selectList(null) );
		return prefix + "/add";
	}

	@Log(title = "用户管理", buinessType = BusinessType.INSERT)
	@RequiresPermissions("system:user:add")
	@PostMapping("/add")
	@Transactional(rollbackFor = Exception.class)
	@ResponseBody
	public Wrapper<String> addSave(SysUser user) {
		if (user.getLoginName().equals( Sys.ADMIN )) {
			return error( "不允许修改超级管理员" );
		}
		user.setSalt( ShiroUtils.randomSalt() );
		user.setPassword( passwordService.encryptPassword( user.getLoginName(), user.getPassword(), user.getSalt() ) );
		user.setCreateBy( ShiroUtils.getLoginName() );
		user.setCreateTime( new Date() );
		return toAjax( sysUserService.insertUser( user ) );
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
	public Wrapper<String> editSave(SysUser user) {
		user.setUpdateBy( ShiroUtils.getLoginName() );
		return toAjax( sysUserService.updateUser( user ) );
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
		user.setSalt( ShiroUtils.randomSalt() );
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
		return sysUserService.selectCount( new EntityWrapper<SysUser>( user ) ) > 0 ? "1" : "0";
	}

	@PostMapping("/checkPhoneUnique")
	@ResponseBody
	public String checkPhoneUnique(SysUser user) {
		SysUser sysUser = getUser();
		if (sysUser.getPhonenumber().equals(user.getPhonenumber())) {
			return "0";
		}
		return sysUserService.selectCount( new EntityWrapper<SysUser>( user ) ) > 0 ? "1" : "0";
	}

	@PostMapping("/checkEmailUnique")
	@ResponseBody
	public String checkEmailUnique(SysUser user) {
		SysUser sysUser = getUser();
		if (sysUser.getEmail().equals(user.getEmail())) {
			return "0";
		}
		return sysUserService.selectCount( new EntityWrapper<SysUser>( user ) ) > 0 ? "1" : "0";
	}

}