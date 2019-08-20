package com.bittrade.c2c.service;

import com.bittrade.__default.service.IDefaultTAdvertInfoService;
import com.bittrade.pojo.dto.TAdvertInfoDTO;
import com.bittrade.pojo.model.TAdvertInfo;
import com.bittrade.pojo.vo.AdvertInfoVO;
import com.bittrade.pojo.vo.TAdvertInfoVO;
import com.core.web.constant.entity.LoginUser;

/**
 * 
 * @author Administrator
 *
 */
public interface ITAdvertInfoService extends IDefaultTAdvertInfoService<TAdvertInfo, TAdvertInfoDTO, TAdvertInfoVO> {

	Boolean publishAdvert(LoginUser user, AdvertInfoVO advertInfoVO);

}
