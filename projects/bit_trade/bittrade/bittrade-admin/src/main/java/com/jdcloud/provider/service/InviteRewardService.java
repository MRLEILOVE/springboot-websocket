package com.jdcloud.provider.service;

import com.baomidou.mybatisplus.service.IService;
import com.jdcloud.provider.dto.InviteRewardDto;
import com.jdcloud.provider.pojo.InviteReward;
import com.jdcloud.provider.pojo.SysUser;
import com.jdcloud.util.wrapper.Wrapper;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yongheng
 * @since 2019-01-09
 */
public interface InviteRewardService extends IService<InviteReward> {

	/**
	 * 邀请奖励管理<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/9 14:02
	 */
	Wrapper<String> edit(InviteRewardDto inviteRewardDto, SysUser user);
}
