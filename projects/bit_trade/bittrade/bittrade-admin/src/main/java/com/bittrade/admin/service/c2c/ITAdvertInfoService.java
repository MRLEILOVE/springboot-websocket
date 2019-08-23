package com.bittrade.admin.service.c2c;

import com.bittrade.__default.service.IDefaultTAdvertInfoService;
import com.bittrade.admin.model.domain.AdvertInfoPageDo;
import com.bittrade.admin.model.vo.PageParamVo;
import com.bittrade.admin.model.vo.c2c.AdvertInfoScreeningParameterVo;
import com.bittrade.pojo.dto.TAdvertInfoDTO;
import com.bittrade.pojo.model.TAdvertInfo;
import com.bittrade.pojo.vo.TAdvertInfoVO;
import com.core.common.DTO.PageDTO;

/**
 * @author Administrator
 */
public interface ITAdvertInfoService extends IDefaultTAdvertInfoService<TAdvertInfo, TAdvertInfoDTO, TAdvertInfoVO> {

    /**
     * 获取页面显示的表单
     *
     * @param pageParamVo
     * @param parameterVo
     * @return
     */
    PageDTO<AdvertInfoPageDo> findList(PageParamVo pageParamVo, AdvertInfoScreeningParameterVo parameterVo);

}
