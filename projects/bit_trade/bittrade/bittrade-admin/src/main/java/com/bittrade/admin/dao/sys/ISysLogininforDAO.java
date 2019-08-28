package com.bittrade.admin.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.admin.model.domain.SysLogininfor;

/**
 * <p>
 * 系统访问记录 Mapper 接口
 * </p>
 *
 * @author who ?
 * @since 2018-11-03
 */
public interface ISysLogininforDAO extends BaseMapper<SysLogininfor> {

	/**
	 * 新增系统登录日志
	 * 
	 * @param logininfor
	 */
	public void insertLogininfor(SysLogininfor logininfor);

	/**
	 * 查询系统登录日志集合
	 * 
	 * @param logininfor
	 * @return 登录记录集合
	 */
	public List<SysLogininfor> selectLogininforList(Page<SysLogininfor> page, @Param("logininfor") SysLogininfor logininfor);

	/**
	 * 批量删除系统登录日志
	 * 
	 * @param ids
	 * @return
	 */
	public int deleteLogininforByIds(String[] ids);

	/**
	 * 清空系统登录日志
	 */
	public int cleanLogininfor();

}
