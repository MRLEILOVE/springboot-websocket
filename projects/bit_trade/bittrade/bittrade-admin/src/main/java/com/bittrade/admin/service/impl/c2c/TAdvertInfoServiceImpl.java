package com.bittrade.admin.service.impl.c2c;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.bittrade.__default.service.impl.DefaultTAdvertInfoServiceImpl;
import com.bittrade.admin.dao.c2c.ITAdvertInfoDAO;
import com.bittrade.admin.service.c2c.ITAdvertInfoService;
import com.bittrade.admin.vo.c2c.AdvertInfoScreeningParameterVo;
import com.bittrade.pojo.dto.TAdvertInfoDTO;
import com.bittrade.pojo.model.TAdvertInfo;
import com.bittrade.pojo.vo.TAdvertInfoVO;
import com.core.common.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
@Service
public class TAdvertInfoServiceImpl extends DefaultTAdvertInfoServiceImpl<ITAdvertInfoDAO, TAdvertInfo, TAdvertInfoDTO, TAdvertInfoVO>
		implements ITAdvertInfoService {

	@Autowired
	private ITAdvertInfoDAO		advertInfoMapper;

	@Override
	public PageDTO<TAdvertInfo> findList(AdvertInfoScreeningParameterVo parameterVo, PageDTO<TAdvertInfo> pageDTO) {
		Map<?, ?> condition = (Map<?, ?>) JSON.toJSON( parameterVo );
		advertInfoMapper.findList( condition );
		return null;
	}

}
