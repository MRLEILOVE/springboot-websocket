package com.bittrade.c2c.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.pojo.vo.AdvertUserVO;
import com.bittrade.pojo.vo.QueryAdvertVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.bittrade.c2c.service.ITAdvertInfoService;
import com.bittrade.pojo.dto.TAdvertInfoDTO;
import com.bittrade.pojo.model.TAdvertInfo;
import com.bittrade.pojo.vo.AdvertInfoVO;
import com.bittrade.pojo.vo.TAdvertInfoVO;
import com.core.common.DTO.ReturnDTO;
import com.core.common.annotation.ALoginUser;
import com.core.framework.base.controller.BaseController;
import com.core.web.constant.entity.LoginUser;

import java.util.List;
import java.util.Objects;

/**
 * 
 * @author Administrator
 *
 */
@Slf4j
@RestController
@RequestMapping(value = { "/tAdvertInfo" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TAdvertInfoController extends BaseController<TAdvertInfo, TAdvertInfoDTO, TAdvertInfoVO, ITAdvertInfoService> {

	@Autowired
	private ITAdvertInfoService itAdvertInfoService;

	/**
	 * 发布广告
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/19 14:43
	 * @param user {@link LoginUser}
	 * @param advertInfoVO {@link AdvertInfoVO}
	 * @return result
	 */
	@PostMapping("/action/publish_advert")
	public ReturnDTO<Object> publishAdvert(@ALoginUser LoginUser user, @Validated AdvertInfoVO advertInfoVO) {
		// 最大限额需大于最小限额
		if (advertInfoVO.getMaxLimit().compareTo(advertInfoVO.getMinLimit()) < 0) {
			return ReturnDTO.error("單筆最大限額需大於最小限額");
		}
		boolean result = itAdvertInfoService.publishAdvert(user, advertInfoVO);
		return result ? ReturnDTO.ok("發布成功") : ReturnDTO.error("發布失敗");
	}

	/**
	 * 获取广告列表
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/20 19:52
	 * @param page : {@link Page}
	 * @param queryAdvertVO : {@link QueryAdvertVO}
	 * @param loginUser : {@link LoginUser}
	 * @return  result
	 */
	@GetMapping("/adverts")
	public ReturnDTO<Object> listAdverts(Page<TAdvertInfo> page, QueryAdvertVO queryAdvertVO, @ALoginUser LoginUser loginUser) {
		IPage<TAdvertInfo> adverts = itAdvertInfoService.listAdverts(page, queryAdvertVO, loginUser);
		return ReturnDTO.ok(adverts);
	}

	/**
	 * 获取登录用户广告列表
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/20 19:52
	 * @param coinId : 法币 id
	 * @param page : {@link Page}
	 * @param loginUser : {@link LoginUser}
	 * @return  result
	 */
	@GetMapping("/adverts/users/{coin_id}")
	public ReturnDTO<Object> listAdvertsUsers(Page<AdvertUserVO> page, @PathVariable("coin_id") Long coinId, @ALoginUser LoginUser loginUser) {
		IPage<AdvertUserVO> adverts = itAdvertInfoService.listAdvertsUsers(page, coinId, loginUser);
		return ReturnDTO.ok(adverts);
	}

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
	@PostMapping("/action/suspend")
	public ReturnDTO<Object> suspendAdverts(@RequestParam("advertIds[]") List<Long> advertIds, @ALoginUser LoginUser loginUser) {
		if (CollectionUtils.isEmpty(advertIds)) {
			return ReturnDTO.error("暫停失敗");
		}
		boolean result = itAdvertInfoService.suspendAdverts(advertIds, loginUser);
		return result ? ReturnDTO.ok("暫停成功") : ReturnDTO.error("暫停失敗");
	}


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
	@PostMapping("/action/revoke/{advert_id}")
	public ReturnDTO<Object> revokeAdverts(@PathVariable("advert_id") Long advertId, @ALoginUser LoginUser loginUser){
		if (Objects.isNull(advertId)) {
			return ReturnDTO.error("撤銷失敗");
		}
		Boolean result = itAdvertInfoService.revokeAdverts(advertId, loginUser);
		return result ? ReturnDTO.ok("撤銷成功") : ReturnDTO.error("撤銷失敗");
	}

	/**
	 * 获取广告详情
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/21 17:14
	 * @param advertId : 广告 id
	 * @return  result
	 */
	@GetMapping("/adverts/details/{advert_id}")
	public ReturnDTO<Object> getAdvertDetails(@PathVariable("advert_id") Long advertId) {
		TAdvertInfo info = itAdvertInfoService.getAdvertDetails(advertId);
		return ReturnDTO.ok(info);
	}

}
