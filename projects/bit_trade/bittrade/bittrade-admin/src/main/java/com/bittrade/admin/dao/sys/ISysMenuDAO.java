package com.bittrade.admin.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bittrade.admin.model.domain.SysMenu;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author who ?
 * @since 2018-11-03
 */
public interface ISysMenuDAO extends BaseMapper<SysMenu> {

	/**
	 * .根据用户ID查询权限
	 * 
	 * @param userId 用户ID
	 * @return 权限列表
	 */

	public List<String> selectPermsByUserId(Integer userId);

	/**
	 * .查询系统正常显示菜单（不含按钮）
	 * 
	 * @return 菜单列表
	 */
	public List<SysMenu> selectMenuNormalAll();

	/**
	 * .根据用户ID查询菜单
	 * 
	 * @param userId 用户ID
	 * @return 菜单列表
	 */
	public List<SysMenu> selectMenusByUserId(Integer userId);
	
	/**
     * 查询系统所有菜单（含按钮）
     * 
     * @return 菜单列表
     */
    public List<SysMenu> selectMenuAll();
    
    /**
     * 根据角色ID查询菜单
     * 
     * @param roleId 角色ID
     * @return 菜单列表
     */
    public List<String> selectMenuTree(Integer roleId);

    /**
     * 查询系统菜单列表
     * 
     * @param menu 菜单信息
     * @return 菜单列表
     */
    public List<SysMenu> selectMenuList(SysMenu menu);

    /**
     * 删除菜单管理信息
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    public int deleteMenuById(Integer menuId);

    /**
     * 根据菜单ID查询信息
     * 
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    public SysMenu selectMenuById(Integer menuId);

    /**
     * 查询菜单数量
     * 
     * @param parentId 菜单父ID
     * @return 结果
     */
    public int selectCountMenuByParentId(Integer parentId);

    /**
     * 新增菜单信息
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    public int insertMenu(SysMenu menu);

    /**
     * 修改菜单信息
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    public int updateMenu(SysMenu menu);

    /**
     * 校验菜单名称是否唯一
     * 
     * @param menuName 菜单名称
     * @param parentId 父菜单ID
     * @return 结果
     */
    public SysMenu checkMenuNameUnique(@Param("menuName") String menuName, @Param("parentId") Integer parentId);

}