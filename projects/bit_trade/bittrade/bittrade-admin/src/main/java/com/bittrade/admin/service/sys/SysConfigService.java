package com.bittrade.admin.service.sys;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bittrade.admin.model.domain.SysConfig;

/**
 * <p>
 * 参数配置表 服务类
 * </p>
 *
 * @author who ?
 * @since 2018-11-03
 */
public interface SysConfigService extends IService<SysConfig> {

	/**
	 * .查询参数配置信息
	 * 
	 * @param configId
	 * @return 参数配置信息
	 */
	public SysConfig selectConfigById(Integer configId);

	/**
	 * .根据键名查询参数配置信息
	 * 
	 * @param configKey
	 * @return 参数键值
	 */
	public String selectConfigByKey(String configKey);

	/**
	 * .查询参数配置列表
	 * 
	 * @param config
	 * @return 参数配置集合
	 */
	public List<SysConfig> selectConfigList(SysConfig config);

	/**
	 * .新增参数配置
	 * 
	 * @param config
	 * @return 结果
	 */
	public int insertConfig(SysConfig config);

	/**
	 * .修改参数配置
	 * 
	 * @param config
	 * @return 结果
	 */
	public int updateConfig(SysConfig config);

	/**
	 * .批量删除参数配置信息
	 * 
	 * @param ids
	 * @return 结果
	 */
	public int deleteConfigByIds(String ids);

	/**
	 * .校验参数键名是否唯一
	 * 
	 * @param config
	 * @return 结果
	 */
	public String checkConfigKeyUnique(SysConfig config);

}
