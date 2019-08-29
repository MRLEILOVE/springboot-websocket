package com.bittrade.c2c.service;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.__default.service.IDefaultTAdvertInfoService;
import com.bittrade.pojo.model.TAdvertInfo;
import com.bittrade.pojo.model.TAdvertOrder;
//git.dev.tencent.com/ha_sir/git.git
import com.bittrade.pojo.vo.AdvertUserVO;
import com.bittrade.pojo.vo.PublishAdvertVO;
import com.bittrade.pojo.vo.QueryAdvertVO;
//git.dev.tencent.com/ha_sir/git.git
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
	 * @param publishAdvertVO {@link PublishAdvertVO}
	 * @return result
	 */
	Boolean publishAdvert(LoginUser user, PublishAdvertVO publishAdvertVO);

	/**
	 * 获取广告列表
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/20 19:52
	 *
	 * @param page             : {@link Page}
	 * @param queryAdvertVO : {@link QueryAdvertVO}
	 * @param loginUser        : {@link LoginUser}
	 * @return result
	 */
	IPage<TAdvertInfo> listAdverts(Page<TAdvertInfo> page, QueryAdvertVO queryAdvertVO, LoginUser loginUser);

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
	TAdvertInfo getAdvertDetails(Long advertId);

	TAdvertOrder placeAdvertOrder(Long advertId, BigDecimal amount, String payPassWord, LoginUser loginUser);
}
