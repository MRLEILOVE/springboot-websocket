package com.bittrade.c2c.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.__default.service.IDefaultTAdvertInfoService;
import com.bittrade.pojo.dto.TAdvertInfoDTO;
import com.bittrade.pojo.model.TAdvertInfo;
import com.bittrade.pojo.vo.AdvertInfoVO;
import com.bittrade.pojo.vo.QueryBuyAdvertVO;
import com.bittrade.pojo.vo.TAdvertInfoVO;
import com.core.common.annotation.ALoginUser;
import com.core.web.constant.entity.LoginUser;

/**
 * @author Administrator
 */
public interface ITAdvertInfoService extends IDefaultTAdvertInfoService<TAdvertInfo, TAdvertInfoDTO, TAdvertInfoVO> {

	/**
	 * 发布广告
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/19 14:43
	 *
	 * @param user         {@link LoginUser}
	 * @param advertInfoVO {@link AdvertInfoVO}
	 * @return result
	 */
	Boolean publishAdvert(LoginUser user, AdvertInfoVO advertInfoVO);

	/**
	 * 获取购买广告列表
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/20 19:52
	 *
	 * @param page             : {@link Page}
	 * @param queryBuyAdvertVO : {@link QueryBuyAdvertVO}
	 * @param loginUser        : {@link LoginUser}
	 * @return result
	 */
	IPage<TAdvertInfo> listBuyAdverts(Page<TAdvertInfo> page, QueryBuyAdvertVO queryBuyAdvertVO, LoginUser loginUser);
}
