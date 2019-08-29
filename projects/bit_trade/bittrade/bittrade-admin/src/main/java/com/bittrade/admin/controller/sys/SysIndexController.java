package com.bittrade.admin.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.bittrade.admin.controller.base.BaseController;
import com.bittrade.admin.service.sys.SysMenuService;
import com.bittrade.pojo.dto.SysUserDTO;
import com.core.common.constant.GlobalConstant;

/**
 * .首页
 *
 * @author who ?
 */
@Controller
public class SysIndexController extends BaseController {

	@Autowired
	private SysMenuService sysMenuService;

	@GetMapping("/index")
	public String index(ModelMap mmap) {
		SysUserDTO userDTO = getUser();
		// 根据用户Id取出拥有的菜单
		mmap.put("menus", sysMenuService.selectMenusByUser(userDTO));
		userDTO.setSex(getSex(userDTO.getSex()));
		mmap.put("user", userDTO);
		mmap.put("copyrightYear", GlobalConstant.CURRENT_YEAR);
		return "index";
	}

}
