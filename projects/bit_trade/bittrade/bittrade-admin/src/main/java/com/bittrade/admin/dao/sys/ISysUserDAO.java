package com.bittrade.admin.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.pojo.model.SysUser;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author who ?
 * @since 2018-11-03
 */
public interface ISysUserDAO extends BaseMapper<SysUser> {

	/**
	 * .通过用户ID查询用户
	 * 
	 * @param userId
	 * @return 用户对象信息
	 */
	public SysUser selectUserById(Integer userId);

	/**
	 * .通过邮箱查询用户
	 * 
	 * @param email
	 * @return 用户对象信息
	 */
	public SysUser selectUserByEmail(String email);

	/**
	 * .通过手机号码查询用户
	 * 
	 * @param phoneNumber
	 * @return 用户对象信息
	 */
	public SysUser selectUserByPhoneNumber(String phoneNumber);

	/**
	 * .通过用户名查询用户
	 * 
	 * @param userName
	 * @return 用户对象信息
	 */
	public SysUser selectUserByLoginName(String userName);
	
	/**
	 * .批量删除用户信息
	 * 
	 * @param ids
	 * @return 结果
	 */
	public int deleteUserByIds(Integer[] ids);
	
	/**
	 * .根据条件分页查询用户对象
	 * 
	 * @param user
	 * @return 用户信息集合信息
	 */
	public List<SysUser> selectUserList(Page<SysUser> page, @Param("sysUser") SysUser sysUser);
	
	/**
     * 修改用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int updateUser(SysUser user);
    
    /**
     * 新增用户信息
     * 
     * @param user 用户信息
     * @return 结果
     */
    public int insertUser(SysUser user);

}