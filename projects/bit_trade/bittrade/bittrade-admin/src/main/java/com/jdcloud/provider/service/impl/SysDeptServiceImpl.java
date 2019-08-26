package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.enums.UserEnum.UserState;
import com.jdcloud.provider.mapper.SysDeptMapper;
import com.jdcloud.provider.pojo.SysDept;
import com.jdcloud.provider.pojo.SysRole;
import com.jdcloud.provider.service.SysDeptService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2018-11-03
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

	@Override
	public List<SysDept> selectDeptList(SysDept dept) {
		return baseMapper.selectDeptList( dept );
	}

	@Override
	public SysDept selectDeptById(Integer deptId) {
		return baseMapper.selectDeptById( deptId );
	}

	@Override
	public List<Map<String, Object>> selectDeptTree() {
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		List<SysDept> deptList = selectDeptList( new SysDept() );
		trees = getTrees( deptList, false, null );
		return trees;
	}

	@Override
	public List<Map<String, Object>> roleDeptTreeData(SysRole role) {
		Integer roleId = role.getRoleId();
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		List<SysDept> deptList = selectDeptList( new SysDept() );
		if (null != roleId) {
			List<String> roleDeptList = baseMapper.selectRoleDeptTree( roleId );
			trees = getTrees( deptList, true, roleDeptList );
		} else {
			trees = getTrees( deptList, false, null );
		}
		return trees;
	}
	
	@Override
	public int selectDeptCount(Integer parentId) {
		SysDept dept = new SysDept();
		dept.setParentId( parentId );
		return baseMapper.selectDeptCount( dept );
	}
	
	@Override
	public boolean checkDeptExistUser(Integer deptId) {
		int result = baseMapper.checkDeptExistUser( deptId );
		return result > 0 ? true : false;
	}

	/**
	 * .对象转部门树
	 *
	 * @param menuList 部门列表
	 * @param isCheck 是否需要选中
	 * @param roleDeptList   角色已存在菜单列表
	 * @return
	 */
	public List<Map<String, Object>> getTrees(List<SysDept> deptList, boolean isCheck, List<String> roleDeptList) {

		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		for (SysDept dept : deptList) {
			if ((UserState.NORMAL_USER.getCode() + "").equals( dept.getStatus() )) {
				Map<String, Object> deptMap = new HashMap<String, Object>();
				deptMap.put( "id", dept.getDeptId() );
				deptMap.put( "pId", dept.getParentId() );
				deptMap.put( "name", dept.getDeptName() );
				deptMap.put( "title", dept.getDeptName() );
				if (isCheck) {
					deptMap.put( "checked", roleDeptList.contains( dept.getDeptId() + dept.getDeptName() ) );
				} else {
					deptMap.put( "checked", false );
				}
				trees.add( deptMap );
			}
		}
		return trees;
	}

}