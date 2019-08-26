package com.jdcloud.provider.web.sys;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jdcloud.provider.pojo.SysDept;
import com.jdcloud.provider.pojo.SysRole;
import com.jdcloud.provider.service.SysDeptService;
import com.jdcloud.provider.utils.ShiroUtils;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.Wrapper;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author ourblue
 * @since 2018-11-03
 */
@Controller
@RequestMapping("/system/dept")
public class SysDeptController extends BaseController {

	private String			prefix	= "system/dept";

	@Autowired
	private SysDeptService	deptService;

	@RequiresPermissions("system:dept:view")
	@GetMapping()
	public String dept() {
		return prefix + "/dept";
	}

	@RequiresPermissions("system:dept:list")
	@GetMapping("/list")
	@ResponseBody
	public List<SysDept> list(SysDept dept) {
		List<SysDept> deptList = deptService.selectDeptList( dept );
		return deptList;
	}
	
	/**
	 * .新增部门
	 * 
	 * @param parentId
	 * @param mmap
	 * @return
	 */
	@GetMapping("/add/{parentId}")
	public String add(@PathVariable("parentId") Integer parentId, ModelMap mmap) {
		mmap.put( "dept", deptService.selectDeptById( parentId ) );
		return prefix + "/add";
	}

	/**
	 * .新增保存部门
	 * @param dept
	 * @return
	 */
	@RequiresPermissions("system:dept:add")
	@PostMapping("/add")
	@ResponseBody
	public Wrapper<String> addSave(SysDept dept) {
		dept.setCreateBy( ShiroUtils.getLoginName() );
		dept.setCreateTime( new Date() );
		deptService.insert( dept );
		return success();
	}

	@GetMapping("/edit/{deptId}")
	public String edit(@PathVariable("deptId") Integer deptId, ModelMap mmap) {
		SysDept dept = deptService.selectDeptById( deptId );
		if (dept != null && 100 == deptId) {
			dept.setParentName( "无" );
		}
		mmap.put( "dept", dept );
		return prefix + "/edit";
	}

	@RequiresPermissions("system:dept:edit")
	@PostMapping("/edit")
	@ResponseBody
	public Wrapper<String> editSave(SysDept dept) {
		dept.setUpdateBy( ShiroUtils.getLoginName() );
		deptService.updateById( dept );
		return success();
	}

	@RequiresPermissions("system:dept:remove")
	@PostMapping("/remove/{deptId}")
	@ResponseBody
	public Wrapper<String> remove(@PathVariable("deptId") Integer deptId) {
		if (deptService.selectDeptCount( deptId ) > 0) {
			return error( 1, "存在下级部门,不允许删除" );
		}
		if (deptService.checkDeptExistUser( deptId )) {
			return error( 1, "部门存在用户,不允许删除" );
		}
		deptService.deleteById( deptId );
		return success();
	}

	/**
	 * .校验部门名称
	 * 
	 * @param dept
	 * @return
	 */
	@PostMapping("/checkDeptNameUnique")
	@ResponseBody
	public String checkDeptNameUnique(SysDept dept) {
		return deptService.selectCount( new EntityWrapper<SysDept>( dept ) ) > 0 ? "1" : "0";
	}

	/**
	 * .选择部门树
	 * 
	 * @param deptId
	 * @param mmap
	 * @return
	 */
	@GetMapping("/selectDeptTree/{deptId}")
	public String selectDeptTree(@PathVariable("deptId") Integer deptId, ModelMap mmap) {
		mmap.put( "dept", deptService.selectDeptById( deptId ) );
		return prefix + "/tree";
	}

	/**
	 * .加载部门列表树
	 * 
	 * @return
	 */
	@GetMapping("/treeData")
	@ResponseBody
	public List<Map<String, Object>> treeData() {
		List<Map<String, Object>> tree = deptService.selectDeptTree();
		return tree;
	}

	/**
	 * .加载角色部门（数据权限）列表树
	 * 
	 * @param role
	 * @return
	 */
	@GetMapping("/roleDeptTreeData")
	@ResponseBody
	public List<Map<String, Object>> deptTreeData(SysRole role) {
		List<Map<String, Object>> tree = deptService.roleDeptTreeData( role );
		return tree;
	}
}