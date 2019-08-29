package com.bittrade.admin.service.sys;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bittrade.pojo.dto.SysDeptDTO;
import com.bittrade.pojo.model.SysDept;
import com.bittrade.pojo.model.SysRole;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author who ?
 * @since 2018-11-03
 */
public interface SysDeptService extends IService<SysDept> {

	/**
	 * .查询部门管理数据
	 * 
	 * @param dept
	 * @return 部门信息集合
	 */
	public List<SysDept> selectDeptList(SysDept dept);

	/**
	 * .根据部门ID查询信息
	 * 
	 * @param deptId
	 * @return 部门信息
	 */
	public SysDeptDTO selectDeptById(Integer deptId);

	/**
	 * .查询部门管理树
	 * 
	 * @return 所有部门信息
	 */
	public List<Map<String, Object>> selectDeptTree();

	/**
	 * .根据角色ID查询菜单
	 *
	 * @param role
	 * @return 菜单列表
	 */
	public List<Map<String, Object>> roleDeptTreeData(SysRole role);
	
	 /**
     * .查询部门人数
     * 
     * @param parentId 父部门ID
     * @return 结果
     */
    public int selectDeptCount(Integer parentId);
    
    /**
     * .查询部门是否存在用户
     * 
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkDeptExistUser(Integer deptId);


}
