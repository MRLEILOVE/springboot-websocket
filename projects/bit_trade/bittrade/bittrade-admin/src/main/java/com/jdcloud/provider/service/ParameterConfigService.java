package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.Config;
import com.jdcloud.provider.pojo.ParameterConfig;
import com.jdcloud.provider.pojo.SysUser;
import com.jdcloud.util.wrapper.Wrapper;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 参数配置表 服务类
 * </p>
 *
 * @author yongheng
 * @since 2018-10-25
 */
public interface ParameterConfigService extends IService<ParameterConfig> {

	String getValue(String key);

	ParameterConfig getConfig(String key);

	Integer getIntValue(String key);

	Long getLongValue(String key);

	BigDecimal getBigDecimalValue(String key);

	/**
	 * 根据keys查询参数配置表<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/29 15:35
	 */
	List<ParameterConfig> selectListByKeys(List<String> keys);

	/**
	 * 批量更新参数配置表 <br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/29 16:37
	 */
	void batchUpdate(List<ParameterConfig> list);

	/**
	 * 参数配置表列表<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/1 11:37
	 */
	Page<ParameterConfig> selectConfigList(Config config);

	/**
	 * 保存或更新<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/1 15:42
	 */
	Wrapper<String> saveOrUpdate(ParameterConfig parameterConfig, SysUser user);

	/**
	 * 删除<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/1 17:51
	 */
	Wrapper<String> deleteByIds(String ids);
}
