package com.bittrade.admin.service.c2c;

import com.bittrade.__default.service.IDefaultTAdvertInfoService;
import com.bittrade.admin.model.vo.c2c.AdvertInfoScreeningParameterVo;
import com.bittrade.pojo.dto.TAdvertInfoDTO;
import com.bittrade.pojo.model.TAdvertInfo;
import com.bittrade.pojo.vo.TAdvertInfoVO;
import com.core.common.DTO.PageDTO;

/**
 * 
 * @author Administrator
 *
 */
public interface ITAdvertInfoService extends IDefaultTAdvertInfoService<TAdvertInfo, TAdvertInfoDTO, TAdvertInfoVO> {

	/**
	 * 按条件分页查询
	 *
	 * @param parameterVo
	 * @param pageDTO
	 * @return
	 */
	PageDTO<TAdvertInfo> findList(AdvertInfoScreeningParameterVo parameterVo, PageDTO<TAdvertInfo> pageDTO);

}
