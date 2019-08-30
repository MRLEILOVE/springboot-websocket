package com.bittrade.admin.service.impl.sys;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.__default.service.impl.DefaultSysRoleServiceImpl;
import com.bittrade.admin.dao.sys.ISysRoleDAO;
import com.bittrade.admin.dao.sys.ISysRoleDeptDAO;
import com.bittrade.admin.dao.sys.ISysRoleMenuDAO;
import com.bittrade.admin.dao.sys.ISysUserRoleDAO;
import com.bittrade.admin.enums.UserEnum.UserState;
import com.bittrade.admin.service.sys.SysRoleService;
import com.bittrade.pojo.dto.SysRoleDTO;
import com.bittrade.pojo.model.SysRole;
import com.bittrade.pojo.model.SysRoleDept;
import com.bittrade.pojo.model.SysRoleMenu;
import com.core.tool.BeanUtil;
import com.core.tool.ConvertUtil;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author who ?
 * @since 2018-11-03
 */
@Service
public class SysRoleServiceImpl 
	extends DefaultSysRoleServiceImpl<ISysRoleDAO> implements SysRoleService {

	@Autowired
	private ISysRoleMenuDAO roleMenuMapper;
	@Autowired
    private ISysUserRoleDAO userRoleMapper;
    @Autowired
    private ISysRoleDeptDAO roleDeptMapper;

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
	public List<SysRoleDTO> selectRolesByUserId(Integer userId) {
		List<SysRole> userRoles = baseMapper.selectRolesByUserId( userId );
		List<SysRoleDTO> roles = baseMapper.getsDTOBy( null );
		for (SysRoleDTO roleDTO : roles) {
			for (SysRole userRole : userRoles) {
				if (roleDTO.getRoleId().longValue() == userRole.getRoleId().longValue()) {
					roleDTO.setFlag( true );
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
	public int insertRole(SysRoleDTO roleDTO) {
		// 新增角色信息
		roleDTO.setRoleKey( UUID.randomUUID().toString() );
		SysRole role = new SysRole();
		BeanUtil.copyObj(roleDTO, role);
		baseMapper.insertRole( role );
		return insertRoleMenu( roleDTO );
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
	public int updateRole(SysRoleDTO roleDTO) {
		// 修改角色信息
		SysRole role = new SysRole();
		BeanUtil.copyObj(roleDTO, role);
		baseMapper.updateRole(role);
        // 删除角色与菜单关联
        roleMenuMapper.deleteRoleMenuByRoleId(roleDTO.getRoleId());
        return insertRoleMenu(roleDTO);
	}

	@Override
	public int updateRule(SysRoleDTO roleDTO) {
		 // 修改角色信息
		SysRole role = new SysRole();
		BeanUtil.copyObj(roleDTO, role);
		baseMapper.updateRole(role);
        // 删除角色与部门关联
        roleDeptMapper.deleteRoleDeptByRoleId(roleDTO.getRoleId());
        // 新增角色和部门信息（数据权限）
        return insertRoleDept(roleDTO);
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
     * @param roleDTO 角色对象
     */
	public int insertRoleDept(SysRoleDTO roleDTO) {
		int rows = 1;
		// 新增角色与部门（数据权限）管理
		List<SysRoleDept> list = new ArrayList<SysRoleDept>();
		for (Integer deptId : roleDTO.getDeptIds()) {
			SysRoleDept rd = new SysRoleDept();
			rd.setRoleId( roleDTO.getRoleId() );
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
	 * @param roleDTO
	 */
	public int insertRoleMenu(SysRoleDTO roleDTO) {
		int rows = 1;
		// 新增用户与角色管理
		List<SysRoleMenu> list = new ArrayList<SysRoleMenu>();
		for (Integer menuId : roleDTO.getMenuIds()) {
			SysRoleMenu rm = new SysRoleMenu();
			rm.setRoleId( roleDTO.getRoleId() );
			rm.setMenuId( menuId );
			list.add( rm );
		}
		if (list.size() > 0) {
			rows = roleMenuMapper.batchRoleMenu( list );
		}
		return rows;
	}

}
