package com.bittrade.admin.service.impl.sys;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.admin.dao.sys.ISysMenuDAO;
import com.bittrade.admin.dao.sys.ISysRoleMenuDAO;
import com.bittrade.admin.enums.UserEnum;
import com.bittrade.admin.service.sys.SysMenuService;
import com.bittrade.pojo.dto.SysMenuDTO;
import com.bittrade.pojo.dto.SysUserDTO;
import com.bittrade.pojo.model.SysMenu;
import com.bittrade.pojo.model.SysRole;
import com.core.common.constant.GlobalConstant.Number;
import com.core.common.constant.GlobalConstant.Sys;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author who ?
 * @since 2018-11-03
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<ISysMenuDAO, SysMenu> implements SysMenuService {
	
	@Autowired
    private ISysRoleMenuDAO roleMenuMapper;
	
	public static final String PREMISSION_STRING = "perms[\"{0}\"]";
	
	@Override
	public Set<String> selectPermsByUserId(Integer userId) {
		List<String> perms = baseMapper.selectPermsByUserId( userId );
		Set<String> permsSet = new HashSet<>();
		for (String perm : perms) {
			if (!StringUtils.isEmpty( perm )) {
				permsSet.addAll( Arrays.asList( perm.trim().split( "," ) ) );
			}
		}
		return permsSet;
	}

	@Override
	public List<SysMenuDTO> selectMenusByUser(SysUserDTO userDTO) {
		List<SysMenuDTO> meus = new LinkedList<SysMenuDTO>();
		if (userDTO.getLoginName().equals( Sys.ADMIN )) {
			meus = baseMapper.selectMenuNormalAll();
		} else {
			meus = baseMapper.selectMenusByUserId( userDTO.getUserId() );
		}
		return getChildPerms(meus, Number.ZERO_0);
	}

	@Override
	public List<SysMenu> selectMenuList(SysMenu menu) {
		return baseMapper.selectMenuList(menu);
	}

	@Override
	public List<SysMenu> selectMenuAll() {
		 return baseMapper.selectMenuAll();
	}

	@Override
	public List<Map<String, Object>> roleMenuTreeData(SysRole role) {
		Integer roleId = role.getRoleId();
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		List<SysMenu> menuList = baseMapper.selectMenuAll();
		if (null != roleId ) {
			List<String> roleMenuList = baseMapper.selectMenuTree( roleId );
			trees = getTrees( menuList, true, roleMenuList, true );
		} else {
			trees = getTrees( menuList, false, null, true );
		}
		return trees;
	}

	@Override
	public List<Map<String, Object>> menuTreeData() {
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
		List<SysMenu> menuList = baseMapper.selectMenuAll();
		trees = getTrees( menuList, false, null, false );
		return trees;
	}

	@Override
	public Map<String, String> selectPermsAll() {
		LinkedHashMap<String, String> section = new LinkedHashMap<>();
		List<SysMenu> permissions = baseMapper.selectMenuAll();
		if (!StringUtils.isEmpty( permissions )) {
			for (SysMenu menu : permissions) {
				section.put( menu.getUrl(), MessageFormat.format( PREMISSION_STRING, menu.getPerms() ) );
			}
		}
		return section;
	}

	@Override
	public int deleteMenuById(Integer menuId) {
		return baseMapper.deleteMenuById( menuId );
	}

	@Override
	public SysMenu selectMenuById(Integer menuId) {
		return baseMapper.selectMenuById(menuId);
	}

	@Override
	public int selectCountMenuByParentId(Integer parentId) {
		return baseMapper.selectCountMenuByParentId(parentId);
	}

	@Override
	public int selectCountRoleMenuByMenuId(Integer menuId) {
		return roleMenuMapper.selectCountRoleMenuByMenuId(menuId);
	}

	@Override
	public int insertMenu(SysMenu menu) {
		return baseMapper.insertMenu(menu);
	}

	@Override
	public int updateMenu(SysMenu menu) {
		return baseMapper.updateMenu(menu);
	}

	@Override
	public String checkMenuNameUnique(SysMenu menu) {
		Integer menuId = StringUtils.isEmpty( menu.getMenuId() ) ? -1 : menu.getMenuId();
		SysMenu info = baseMapper.checkMenuNameUnique( menu.getMenuName(), menu.getParentId() );
		if (null != info  && info.getMenuId().longValue() != menuId.longValue()) {
			return UserEnum.UserState.NORMAL_USER.getCode()+"";
		}
		return UserEnum.UserState.LOCK_USER.getCode()+"";
	}
	
	/**
	 * .根据父节点的ID获取所有子节点
	 * 
	 * @param list 分类表
	 * @param typeId  传入的父节点ID
	 * @return String
	 */
	public List<SysMenuDTO> getChildPerms(List<SysMenuDTO> list, int parentId) {
		List<SysMenuDTO> returnList = new ArrayList<SysMenuDTO>();
		for (Iterator<SysMenuDTO> iterator = list.iterator(); iterator.hasNext();) {
			SysMenuDTO t = (SysMenuDTO) iterator.next();
			// 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
			if (t.getParentId() == parentId) {
				recursionFn( list, t );
				returnList.add( t );
			}
		}
		return returnList;
	}

	/**
	 * .递归列表
	 * 
	 * @param list
	 * @param SysMenu
	 */
	private void recursionFn(List<SysMenuDTO> list, SysMenuDTO t) {
		// 得到子节点列表
		List<SysMenuDTO> childList = getChildList( list, t );
		t.setChildren( childList );
		for (SysMenuDTO tChild : childList) {
			if (hasChild( list, tChild )) {
				// 判断是否有子节点
				//Iterator<SysMenu> it = childList.iterator();
				//while (it.hasNext()) {
				//	SysMenu n = (SysMenu) it.next();
					recursionFn( list, tChild );
				//}
			}
		}
	}

	/**
	 * .得到子节点列表
	 */
	private List<SysMenuDTO> getChildList(List<SysMenuDTO> list, SysMenuDTO t) {
		List<SysMenuDTO> tlist = new ArrayList<SysMenuDTO>();
		Iterator<SysMenuDTO> it = list.iterator();
		while (it.hasNext()) {
			SysMenuDTO n = (SysMenuDTO) it.next();
			if (n.getParentId().longValue() == t.getMenuId().longValue()) {
				tlist.add( n );
			}
		}
		return tlist;
	}

	/**
	 * .判断是否有子节点
	 */
	private boolean hasChild(List<SysMenuDTO> list, SysMenuDTO t) {
		return getChildList( list, t ).size() > 0 ? true : false;
	}
	
	 /**
     * 对象转菜单树
     * 
     * @param menuList 菜单列表
     * @param isCheck 是否需要选中
     * @param roleMenuList 角色已存在菜单列表
     * @param permsFlag 是否需要显示权限标识
     * @return
     */
    public List<Map<String, Object>> getTrees(List<SysMenu> menuList, boolean isCheck, List<String> roleMenuList,
            boolean permsFlag)
    {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (SysMenu menu : menuList)
        {
            Map<String, Object> deptMap = new HashMap<String, Object>();
            deptMap.put("id", menu.getMenuId());
            deptMap.put("pId", menu.getParentId());
            deptMap.put("name", transMenuName(menu, roleMenuList, permsFlag));
            deptMap.put("title", menu.getMenuName());
            if (isCheck)
            {
                deptMap.put("checked", roleMenuList.contains(menu.getMenuId() + menu.getPerms()));
            }
            else
            {
                deptMap.put("checked", false);
            }
            trees.add(deptMap);
        }
        return trees;
    }
    
    public String transMenuName(SysMenu menu, List<String> roleMenuList, boolean permsFlag)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(menu.getMenuName());
        if (permsFlag)
        {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + menu.getPerms() + "</font>");
        }
        return sb.toString();
    }

}