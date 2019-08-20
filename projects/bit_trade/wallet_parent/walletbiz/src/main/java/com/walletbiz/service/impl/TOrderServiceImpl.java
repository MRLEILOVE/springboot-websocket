package com.walletbiz.service.impl;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.core.common.DTO.ReturnDTO;
import com.core.web.tool.WebUtil;
import com.walletbiz.enumer.AuditEnum;
import com.walletbiz.enumer.AuthStatusEnum;
import com.walletbiz.mapper.TOrderMapper;
import com.walletbiz.pojo.PersonalCard;
import com.walletbiz.pojo.TOrder;
import com.walletbiz.pojo.TWalletFundAccount;
import com.walletbiz.pojo.UserPayPassword;
import com.walletbiz.service.ITOrderService;
import com.walletbiz.service.ITwalletFundAccountService;
import com.walletbiz.service.PersonalCardService;
import com.walletbiz.service.UserPayPasswordService;
import com.walletbiz.tool.SnowFlake;
import com.walletbiz.vo.WithDrawParamVo;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2019-07-08
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements ITOrderService {

	@Autowired
	private UserPayPasswordService			userPayPasswordService;

	@Autowired
	private PersonalCardService				personalCardService;

	@Autowired
	private ITwalletFundAccountService		iTwalletFundAccountService;

	@Autowired
	private RedisTemplate<String, Object>	redisTemplate;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public ReturnDTO<String> confirmTibi(WithDrawParamVo withDrawParamVo) {
		Long user_id = WebUtil.getLoginUser().getUser_id();

		QueryWrapper<PersonalCard> entityWrapper = new QueryWrapper<>();
		entityWrapper.eq( "user_id", user_id );
		PersonalCard personalCard = personalCardService.getOne( entityWrapper );
		if (null == personalCard) {
			return ReturnDTO.error( "用户未实名认证" );
		}
		Integer authStauts = personalCard.getAuthStauts();
		if (!AuthStatusEnum.authType.SUCCESS.getCode().equals( authStauts )) {
			return ReturnDTO.error( "用户实名认证审核还未通过" );
		}

		QueryWrapper<TWalletFundAccount> entityWrapper1 = new QueryWrapper<>();
		entityWrapper1.eq( "user_id", user_id ).eq( "currency", withDrawParamVo.getToken() );
		TWalletFundAccount tWalletFundAccount = iTwalletFundAccountService.getOne( entityWrapper1 );
		int i = tWalletFundAccount.getTotal()
				.compareTo( new BigDecimal( withDrawParamVo.getAmount() ).add( new BigDecimal( withDrawParamVo.getFree() ) ) );
		if (i < 0) {
			return ReturnDTO.error( "提币超过用户当前余额" );
		}

		UserPayPassword userPayPassword = userPayPasswordService.selectByUserId( user_id );
		if (null == userPayPassword) {
			return ReturnDTO.error( "支付密码未设置" );
		}
		String idStr = Long.toString( user_id, 36 );// userId
		String md5Password = DigestUtils.md5Hex( withDrawParamVo.getPassword() + idStr );
		if (!userPayPassword.getPassword().equals( md5Password )) {
			return ReturnDTO.error( "密码不正确" );
		}

		Long id = (Long) redisTemplate.opsForValue().get( "user" + user_id );
		if (null != id && id.equals( user_id )) {
			return ReturnDTO.error( "3秒内不可重复提交订单" );
		}

		redisTemplate.opsForValue().set( "user" + user_id, user_id );
		redisTemplate.expire( "user" + user_id, 3, TimeUnit.SECONDS );
		SnowFlake snowFlake = new SnowFlake( 1, 1 );

		BigDecimal add = (new BigDecimal( withDrawParamVo.getAmount() ).add( new BigDecimal( withDrawParamVo.getFree() ) ));
		BigDecimal subtract = (new BigDecimal( withDrawParamVo.getAmount() ).add( new BigDecimal( withDrawParamVo.getFree() ) ));

		tWalletFundAccount.setTransferFrozen( tWalletFundAccount.getTransferFrozen().add( add ) );
		tWalletFundAccount.setTotal( tWalletFundAccount.getTotal().subtract( subtract ) );
		TOrder order = new TOrder();
		order.setAmount( new BigDecimal( withDrawParamVo.getAmount() ) );
		order.setOrderId( String.valueOf( snowFlake.nextId() ) );
		order.setReceiverAddress( withDrawParamVo.getReceiverAddress() );
		order.setToken( withDrawParamVo.getToken() );
		order.setUserId( user_id );
		order.setType( AuditEnum.orderType.NOTAUDITED.getCode() );
		order.setFee( new BigDecimal( withDrawParamVo.getFree() ) );

		boolean insert1 = iTwalletFundAccountService.updateById( tWalletFundAccount );
		boolean insert = this.save( order );
		if (insert && insert1) {
			return ReturnDTO.ok( "您的提币申请已经成功提交，请等待人工审核!" );
		}
		// TOrder order2= TOrder;
		return ReturnDTO.error( "充值出错" );
	}
}
