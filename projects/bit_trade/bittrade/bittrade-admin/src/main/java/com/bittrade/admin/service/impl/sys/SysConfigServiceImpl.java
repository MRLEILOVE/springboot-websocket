package com.bittrade.admin.service.impl.sys;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bittrade.admin.constant.GlobalConstant.UserConstant;
import com.bittrade.admin.dao.sys.ISysConfigDAO;
import com.bittrade.admin.model.domain.SysConfig;
import com.bittrade.admin.service.sys.SysConfigService;
import com.bittrade.admin.util.ConvertUtil;

/**
 * <p>
 * 参数配置表 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2018-11-03
 */
@Service
public class SysConfigServiceImpl extends ServiceImpl<ISysConfigDAO, SysConfig> implements SysConfigService {

	@Override
	public SysConfig selectConfigById(Integer configId) {
		SysConfig config = new SysConfig();
		config.setConfigId( configId );
		return baseMapper.selectConfig( config );
	}

	@Override
	public String selectConfigByKey(String configKey) {
		SysConfig config = new SysConfig();
		config.setConfigKey( configKey );
		SysConfig retConfig = baseMapper.selectConfig( config );
		return null != retConfig ? retConfig.getConfigValue() : "";
	}

	@Override
	public List<SysConfig> selectConfigList(SysConfig config) {
		return baseMapper.selectConfigList( config );
	}

	@Override
	public int insertConfig(SysConfig config) {
		return baseMapper.insertConfig( config );
	}

	@Override
	public int updateConfig(SysConfig config) {
		return baseMapper.updateConfig( config );
	}

	@Override
	public int deleteConfigByIds(String ids) {
		return baseMapper.deleteConfigByIds( ConvertUtil.toStrArray( ids ) );
	}

	@Override
	public String checkConfigKeyUnique(SysConfig config) {
		Long configId = StringUtils.isEmpty( config.getConfigId() ) ? -1L : config.getConfigId();
		SysConfig info = baseMapper.checkConfigKeyUnique( config.getConfigKey() );
		if (null != info && info.getConfigId().longValue() != configId.longValue()) {
			return UserConstant.CONFIG_KEY_NOT_UNIQUE;
		}
		return UserConstant.CONFIG_KEY_UNIQUE;
	}

}
