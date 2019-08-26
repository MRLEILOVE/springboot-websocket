package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.BetaFeminaDto;
import com.jdcloud.provider.dto.BetaFeminaStatusDto;
import com.jdcloud.provider.pojo.BetaFemina;
import com.jdcloud.provider.pojo.vo.BetaFeminaVo;
import com.jdcloud.util.wrapper.Wrapper;

/**
 * <p>
 * 贝塔狗---母狗表 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-04-10
 */
public interface BetaFeminaService extends IService<BetaFemina> {

    Page<BetaFeminaVo> selectBetaFeminaPage(Page<BetaFeminaVo> page, BetaFeminaDto betaFeminaDto);

    /**
     * 新的母狗列表
     * @param page
     * @param betaFeminaDto
     * @return
     */
    Page<BetaFeminaVo> selectBetaFeminaPageNew(Page<BetaFeminaVo> page, BetaFeminaDto betaFeminaDto);

    BetaFeminaVo selectbetaFeminaById(Integer id);

    Wrapper<String> saveOrUpdate(BetaFemina betafemina);

    Wrapper<String> updateStatus(BetaFeminaStatusDto betaFeminaStatusDto);
}
