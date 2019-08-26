package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.mapper.BetaTeamConfigMapper;
import com.jdcloud.provider.pojo.BetaTeamConfig;
import com.jdcloud.provider.service.BetaTeamConfigService;
import com.jdcloud.util.Convert;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ourblue
 * @since 2019-05-09
 */
@Service
public class BetaTeamConfigServiceImpl extends ServiceImpl<BetaTeamConfigMapper, BetaTeamConfig> implements BetaTeamConfigService {

    @Override
    public Wrapper<String> saveOrUpdate(BetaTeamConfig betaTeamConfig) {
        if (betaTeamConfig.getId() != null) {
            betaTeamConfig.setUpdateTime(new Date());
            updateById(betaTeamConfig);
            return WrapMapper.ok("修改成功");
        } else {
            betaTeamConfig.setCreateTime(new Date());
            insert(betaTeamConfig);
            return WrapMapper.ok("新增成功");
        }
    }

    @Override
    public int deleteByIds(String ids) {
        Integer[] configIds = ConvertUtil.toIntArray(ids);
        return baseMapper.deleteConfigByIds(configIds);
    }
}
