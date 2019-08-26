package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.SysLogininfor;

/**
 * <p>
 * 系统访问记录 服务类
 * </p>
 *
 * @author ourblue
 * @since 2018-11-03
 */
public interface SysLogininforService extends IService<SysLogininfor> {

	/**
	 * .新增系统登录日志
	 * 
	 * @param logininfor
	 */
	public void insertLogininfor(SysLogininfor logininfor);

	/**
	 * .查询系统登录日志集合
	 * 
	 * @param logininfor
	 * @return 登录记录集合
	 */
	public Page<SysLogininfor> selectLogininforList(Page<SysLogininfor> page, SysLogininfor logininfor);

	/**
	 * .批量删除系统登录日志
	 * 
	 * @param ids
	 * @return
	 */
	public int deleteLogininforByIds(String ids);

	/**
	 * .清空系统登录日志
	 */
	public void cleanLogininfor();

}
