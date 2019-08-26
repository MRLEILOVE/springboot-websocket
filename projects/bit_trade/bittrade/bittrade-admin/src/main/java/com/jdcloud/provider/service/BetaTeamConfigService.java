package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.pojo.BetaTeamConfig;
import com.jdcloud.util.wrapper.Wrapper;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-05-09
 */
public interface BetaTeamConfigService extends IService<BetaTeamConfig> {

    /** 
     * @Description: 增加/编辑
     * @Author: senghor
     * @Date: 2019/5/9 0009 22:10
     */
    Wrapper<String> saveOrUpdate(BetaTeamConfig betaTeamConfig);

    /** 
     * @Description: 批量删除
     * @Author: senghor
     * @Date: 2019/5/9 0009 22:13
     */
    
    int deleteByIds(String ids);
}
