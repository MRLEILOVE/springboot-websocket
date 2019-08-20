package com.bittrade.c2c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bittrade.c2c.service.ITAdvertInfoService;
import com.bittrade.pojo.dto.TAdvertInfoDTO;
import com.bittrade.pojo.model.TAdvertInfo;
import com.bittrade.pojo.vo.AdvertInfoVO;
import com.bittrade.pojo.vo.TAdvertInfoVO;
import com.core.common.DTO.ReturnDTO;
import com.core.common.annotation.ALoginUser;
import com.core.framework.base.controller.BaseController;
import com.core.web.constant.entity.LoginUser;

/**
 * 
 * @author Administrator
 *
 */
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

}
