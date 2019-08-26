package com.jdcloud.provider.web.sys;

import com.jdcloud.provider.pojo.SysUser;
import com.jdcloud.provider.service.SysUserService;
import com.jdcloud.provider.shiro.service.PasswordService;
import com.jdcloud.provider.utils.FileUploadUtils;
import com.jdcloud.provider.utils.ShiroUtils;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * .个人信息
 * 
 * @author ourblue
 */
@Slf4j
@Controller
@RequestMapping("/system/user/profile")
public class SysProfileController extends BaseController {

	@Autowired
	private SysUserService	sysUserService;
	@Autowired
	private PasswordService	passwordService;
	
	@Value("${user.file.profile.imgpath}")
	private String  imgpath;

	private String			prefix	= "system/user/profile";

	/**
	 * .个人信息
	 * 
	 * @param mmap
	 * @return
	 */
	@GetMapping()
	public String profile(ModelMap mmap) {
		SysUser user = getUser();
		user.setSex( getSex( user.getSex() ) );
		mmap.put( "user", user );
		mmap.put( "roleGroup", sysUserService.selectUserRoleGroup( user.getUserId() ) );
		return prefix + "/profile";
	}

	/**
	 * .检查密码
	 * 
	 * @param password
	 * @return
	 */
	@GetMapping("/checkPassword")
	@ResponseBody
	public boolean checkPassword(String password) {
		SysUser user = getUser();
		String encrypt = new Md5Hash( user.getLoginName() + password + user.getSalt() ).toHex().toString();
		if (user.getPassword().equals( encrypt )) {
			return true;
		}
		return false;
	}

	/**
	 * .修改密码
	 * 
	 * @param user
	 * @return
	 */
	@GetMapping("/resetPwd/{userId}")
	public String resetPwd(@PathVariable("userId") Integer userId, ModelMap mmap) {
		mmap.put( "user", sysUserService.selectUserById( userId ) );
		return prefix + "/resetPwd";
	}

	/**
	 * .修改密码
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/resetPwd")
	@ResponseBody
	public Wrapper<String> resetPwd(SysUser user) {
		user.setSalt( ShiroUtils.randomSalt() );
		user.setPassword( passwordService.encryptPassword( user.getLoginName(), user.getPassword(), user.getSalt() ) );
		if (sysUserService.updateById( user )) {
			setUser( sysUserService.selectUserById( user.getUserId() ) );
			return success();
		}
		return error();
	}

	/**
	 * .编辑个人信息
	 * 
	 * @param userId
	 * @param mmap
	 * @return
	 */
	@GetMapping("/edit/{userId}")
	public String edit(@PathVariable("userId") Integer userId, ModelMap mmap) {
		mmap.put( "user", sysUserService.selectUserById( userId ) );
		return prefix + "/edit";
	}

	/**
	 * .保存头像
	 * 
	 * @param userId
	 * @param mmap
	 * @return
	 */
	@GetMapping("/avatar/{userId}")
	public String avatar(@PathVariable("userId") Integer userId, ModelMap mmap) {
		mmap.put( "user", sysUserService.selectUserById( userId ) );
		return prefix + "/avatar";
	}

	/**
	 * .修改用户
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/update")
	@ResponseBody
	public Wrapper<String> update(SysUser user) {
		if (sysUserService.updateById( user )) {
			setUser( sysUserService.selectUserById( user.getUserId() ) );
			return success();
		}
		return error();
	}

	/**
	 * .保存头像
	 * 
	 * @param user
	 * @param file
	 * @return
	 */
	@PostMapping("/updateAvatar")
	@ResponseBody
	public Wrapper<String> updateAvatar(SysUser user, @RequestParam("avatarfile") MultipartFile file) {
		try {
			if (!file.isEmpty()) {
				String avatar = FileUploadUtils.upload( imgpath, file );
				user.setAvatar( avatar );
				if (sysUserService.updateById( user )) {
					setUser( sysUserService.selectUserById( user.getUserId() ) );
					return success();
				}
			}
			return error();
		} catch (Exception e) {
			log.error( "修改头像失败！", e );
			return error( e.getMessage() );
		}
	}

}