package com.bittrade.admin.controller.sys;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bittrade.admin.controller.base.BaseController;
import com.bittrade.admin.service.sys.SysUserService;
import com.bittrade.admin.shiro.service.PasswordService;
import com.bittrade.admin.util.FileUploadUtil;
import com.bittrade.admin.util.ShiroUtil;
import com.bittrade.admin.wrapper.Wrapper;
import com.bittrade.pojo.dto.SysUserDTO;
import com.bittrade.pojo.model.SysUser;

import lombok.extern.slf4j.Slf4j;

/**
 * .个人信息
 * 
 * @author who ?
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
		SysUserDTO userDTO = getUser();
		userDTO.setSex( getSex( userDTO.getSex() ) );
		mmap.put( "user", userDTO );
		mmap.put( "roleGroup", sysUserService.selectUserRoleGroup( userDTO.getUserId() ) );
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
		SysUserDTO userDTO = getUser();
		String encrypt = new Md5Hash( userDTO.getLoginName() + password + userDTO.getSalt() ).toHex().toString();
		if (userDTO.getPassword().equals( encrypt )) {
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
		user.setSalt( ShiroUtil.randomSalt() );
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
				String avatar = FileUploadUtil.upload( imgpath, file );
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