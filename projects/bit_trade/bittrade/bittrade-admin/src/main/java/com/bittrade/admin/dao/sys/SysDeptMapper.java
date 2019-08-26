package com.bittrade.admin.dao.sys;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bittrade.admin.model.domain.SysDept;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author ourblue
 * @since 2018-11-03
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {

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
	public SysDept selectDeptById(Integer deptId);
	
	/**
	 * .根据角色ID查询部门
	 *
	 * @param roleId
	 * @return 部门列表
	 */
	public List<String> selectRoleDeptTree(Integer roleId);
	
	/**
     * .查询部门人数
     * 
     * @param dept 部门信息
     * @return 结果
     */
    public int selectDeptCount(SysDept dept);
    
    /**
     * .查询部门是否存在用户
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    public int checkDeptExistUser(Integer deptId);

}
