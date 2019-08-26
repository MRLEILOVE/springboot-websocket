package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.SysOperLog;

/**
 * <p>
 * 操作日志记录 服务类
 * </p>
 *
 * @author ourblue
 * @since 2018-11-03
 */
public interface SysOperLogService extends IService<SysOperLog> {

	/**
	 * 查询系统操作日志集合
	 * 
	 * @param operLog
	 * @return 操作日志集合
	 */
	public Page<SysOperLog> selectOperLogList(Page<SysOperLog> page, SysOperLog operLog);

	/**
	 * 批量删除系统操作日志
	 * 
	 * @param ids
	 * @return 结果
	 */
	public int deleteOperLogByIds(String ids);

	/**
	 * 查询操作日志详细
	 * 
	 * @param operId
	 * @return 操作日志对象
	 */
	public SysOperLog selectOperLogById(Integer operId);

	/**
	 * 清空操作日志
	 */
	public void cleanOperLog();

}
