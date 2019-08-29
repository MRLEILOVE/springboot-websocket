package com.bittrade.admin.service.impl.c2c;

import com.bittrade.admin.dao.c2c.AdvertInfoPrimaryDAO;
import com.bittrade.admin.model.domain.AdvertInfoPageDo;
import com.bittrade.admin.model.vo.PageParamVo;
import com.bittrade.pojo.dto.TAdvertInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bittrade.__default.service.impl.DefaultTAdvertInfoServiceImpl;
import com.bittrade.admin.dao.c2c.ITAdvertInfoDAO;
import com.bittrade.admin.model.vo.c2c.AdvertInfoScreeningParameterVo;
import com.bittrade.admin.service.c2c.ITAdvertInfoService;
import com.bittrade.pojo.model.TAdvertInfo;
import com.bittrade.pojo.vo.TAdvertInfoVO;
import com.core.common.DTO.PageDTO;

/**
 * @author Administrator
 */
@Service
public class TAdvertInfoServiceImpl extends DefaultTAdvertInfoServiceImpl<ITAdvertInfoDAO, TAdvertInfo, TAdvertInfoDTO, TAdvertInfoVO>
        implements ITAdvertInfoService {

    @Autowired
    private ITAdvertInfoDAO advertInfoMapper;

    @Autowired
    private AdvertInfoPrimaryDAO advertInfoPrimaryDAO;


    @Override
    public PageDTO<AdvertInfoPageDo> findList(PageParamVo pageParamVo, AdvertInfoScreeningParameterVo parameterVo) {

        return null;
    }
}
