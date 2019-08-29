package com.bittrade.admin.service.sys;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bittrade.pojo.dto.SysUserDTO;
import com.bittrade.pojo.model.SysUser;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author who ?
 * @since 2018-11-03
 */
public interface SysUserService extends IService<SysUser> {

	/**
	 * .根据用户ID查询用户所属角色组
	 * 
	 * @param userId
	 *            用户ID
	 * @return 结果
	 */
	public String selectUserRoleGroup(Integer userId);

	/**
	 * .通过用户ID查询用户
	 * 
	 * @param userId
	 * @return 用户对象信息
	 */
	public SysUser selectUserById(Integer userId);

	/**
	 * .通过用户名查询用户
	 * 
	 * @param userName
	 * @return 用户对象信息
	 */
	public SysUser selectUserByLoginName(String userName);

	/**
	 * .通过手机号码查询用户
	 * 
	 * @param phoneNumber
	 * @return 用户对象信息
	 */
	public SysUser selectUserByPhoneNumber(String phoneNumber);

	/**
	 * .通过邮箱查询用户
	 * 
	 * @param email
	 * @return 用户对象信息
	 */
	public SysUser selectUserByEmail(String email);
	
	/**
	 * .批量删除用户信息
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 * @throws Exception
	 */
	public int deleteUserByIds(String ids) throws Exception;
	
	/**
	 * .用户分页
	 * 
	 * @param sysUser
	 * @return
	 */
	public Page<SysUser> selectUserList(SysUser sysUser, Page<SysUser> page);
	
	/**
	 * 保存用户信息
	 * 
	 * @param userDTO
	 * @return 结果
	 */
	public int updateUser(SysUserDTO userDTO);
	
	/**
     * 保存用户信息
     * 
     * @param userDTO 用户信息
     * @return 结果
     */
    public int insertUser(SysUserDTO userDTO);

}
