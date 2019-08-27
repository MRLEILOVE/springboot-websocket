package com.bittrade.admin.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.admin.model.domain.SysOperLog;

/**
 * <p>
 * 操作日志记录 Mapper 接口
 * </p>
 *
 * @author ourblue
 * @since 2018-11-03
 */
public interface ISysOperLogDAO extends BaseMapper<SysOperLog> {

	/**
	 * .新增操作日志
	 * 
	 * @param operLog
	 */
	public void insertOperlog(SysOperLog operLog);

	/**
	 * .查询系统操作日志集合
	 * 
	 * @param operLog
	 * @return 操作日志集合
	 */
	public List<SysOperLog> selectOperLogList(Page<SysOperLog> page, @Param("operLog") SysOperLog operLog);

	/**
	 * .批量删除系统操作日志
	 * 
	 * @param ids
	 * @return 结果
	 */
	public int deleteOperLogByIds(String[] ids);

	/**
	 * .查询操作日志详细
	 * 
	 * @param operId
	 * @return 操作日志对象
	 */
	public SysOperLog selectOperLogById(Integer operId);

	/**
	 * .清空操作日志
	 */
	public void cleanOperLog();

}
