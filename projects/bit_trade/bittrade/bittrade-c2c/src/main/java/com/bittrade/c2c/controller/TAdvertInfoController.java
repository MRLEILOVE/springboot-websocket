package com.bittrade.c2c.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bittrade.pojo.dto.TAdvertOrderDTO;
import com.bittrade.pojo.model.TAdvertOrder;
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
import com.bittrade.pojo.vo.TAdvertInfoVO;
import com.core.common.DTO.ReturnDTO;
import com.core.common.annotation.ALoginUser;
import com.core.framework.base.controller.BaseController;
import com.core.web.constant.entity.LoginUser;

import javax.annotation.Resource;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Administrator
 *
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = { "/tAdvertInfo" }, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TAdvertInfoController extends BaseController<TAdvertInfo, TAdvertInfoDTO, TAdvertInfoVO, ITAdvertInfoService> {

	@Resource
	private ITAdvertInfoService itAdvertInfoService;

	/**
	 * 发布广告
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/19 14:43
	 * @param user {@link LoginUser}
	 * @param tAdvertInfoDTO {@link TAdvertInfoDTO}
	 * @return result
	 */
	@PostMapping("/action/publish_advert")
	public ReturnDTO<Object> publishAdvert(@ALoginUser LoginUser user, @Validated TAdvertInfoDTO tAdvertInfoDTO) {
		// 最大限额需大于最小限额
		if (tAdvertInfoDTO.getMaxLimit().compareTo(tAdvertInfoDTO.getMinLimit()) < 0) {
			return ReturnDTO.error("單筆最大限額需大於最小限額");
		}
		boolean result = itAdvertInfoService.publishAdvert(user, tAdvertInfoDTO);
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
	public ReturnDTO<Object> listAdverts(Page<TAdvertInfo> page, @Validated QueryAdvertVO queryAdvertVO, @ALoginUser LoginUser loginUser) {
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
	public ReturnDTO<Object> suspendAdverts(@RequestParam("advertIds[]") List<Long> advertIds,
	                                        @ALoginUser LoginUser loginUser) {
		if (CollectionUtils.isEmpty(advertIds)) {
			return ReturnDTO.error("advertIds cannot be null");
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
		TAdvertInfoDTO info = itAdvertInfoService.getAdvertDetails(advertId);
		return ReturnDTO.ok(info);
	}

	/**
	 * 购买、出售操作（下购买、出售订单）
	 * <br/>
	 * create by: leigq
	 * <br/>
	 * create time: 2019/8/22 14:35
	 * @param advertId : 广告id
	 * @param amount : 数量
	 * @param payPassWord : 资金密码
	 * @param loginUser : {@link LoginUser}
	 * @return result
	 */
	@PostMapping("/action/place_advert_order/{advert_id}")
	public ReturnDTO<Object> placeAdvertOrder(@PathVariable("advert_id") Long advertId,
	                                          @NotNull(message = "數量必填") @DecimalMin(value = "0", message = "數量需大於0", inclusive = false) BigDecimal amount,
	                                          String payPassWord,
	                                          @ALoginUser LoginUser loginUser) {
		TAdvertOrderDTO advertOrder = itAdvertInfoService.placeAdvertOrder(advertId, amount, payPassWord, loginUser);
		return Objects.nonNull(advertOrder) ? ReturnDTO.ok(advertOrder) : ReturnDTO.error("下單失敗");
	}

}
