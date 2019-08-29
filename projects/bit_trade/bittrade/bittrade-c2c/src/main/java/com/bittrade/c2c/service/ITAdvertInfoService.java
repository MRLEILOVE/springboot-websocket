package com.bittrade.c2c.service;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.__default.service.IDefaultTAdvertInfoService;
import com.bittrade.pojo.dto.TAdvertInfoDTO;
import com.bittrade.pojo.dto.TAdvertOrderDTO;
import com.bittrade.pojo.vo.AdvertUserVO;
import com.bittrade.pojo.vo.QueryAdvertVO;
import com.core.common.DTO.PageDTO;
import com.core.web.constant.entity.LoginUser;

/**
 * @author Administrator
 */
public interface ITAdvertInfoService extends IDefaultTAdvertInfoService {

	/**
	 * 发布广告
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/19 14:43
	 *
	 * @param user         {@link LoginUser}
	 * @param advertInfoDTO {@link AdvertInfoVO}
	 * @return result
	 */
	Boolean publishAdvert(LoginUser user, TAdvertInfoDTO advertInfoDTO);

	/**
	 * 获取广告列表
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/20 19:52
	 *
	 * @param pageDTO             : {@link PageDTO}
	 * @param queryAdvertVO : {@link QueryAdvertVO}
	 * @param loginUser        : {@link LoginUser}
	 * @return result
	 */
	PageDTO<TAdvertInfoDTO> listAdverts(PageDTO<TAdvertInfoDTO> pageDTO, QueryAdvertVO queryAdvertVO, LoginUser loginUser);

	/**
	 * 获取登录用户广告列表
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/20 19:52
	 * @param page : {@link Page}
	 * @param coinId
	 * @param loginUser : {@link LoginUser}
	 * @return  result
	 */
	IPage<AdvertUserVO> listAdvertsUsers(Page<AdvertUserVO> page, Long coinId, LoginUser loginUser);

	/**
	 * 暂停广告
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/21 17:07
	 * @param advertIds : 多个广告 ids
	 * @param loginUser : {@link LoginUser}
	 * @return  result
	 */
	Boolean suspendAdverts(List<Long> advertIds, LoginUser loginUser);

	/**
	 * 撤销广告
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/21 17:14
	 * @param advertId : 广告 id
	 * @param loginUser : {@link LoginUser}
	 * @return  result
	 */
	Boolean revokeAdverts(Long advertId, LoginUser loginUser);

	/**
	 * 获取广告详情
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/21 17:14
	 * @param advertId : 广告 id
	 * @return  result
	 */
	TAdvertInfoDTO getAdvertDetails(Long advertId);

	TAdvertOrderDTO placeAdvertOrder(Long advertId, BigDecimal amount, String payPassWord, LoginUser loginUser);
}
