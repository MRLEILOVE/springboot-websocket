package com.jdcloud.provider.web.product;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jdcloud.provider.dto.InviteRewardDto;
import com.jdcloud.provider.pojo.InviteReward;
import com.jdcloud.provider.service.InviteRewardService;
import com.jdcloud.provider.web.base.BaseController;
import com.jdcloud.util.wrapper.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 * 个人邀请奖励管理
 * <p>
 *
 * @author yongheng
 * @since 2019/1/9
 */
@Controller
@RequestMapping(value = "/product/inviteReward")
@Slf4j
public class InviteRewardController extends BaseController {

	private String				prefix	= "product/inviteReward";

	@Autowired
	private InviteRewardService	inviteRewardService;

	@RequiresPermissions("product:inviteReward:view")
	@GetMapping()
	public String acceptDealer(ModelMap mmap) {
		List<InviteReward> inviteRewards = inviteRewardService.selectList( new EntityWrapper<>() );
		mmap.put( "inviteRewards", inviteRewards );
		return prefix + "/inviteReward";
	}

	@RequiresPermissions("product:inviteReward:edit")
	@PostMapping(value = "/edit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
	public Wrapper<String> edit(InviteRewardDto inviteRewardDto) {
		return inviteRewardService.edit( inviteRewardDto, this.getUser() );
	}

}