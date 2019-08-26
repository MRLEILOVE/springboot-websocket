package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.provider.dto.InviteRewardDto;
import com.jdcloud.provider.mapper.InviteRewardMapper;
import com.jdcloud.provider.pojo.InviteReward;
import com.jdcloud.provider.pojo.SysUser;
import com.jdcloud.provider.service.InviteRewardService;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yongheng
 * @since 2019-01-09
 */
@Service
public class InviteRewardServiceImpl extends ServiceImpl<InviteRewardMapper, InviteReward> implements InviteRewardService {

	/**
	 * 邀请奖励管理<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/9 14:02
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Wrapper<String> edit(InviteRewardDto inviteRewardDto, SysUser user) {
		String[] ids = inviteRewardDto.getIdsUnit().split( "," );
		String[] secondRewardPercents = inviteRewardDto.getSecondRewardPercentsUnit().split( "," );
		String[] thirdRewardPercents = inviteRewardDto.getThirdRewardPercentsUnit().split( "," );

		List<InviteReward> list = new ArrayList<>();
		for (int i = 0; i < ids.length; i++) {
			InviteReward inviteReward = new InviteReward();
			inviteReward.setId( Long.valueOf( ids[ i ] ) );
			inviteReward.setSecondRewardPercent( new BigDecimal( secondRewardPercents[ i ] ) );
			inviteReward.setThirdRewardPercent( new BigDecimal( thirdRewardPercents[ i ] ) );
			inviteReward.setUpdater( user.getUserName() );
			inviteReward.setUpdateTime( new Date() );
			list.add( inviteReward );
		}

		this.updateBatchById( list );
		return WrapMapper.ok();
	}
}
