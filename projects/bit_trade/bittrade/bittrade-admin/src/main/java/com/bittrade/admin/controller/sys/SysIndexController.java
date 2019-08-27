package com.bittrade.admin.controller.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.bittrade.admin.constant.GlobalConstant;
import com.bittrade.admin.controller.base.BaseController;
import com.bittrade.admin.model.domain.SysUser;
import com.bittrade.admin.service.sys.SysMenuService;

/**
 * .首页
 *
 * @author ourblue
 */
@Controller
public class SysIndexController extends BaseController {

	@Autowired
	private SysMenuService sysMenuService;

	@GetMapping("/index")
	public String index(ModelMap mmap) {
		SysUser user = getUser();
		// 根据用户Id取出拥有的菜单
		mmap.put("menus", sysMenuService.selectMenusByUser(user));
		user.setSex(getSex(user.getSex()));
		mmap.put("user", user);
		mmap.put("copyrightYear", GlobalConstant.CURRENT_YEAR);
		return "index";
	}

}
