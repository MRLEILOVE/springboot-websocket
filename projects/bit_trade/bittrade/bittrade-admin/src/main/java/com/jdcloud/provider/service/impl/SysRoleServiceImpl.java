package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.enums.UserEnum.UserState;
import com.jdcloud.provider.mapper.SysRoleDeptMapper;
import com.jdcloud.provider.mapper.SysRoleMapper;
import com.jdcloud.provider.mapper.SysRoleMenuMapper;
import com.jdcloud.provider.mapper.SysUserRoleMapper;
import com.jdcloud.provider.pojo.SysRole;
import com.jdcloud.provider.pojo.SysRoleDept;
import com.jdcloud.provider.pojo.SysRoleMenu;
import com.jdcloud.provider.service.SysRoleService;
import com.jdcloud.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2018-11-03
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

	@Autowired
	private SysRoleMenuMapper roleMenuMapper;
	@Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private SysRoleDeptMapper roleDeptMapper;

	@Override
	public Set<String> selectRoleKeys(Integer userId) {
		List<SysRole> perms = baseMapper.selectRolesByUserId( userId );
		Set<String> permsSet = new HashSet<>();
		for (SysRole perm : perms) {
			if (!StringUtils.isEmpty( perm )) {
				permsSet.addAll( Arrays.asList( perm.getRoleKey().trim().split( "," ) ) );
			}
		}
		return permsSet;
	}

	@Override
	public List<SysRole> selectRolesByUserId(Integer userId) {
		List<SysRole> userRoles = baseMapper.selectRolesByUserId( userId );
		List<SysRole> roles = baseMapper.selectList( null );
		for (SysRole role : roles) {
			for (SysRole userRole : userRoles) {
				if (role.getRoleId().longValue() == userRole.getRoleId().longValue()) {
					role.setFlag( true );
					break;
				}
			}
		}
		return roles;
	}

	@Override
	public Page<SysRole> selectRoleList(SysRole role, Page<SysRole> page) {
		page.setRecords( baseMapper.selectRoleList( page, role ) );
		return page;
	}

	@Override
	public int insertRole(SysRole role) {
		// 新增角色信息
		role.setRoleKey( UUID.randomUUID().toString() );
		baseMapper.insertRole( role );
		return insertRoleMenu( role );
	}

	@Override
	public List<SysRole> selectRoleAll() {
		return baseMapper.selectRoleList( new Page<>(), new SysRole() );
	}

	@Override
	public SysRole selectRoleById(Integer roleId) {
		return baseMapper.selectRoleById( roleId );
	}

	@Override
	public boolean deleteRoleById(Integer roleId) {
		return baseMapper.deleteRoleById( roleId ) > 0 ? true : false;
	}

	@Override
	public int deleteRoleByIds(String ids) throws Exception {
		Integer[] roleIds = ConvertUtil.toIntArray( ids );
		for (Integer roleId : roleIds) {
			SysRole role = selectRoleById( roleId );
			if (countUserRoleByRoleId( roleId ) > 0) {
				throw new Exception( String.format( "%1$s已分配,不能删除", role.getRoleName() ) );
			}
		}
		return baseMapper.deleteRoleByIds( roleIds );
	}

	@Override
	public int updateRole(SysRole role) {
		// 修改角色信息
		baseMapper.updateRole(role);
        // 删除角色与菜单关联
        roleMenuMapper.deleteRoleMenuByRoleId(role.getRoleId());
        return insertRoleMenu(role);
	}

	@Override
	public int updateRule(SysRole role) {
		 // 修改角色信息
		baseMapper.updateRole(role);
        // 删除角色与部门关联
        roleDeptMapper.deleteRoleDeptByRoleId(role.getRoleId());
        // 新增角色和部门信息（数据权限）
        return insertRoleDept(role);
	}

	@Override
	public String checkRoleNameUnique(SysRole role) {
		Integer roleId = null == role.getRoleId() ? -1 : role.getRoleId();
		SysRole info = baseMapper.checkRoleNameUnique( role.getRoleName() );
		if (null != info && info.getRoleId().longValue() != roleId.longValue()) {
			return UserState.NORMAL_USER.getCode() + "";
		}
		return UserState.LOCK_USER.getCode() + "";
	}

	@Override
	public String checkRoleKeyUnique(SysRole role) {
		Integer roleId = null == role.getRoleId() ? -1 : role.getRoleId();
		SysRole info = baseMapper.checkRoleKeyUnique( role.getRoleKey() );
		if (null != info && info.getRoleId().longValue() != roleId.longValue()) {
			return UserState.NORMAL_USER.getCode() + "";
		}
		return UserState.LOCK_USER.getCode() + "";
	}

	@Override
	public int countUserRoleByRoleId(Integer roleId) {
		 return userRoleMapper.countUserRoleByRoleId(roleId);
	}
	
	/**
     * .新增角色部门信息(数据权限)
     *
     * @param role 角色对象
     */
	public int insertRoleDept(SysRole role) {
		int rows = 1;
		// 新增角色与部门（数据权限）管理
		List<SysRoleDept> list = new ArrayList<SysRoleDept>();
		for (Integer deptId : role.getDeptIds()) {
			SysRoleDept rd = new SysRoleDept();
			rd.setRoleId( role.getRoleId() );
			rd.setDeptId( deptId );
			list.add( rd );
		}
		if (list.size() > 0) {
			rows = roleDeptMapper.batchRoleDept( list );
		}
		return rows;
	}


	/**
	 * .新增角色菜单信息
	 * 
	 * @param role
	 */
	public int insertRoleMenu(SysRole role) {
		int rows = 1;
		// 新增用户与角色管理
		List<SysRoleMenu> list = new ArrayList<SysRoleMenu>();
		for (Integer menuId : role.getMenuIds()) {
			SysRoleMenu rm = new SysRoleMenu();
			rm.setRoleId( role.getRoleId() );
			rm.setMenuId( menuId );
			list.add( rm );
		}
		if (list.size() > 0) {
			rows = roleMenuMapper.batchRoleMenu( list );
		}
		return rows;
	}

}
