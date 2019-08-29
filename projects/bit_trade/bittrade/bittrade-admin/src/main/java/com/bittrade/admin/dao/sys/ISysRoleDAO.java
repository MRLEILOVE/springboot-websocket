package com.bittrade.admin.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.__default.DAO.IDefaultSysRoleDAO;
import com.bittrade.pojo.model.SysRole;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author who ?
 * @since 2018-11-03
 */
public interface ISysRoleDAO extends IDefaultSysRoleDAO {

	/**
	 * .根据用户ID查询角色
	 * 
	 * @param userId
	 * @return 角色列表
	 */
	public List<SysRole> selectRolesByUserId(Integer userId);

	/**
	 * .根据条件分页查询角色数据
	 * 
	 * @param role
	 * @return 角色数据集合信息
	 */
	public List<SysRole> selectRoleList(Page<SysRole> page, @Param("role") SysRole role);
	
	/**
     * .新增角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int insertRole(SysRole role);
    
    /**
     * .修改角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int updateRole(SysRole role);
    
    /**
     * .通过角色ID查询角色
     * 
     * @param roleId 角色ID
     * @return 角色对象信息
     */
    public SysRole selectRoleById(Integer roleId);

    /**
     * .通过角色ID删除角色
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public int deleteRoleById(Integer roleId);

    /**
     * .批量角色用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRoleByIds(Integer[] ids);

    /**
     * .校验角色名称是否唯一
     * 
     * @param roleName 角色名称
     * @return 角色信息
     */
    public SysRole checkRoleNameUnique(String roleName);
    
    /**
     * .校验角色权限是否唯一
     * 
     * @param roleKey 角色权限
     * @return 角色信息
     */
    public SysRole checkRoleKeyUnique(String roleKey);

}