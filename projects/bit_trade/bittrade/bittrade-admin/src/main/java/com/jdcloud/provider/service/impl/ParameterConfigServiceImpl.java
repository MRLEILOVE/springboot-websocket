package com.jdcloud.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.enums.ParameterConfigEnum;
import com.jdcloud.core.redis.RedisKeyUtil;
import com.jdcloud.core.redis.RedisTemplate;
import com.jdcloud.provider.dto.Config;
import com.jdcloud.provider.mapper.ParameterConfigMapper;
import com.jdcloud.provider.model.service.BizCommonsFeignApi;
import com.jdcloud.provider.pojo.ParameterConfig;
import com.jdcloud.provider.pojo.SysUser;
import com.jdcloud.provider.service.ParameterConfigService;
import com.jdcloud.util.Convert;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 参数配置表 服务实现类
 * </p>
 *
 * @author yongheng
 * @since 2018-10-25
 */
@Service
@Slf4j
public class ParameterConfigServiceImpl extends ServiceImpl<ParameterConfigMapper, ParameterConfig> implements ParameterConfigService {

	@Resource
	private ParameterConfigMapper	parameterConfigMapper;

	@Resource
	private BizCommonsFeignApi		commonsFeignApi;

	@Autowired
	private RedisTemplate redisTemplate;

	public String getValue(String key) {
		ParameterConfig pc = getConfig( key );
		if (pc == null) {
			return null;
		}
		return pc.getDictionaryValue();
	}

	public ParameterConfig getConfig(String key) {
		ParameterConfig parameterConfig = new ParameterConfig();
		parameterConfig.setDictionaryKey( key );
		return parameterConfigMapper.selectOne( parameterConfig );
	}

	public Integer getIntValue(String key) {
		String value = getValue( key );
		if (value == null) {
			return null;
		}
		return Integer.parseInt( value );
	}

	public Long getLongValue(String key) {
		String value = getValue( key );
		if (value == null) {
			return null;
		}
		return Long.parseLong( value );
	}

	public BigDecimal getBigDecimalValue(String key) {
		String value = getValue( key );
		if (StringUtils.isNotBlank( value )) {
			return new BigDecimal( value );
		}
		return null;
	}

	/**
	 * 根据keys查询参数配置表<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/29 15:35
	 */
	@Override
	public List<ParameterConfig> selectListByKeys(List<String> keys) {
		return baseMapper.selectListByKeys( keys );
	}

	/**
	 * 批量更新参数配置表 <br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/29 16:38
	 */
	@Override
	public void batchUpdate(List<ParameterConfig> list) {
		baseMapper.batchUpdate( list );
	}

	/**
	 * 参数配置表列表<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/1 11:37
	 */
	@Override
	public Page<ParameterConfig> selectConfigList(Config config) {
		List<ParameterConfig> list = baseMapper.selectConfigList( config );
		Integer count = baseMapper.selectConfigCount( config );
		Page<ParameterConfig> page = new Page<>();
		page.setRecords( list );
		page.setSize( config.getSize() );
		page.setCurrent( config.getCurrent() );
		page.setTotal( count );
		return page;
	}

	/**
	 * 保存或更新<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/1 15:43
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Wrapper<String> saveOrUpdate(ParameterConfig parameterConfig, SysUser user) {

		parameterConfig.setUpdater( user.getUserName() );
		parameterConfig.setUpdateTime( new Date() );

		if (parameterConfig.getDictionaryId() == null) {
			parameterConfig.setCreater( user.getUserName() );
			parameterConfig.setCreateTime( new Date() );
		}

		boolean flag = this.insertOrUpdate( parameterConfig );
		if (!flag) {
			return WrapMapper.error( "保存失败！" );
		}
		// 缓存
		//String[] keys = { RedisKeyUtil.PARAMETER_CONFIG_PREF + parameterConfig.getDictionaryKey() };
		boolean bo = redisTemplate.set(RedisKeyUtil.PARAMETER_CONFIG_PREF + parameterConfig.getDictionaryKey(),parameterConfig.getDictionaryValue());
		if(!bo){
			return WrapMapper.error( "增加失败！" );
		}
		log.info("删除缓存后返回的参数："+bo);


		// 灰度发布特殊处理
		if (ParameterConfigEnum.GRAY_RELEASE.getKey().equals( parameterConfig.getDictionaryKey() )) {
			commonsFeignApi.setRedisKey( parameterConfig.getDictionaryKey(), parameterConfig.getDictionaryValue() );
		}

		return WrapMapper.ok();
	}

	/**
	 * 删除<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/1 17:52
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Wrapper<String> deleteByIds(String ids) {
		Long[] dictionaryIds = ConvertUtil.toLongArray( ids );
		List<ParameterConfig> list = selectBatchIds( Arrays.asList( dictionaryIds ) );
		// 删除数据
		Integer count = baseMapper.deleteBatchIds( Arrays.asList( dictionaryIds ) );
		if (count == 0) {
			return WrapMapper.error( "删除失败" );
		}

		// 删除缓存
		for (ParameterConfig parameterConfig : list) {
			redisTemplate.delete(RedisKeyUtil.PARAMETER_CONFIG_PREF + parameterConfig.getDictionaryKey());
		}
		return WrapMapper.ok( "删除成功" );
	}
}
