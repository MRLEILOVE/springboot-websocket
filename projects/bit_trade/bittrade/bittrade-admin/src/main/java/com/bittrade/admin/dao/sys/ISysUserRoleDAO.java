package com.bittrade.admin.dao.sys;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bittrade.pojo.model.SysUserRole;

/**
 * <p>
 * 用户和角色关联表 Mapper 接口
 * </p>
 *
 * @author who ?
 * @since 2018-11-03
 */
public interface ISysUserRoleDAO extends BaseMapper<SysUserRole> {
	
	/**
	 * 通过用户ID删除用户和角色关联
	 * 
	 * @param userId
	 * @return 结果
	 */
	public int deleteUserRoleByUserId(Integer userId);

	/**
	 * 批量删除用户和角色关联
	 * 
	 * @param ids
	 * @return 结果
	 */
	public int deleteUserRole(Integer[] ids);

	/**
	 * 通过角色ID查询角色使用数量
	 * 
	 * @param roleId
	 * @return 结果
	 */
	public int countUserRoleByRoleId(Integer roleId);

	/**
	 * 批量新增用户角色信息
	 * 
	 * @param userRoleList
	 * @return 结果
	 */
	public int batchUserRole(List<SysUserRole> userRoleList);

}
