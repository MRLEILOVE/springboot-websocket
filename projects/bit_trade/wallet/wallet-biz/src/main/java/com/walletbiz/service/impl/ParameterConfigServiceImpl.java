package com.walletbiz.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.walletbiz.mapper.ParameterConfigMapper;
import com.walletbiz.pojo.ParameterConfig;
import com.walletbiz.service.ParameterConfigService;
import com.walletbiz.utils.RedisKeyUtil;

import lombok.extern.slf4j.Slf4j;

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
	@Autowired
	private RedisTemplate<String,Object> redisTemplate;

	/**
	 * 根据配置表的币种key获取汇率
	 * @param key 币种 -》参数配置表的key
	 * @return
	 */
	@Override
	public BigDecimal getRateByKey(String key) {
		BigDecimal rate = new BigDecimal("1");//给个默认汇率，防止数据库的值为空

		//先从缓存获取
		Object o = redisTemplate.opsForValue().get(RedisKeyUtil.PARAMETER_CONFIG_PREF +  key);
		if(o != null){
			rate = new BigDecimal(o.toString());
		}else{
			ParameterConfig config = new ParameterConfig();
			config.setDictionaryKey(key);
			QueryWrapper<ParameterConfig> qe = new QueryWrapper<>();
			qe.setEntity( config );

			ParameterConfig pc = parameterConfigMapper.selectOne(qe);
			if (null != pc && null != pc.getDictionaryValue()) {
				rate = new BigDecimal(pc.getDictionaryValue());
			}
		}
		return rate;
	}
}
