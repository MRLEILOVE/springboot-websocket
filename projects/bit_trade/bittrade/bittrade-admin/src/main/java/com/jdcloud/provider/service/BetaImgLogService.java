package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.ImgLogDto;
import com.jdcloud.provider.pojo.BetaImgLog;
import com.jdcloud.provider.pojo.vo.BetaImgLogVo;

/**
 * <p>
 * 覆盖图片表 服务类
 * </p>
 *
 * @author ourblue
 * @since 2019-06-01
 */
public interface BetaImgLogService extends IService<BetaImgLog> {

    Page<BetaImgLogVo> selectImgLog(Page<BetaImgLogVo> page, ImgLogDto imgLogDto);

    int deleteByIds(String ids);

    BetaImgLogVo selectImgLogDetails(Integer id);
}
