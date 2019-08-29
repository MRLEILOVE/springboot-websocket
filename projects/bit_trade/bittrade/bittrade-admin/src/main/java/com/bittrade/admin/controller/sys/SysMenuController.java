package com.bittrade.admin.controller.sys;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bittrade.admin.annotation.Log;
import com.bittrade.admin.controller.base.BaseController;
import com.bittrade.admin.enums.AnnotationEnum.BusinessType;
import com.bittrade.admin.service.sys.SysMenuService;
import com.bittrade.admin.util.ShiroUtil;
import com.bittrade.admin.wrapper.Wrapper;
import com.bittrade.pojo.model.SysMenu;
import com.bittrade.pojo.model.SysRole;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author who ?
 * @since 2018-11-03
 */
@Controller
@RequestMapping("/system/menu")
public class SysMenuController extends BaseController {

	private String			prefix	= "system/menu";

	@Autowired
	private SysMenuService	sysMenuService;

	@RequiresPermissions("system:menu:view")
	@GetMapping()
	public String menu() {
		return prefix + "/menu";
	}

	@RequiresPermissions("system:menu:list")
	@GetMapping("/list")
	@ResponseBody
	public List<SysMenu> list(SysMenu menu) {
		List<SysMenu> menuList = sysMenuService.selectMenuList( menu );
		return menuList;
	}
	
	/**
	 * 删除菜单
	 * 
	 * @param menuId
	 * @return
	 */
	@Log(title = "菜单管理", buinessType = BusinessType.DELETE)
	@RequiresPermissions("system:menu:remove")
	@PostMapping("/remove/{menuId}")
	@ResponseBody
	public Wrapper<String> remove(@PathVariable("menuId") Integer menuId) {
		if (sysMenuService.selectCountMenuByParentId( menuId ) > 0) {
			return error( "存在子菜单,不允许删除" );
		}
		if (sysMenuService.selectCountRoleMenuByMenuId( menuId ) > 0) {
			return error( "菜单已分配,不允许删除" );
		}
		ShiroUtil.clearCachedAuthorizationInfo();
		return toAjax( sysMenuService.deleteMenuById( menuId ) );
	}
	
	/**
	 * 新增目录
	 * 
	 * @param parentId
	 * @param mmap
	 * @return
	 */
	@GetMapping("/add/{parentId}")
	public String add(@PathVariable("parentId") Integer parentId, ModelMap mmap) {
		SysMenu menu = null;
		if (0 != parentId) {
			menu = sysMenuService.selectMenuById( parentId );
		} else {
			menu = new SysMenu();
			menu.setMenuId( 0 );
			menu.setMenuName( "主目录" );
		}
		mmap.put( "menu", menu );
		return prefix + "/add";
	}
	
	/**
	 * 新增保存菜单
	 */
	@Log(title = "菜单管理", buinessType = BusinessType.INSERT)
	@RequiresPermissions("system:menu:add")
	@PostMapping("/add")
	@ResponseBody
	public Wrapper<String> addSave(SysMenu menu) {
		menu.setCreateBy( ShiroUtil.getLoginName() );
		ShiroUtil.clearCachedAuthorizationInfo();
		return toAjax( sysMenuService.insertMenu( menu ) );
	}

	/**
	 * 修改菜单
	 */
	@GetMapping("/edit/{menuId}")
	public String edit(@PathVariable("menuId") Integer menuId, ModelMap mmap) {
		mmap.put( "menu", sysMenuService.selectMenuById( menuId ) );
		return prefix + "/edit";
	}

	/**
	 * 修改保存菜单
	 */
	@Log(title = "菜单管理", buinessType = BusinessType.UPDATE)
	@RequiresPermissions("system:menu:edit")
	@PostMapping("/edit")
	@ResponseBody
	public Wrapper<String> editSave(SysMenu menu) {
		menu.setUpdateBy( ShiroUtil.getLoginName() );
		ShiroUtil.clearCachedAuthorizationInfo();
		return toAjax( sysMenuService.updateMenu( menu ) );
	}

	/**
	 * 选择菜单图标
	 */
	@GetMapping("/icon")
	public String icon() {
		return prefix + "/icon";
	}

	/**
	 * 校验菜单名称
	 */
	@PostMapping("/checkMenuNameUnique")
	@ResponseBody
	public String checkMenuNameUnique(SysMenu menu) {
		return sysMenuService.checkMenuNameUnique( menu );
	}

	/**
	 * 加载角色菜单列表树
	 */
	@GetMapping("/roleMenuTreeData")
	@ResponseBody
	public List<Map<String, Object>> roleMenuTreeData(SysRole role) {
		List<Map<String, Object>> tree = sysMenuService.roleMenuTreeData( role );
		return tree;
	}

	/**
	 * 加载所有菜单列表树
	 */
	@GetMapping("/menuTreeData")
	@ResponseBody
	public List<Map<String, Object>> menuTreeData(SysRole role) {
		List<Map<String, Object>> tree = sysMenuService.menuTreeData();
		return tree;
	}

	/**
	 * 选择菜单树
	 */
	@GetMapping("/selectMenuTree/{menuId}")
	public String selectMenuTree(@PathVariable("menuId") Integer menuId, ModelMap mmap) {
		mmap.put( "menu", sysMenuService.selectMenuById( menuId ) );
		return prefix + "/tree";
	}

}