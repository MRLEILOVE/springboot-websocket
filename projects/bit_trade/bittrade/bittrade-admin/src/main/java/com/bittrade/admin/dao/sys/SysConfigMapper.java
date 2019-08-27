package com.bittrade.admin.dao.sys;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bittrade.admin.model.domain.SysConfig;

/**
 * <p>
 * 参数配置表 Mapper 接口
 * </p>
 *
 * @author ourblue
 * @since 2018-11-03
 */
public interface SysConfigMapper extends BaseMapper<SysConfig> {

	/**
	 * 查询参数配置信息
	 * 
	 * @return 参数配置信息
	 */
	public SysConfig selectConfig(SysConfig config);

	/**
	 * 查询参数配置列表
	 * 
	 * @param config
	 * @return 参数配置集合
	 */
	public List<SysConfig> selectConfigList(SysConfig config);

	/**
	 * 根据键名查询参数配置信息
	 * 
	 * @param configKey
	 * @return 参数配置信息
	 */
	public SysConfig checkConfigKeyUnique(String configKey);

	/**
	 * 新增参数配置
	 * 
	 * @param config
	 * @return 结果
	 */
	public int insertConfig(SysConfig config);

	/**
	 * 修改参数配置
	 * 
	 * @param config
	 * @return 结果
	 */
	public int updateConfig(SysConfig config);

	/**
	 * 批量删除参数配置
	 * 
	 * @param configIds
	 * @return 结果
	 */
	public int deleteConfigByIds(String[] configIds);

}