package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.BetaAdoptionLogDto;
import com.jdcloud.provider.pojo.BetaAdoptionLog;
import com.jdcloud.provider.pojo.vo.BetaAdoptionLogVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-05-15
 */
public interface BetaAdoptionLogService extends IService<BetaAdoptionLog> {

    Page<BetaAdoptionLogVo> selectPageList(Page<BetaAdoptionLogVo> page, BetaAdoptionLogDto betaAdoptionLogDto);
}
