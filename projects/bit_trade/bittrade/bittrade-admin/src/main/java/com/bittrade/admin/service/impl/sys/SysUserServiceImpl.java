package com.bittrade.admin.service.impl.sys;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.admin.dao.sys.ISysRoleDAO;
import com.bittrade.admin.dao.sys.ISysUserDAO;
import com.bittrade.admin.dao.sys.ISysUserRoleDAO;
import com.bittrade.admin.model.domain.SysRole;
import com.bittrade.admin.model.domain.SysUser;
import com.bittrade.admin.model.domain.SysUserRole;
import com.bittrade.admin.service.sys.SysUserService;
import com.bittrade.admin.util.ConvertUtil;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author who ?
 * @since 2018-11-03
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<ISysUserDAO, SysUser> implements SysUserService {

	@Autowired
	private ISysRoleDAO sysRoleMapper;
	@Autowired
	private ISysUserRoleDAO userRoleMapper;

	@Override
	public String selectUserRoleGroup(Integer userId) {
		List<SysRole> list = sysRoleMapper.selectRolesByUserId( userId );
		StringBuffer idsStr = new StringBuffer();
		for (SysRole role : list) {
			idsStr.append( role.getRoleName() ).append( "," );
		}
		if (StringUtils.isNotEmpty( idsStr.toString() )) {
			return idsStr.substring( 0, idsStr.length() - 1 );
		}
		return idsStr.toString();
	}

	@Override
	public SysUser selectUserById(Integer userId) {
		return baseMapper.selectUserById( userId );
	}

	@Override
	public SysUser selectUserByLoginName(String userName) {
		return baseMapper.selectUserByLoginName( userName );
	}

	@Override
	public SysUser selectUserByPhoneNumber(String phoneNumber) {
		return baseMapper.selectUserByPhoneNumber( phoneNumber );
	}

	@Override
	public SysUser selectUserByEmail(String email) {
		return baseMapper.selectUserByEmail( email );
	}

	@Override
	public int deleteUserByIds(String ids) throws Exception {
		Integer[] userIds = ConvertUtil.toIntArray( ids );
		return baseMapper.deleteUserByIds( userIds );
	}

	@Override
	public Page<SysUser> selectUserList(SysUser sysUser, Page<SysUser> page) {
		page.setRecords( baseMapper.selectUserList( page, sysUser ) );
		return page;
	}

	@Override
	public int updateUser(SysUser user) {
		Integer userId = user.getUserId();
		// 删除用户与角色关联
		userRoleMapper.deleteUserRoleByUserId( userId );
		// 新增用户与角色管理
		insertUserRole( user );
		return baseMapper.updateUser( user );
	}
	
	public void insertUserRole(SysUser user) {
		// 新增用户与角色管理
		List<SysUserRole> list = new ArrayList<SysUserRole>();
		for (Integer roleId : user.getRoleIds()) {
			SysUserRole ur = new SysUserRole();
			ur.setUserId( user.getUserId() );
			ur.setRoleId( roleId );
			list.add( ur );
		}
		if (list.size() > 0) {
			userRoleMapper.batchUserRole( list );
		}
	}

	@Override
	public int insertUser(SysUser user) {
		// 新增用户信息
		int rows = baseMapper.insertUser( user );
		// 新增用户与角色管理
		insertUserRole( user );
		return rows;
	}
}