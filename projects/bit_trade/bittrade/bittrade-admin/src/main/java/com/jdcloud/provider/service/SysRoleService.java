package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.SysRole;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ourblue
 * @since 2018-11-03
 */
public interface SysRoleService extends IService<SysRole> {

	/**
	 * .根据用户ID查询角色
	 * 
	 * @param userId
	 * @return 权限列表
	 */
	public Set<String> selectRoleKeys(Integer userId);

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
	public Page<SysRole> selectRoleList(SysRole role , Page<SysRole> page);
	
	/**
     * .增保存角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int insertRole(SysRole role);
    
    /**
     *. 查询所有角色
     * 
     * @return 角色列表
     */
    public List<SysRole> selectRoleAll();

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
    public boolean deleteRoleById(Integer roleId);

    /**
     * .批量删除角色用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     * @throws Exception 异常
     */
    public int deleteRoleByIds(String ids) throws Exception;

    /**
     * .修改保存角色信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int updateRole(SysRole role);

    /**
     * .修改数据权限信息
     * 
     * @param role 角色信息
     * @return 结果
     */
    public int updateRule(SysRole role);

    /**
     * .校验角色名称是否唯一
     * 
     * @param role 角色信息
     * @return 结果
     */
    public String checkRoleNameUnique(SysRole role);

    /**
     * .校验角色权限是否唯一
     * 
     * @param role 角色信息
     * @return 结果
     */
    public String checkRoleKeyUnique(SysRole role);

    /**
     * .通过角色ID查询角色使用数量
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public int countUserRoleByRoleId(Integer roleId);

}