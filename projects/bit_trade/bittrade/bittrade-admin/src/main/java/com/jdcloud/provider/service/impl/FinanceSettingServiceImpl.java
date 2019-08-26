package com.jdcloud.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jdcloud.base.constant.GlobalConstant;
import com.jdcloud.base.enums.ConstantEnum;
import com.jdcloud.base.enums.ParameterConfigEnum;
import com.jdcloud.core.redis.RedisKeyUtil;
import com.jdcloud.provider.dto.AmountSetting;
import com.jdcloud.provider.dto.ExchangeRate;
import com.jdcloud.provider.dto.RateSetting;
import com.jdcloud.provider.dto.TokenSetting;
import com.jdcloud.provider.model.service.BizCommonsFeignApi;
import com.jdcloud.provider.pojo.*;
import com.jdcloud.provider.service.*;
import com.jdcloud.util.GeneralMethodUtils;
import com.jdcloud.util.http.HttpClientResult;
import com.jdcloud.util.http.HttpClientUtils;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 〈一句话功能简述〉
 * <p>
 *
 * @author yongheng
 * @since 2018/11/29
 */
@Service
@Slf4j
public class FinanceSettingServiceImpl implements FinanceSettingService {

	@Autowired
	private ParameterConfigService	parameterConfigService;

	@Autowired
	private BizCommonsFeignApi		commonsFeignApi;

	@Autowired
	private RechargeOrderService	rechargeOrderService;

	@Autowired
	private UserAccountService		userAccountService;

	@Autowired
	private WithdrawOrderService	withdrawOrderService;

	/**
	 * 汇率设置<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/29 10:31
	 */
	@Override
	public RateSetting queryRateSetting() {
		RateSetting rateSetting = new RateSetting();
		try {
			// 获取蓝鲸汇率
			String url = parameterConfigService.getValue( ConstantEnum.LanjingConstant.EXCHANGE_RATE_URL.getCode() );
			HttpClientResult result = HttpClientUtils.doGet( url );
			if (Wrapper.SUCCESS_CODE != result.getCode()) {
				throw new RuntimeException( "获取汇率失败" );
			}
			ExchangeRate exchangeRateVo = JSON.parseObject( String.valueOf( result.getContent() ), ExchangeRate.class );
			if (GlobalConstant.Number.ZERO_0 != exchangeRateVo.getCode()) {
				throw new RuntimeException( "获取汇率失败" );
			}
			rateSetting.setRate( new BigDecimal( exchangeRateVo.getExchange_rate() ) );
		} catch (Exception e) {
			log.error( "获取蓝鲸汇率失败", e );
			rateSetting.setCode( GlobalConstant.Number.ZERO_0 + "" );
			rateSetting.setRate( BigDecimal.ZERO );
		}

		// 获取调整汇率
		BigDecimal exchangeRateAdjust = parameterConfigService.getBigDecimalValue( ParameterConfigEnum.EXCHANGE_RATE_ADJUST.getKey() );
		rateSetting.setExchangeRateAdjust( exchangeRateAdjust );
		return rateSetting;
	}

	/**
	 * 修改调整汇率<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/29 14:26
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Wrapper<String> rateSettingEdit(BigDecimal exchangeRateAdjust, SysUser user) {
		ParameterConfig parameterConfig = new ParameterConfig();
		parameterConfig.setDictionaryKey( ParameterConfigEnum.EXCHANGE_RATE_ADJUST.getKey() );
		parameterConfig = parameterConfigService.selectOne( new EntityWrapper<>( parameterConfig ) );
		parameterConfig.setDictionaryValue( exchangeRateAdjust.toString() );
		parameterConfig.setUpdater( user.getUserName() );
		parameterConfig.setUpdateTime( new Date() );
		parameterConfigService.updateById( parameterConfig );

		// 删除缓存
		String[] keys = { RedisKeyUtil.PARAMETER_CONFIG_PREF + ParameterConfigEnum.EXCHANGE_RATE_ADJUST.getKey() };
		Wrapper<String> wrapper = commonsFeignApi.deleteRedisKeys( JSON.toJSONString( keys ) );
		if (wrapper == null) {
			throw new RuntimeException( "删除缓存失败！" );
		}

		return WrapMapper.ok();
	}

	/**
	 * 出入金限制设置<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/29 15:22
	 */
	@Override
	public AmountSetting queryAmountSetting() {
		AmountSetting amountSetting = new AmountSetting();
		List<String> keys = new ArrayList<>();
		keys.add( ParameterConfigEnum.MIN_WITHDRAW_LIMIT.getKey() );
		keys.add( ParameterConfigEnum.MAX_WITHDRAW_LIMIT.getKey() );
		keys.add( ParameterConfigEnum.MIN_RECHARGE_LIMIT.getKey() );
		keys.add( ParameterConfigEnum.MAX_RECHARGE_LIMIT.getKey() );

		List<ParameterConfig> list = parameterConfigService.selectListByKeys( keys );
		for (ParameterConfig parameterConfig : list) {
			switch (parameterConfig.getDictionaryKey()) {
			case "minWithdrawLimit":
				amountSetting.setMinWithdrawLimit( new BigDecimal( parameterConfig.getDictionaryValue() ) );
				break;
			case "maxWithdrawLimit":
				amountSetting.setMaxWithdrawLimit( new BigDecimal( parameterConfig.getDictionaryValue() ) );
				break;
			case "minRechargeLimit":
				amountSetting.setMinRechargeLimit( new BigDecimal( parameterConfig.getDictionaryValue() ) );
				break;
			case "maxRechargeLimit":
				amountSetting.setMaxRechargeLimit( new BigDecimal( parameterConfig.getDictionaryValue() ) );
				break;
			}
		}

		return amountSetting;
	}

	/**
	 * 出入金限制修改 <br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/29 16:14
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Wrapper<String> amountSettingEdit(AmountSetting amountSetting, SysUser user) {
		List<ParameterConfig> list = new ArrayList<>();
		list.add( new ParameterConfig( ParameterConfigEnum.MIN_WITHDRAW_LIMIT.getKey(), amountSetting.getMinWithdrawLimit().toString(),
				user.getUserName() ) );
		list.add( new ParameterConfig( ParameterConfigEnum.MAX_WITHDRAW_LIMIT.getKey(), amountSetting.getMaxWithdrawLimit().toString(),
				user.getUserName() ) );
		list.add( new ParameterConfig( ParameterConfigEnum.MIN_RECHARGE_LIMIT.getKey(), amountSetting.getMinRechargeLimit().toString(),
				user.getUserName() ) );
		list.add( new ParameterConfig( ParameterConfigEnum.MAX_RECHARGE_LIMIT.getKey(), amountSetting.getMaxRechargeLimit().toString(),
				user.getUserName() ) );
		// 批量更新
		parameterConfigService.batchUpdate( list );

		// 删除redisKey
		String[] keys = new String[4];
		keys[ 0 ] = RedisKeyUtil.PARAMETER_CONFIG_PREF + ParameterConfigEnum.MIN_WITHDRAW_LIMIT.getKey();
		keys[ 1 ] = RedisKeyUtil.PARAMETER_CONFIG_PREF + ParameterConfigEnum.MAX_WITHDRAW_LIMIT.getKey();
		keys[ 2 ] = RedisKeyUtil.PARAMETER_CONFIG_PREF + ParameterConfigEnum.MIN_RECHARGE_LIMIT.getKey();
		keys[ 3 ] = RedisKeyUtil.PARAMETER_CONFIG_PREF + ParameterConfigEnum.MAX_RECHARGE_LIMIT.getKey();
		Wrapper<String> wrapper = commonsFeignApi.deleteRedisKeys( JSON.toJSONString( keys ) );
		if (wrapper == null) {
			throw new RuntimeException( "删除缓存失败！" );
		}
		return WrapMapper.ok();
	}

	/**
	 * 人工充值申请 <br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/29 20:05
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Wrapper<String> rechargeApply(RechargeOrder rechargeOrder, SysUser user) {

		Long userId = rechargeOrder.getUserId();
		// 校验用户
		UserAccount userAccount = new UserAccount();
		userAccount.setUserId( userId );
		UserAccount account = userAccountService.selectOne( new EntityWrapper<>( userAccount ) );
		if (account == null) {
			return WrapMapper.error( "账户不存在" );
		}

		// 获取汇率
		BigDecimal exchangeRate = this.getExchangeRate();

		// 生成充值订单
		RechargeOrder order = new RechargeOrder();
		order.setOrderNo( "CZ" + GeneralMethodUtils.generatorOrderId( userId.toString() ) );
		order.setUserId( userId );
		order.setOrderAmount( rechargeOrder.getExchangeAmount().multiply( exchangeRate ).setScale( 2, BigDecimal.ROUND_DOWN ) );
		order.setExchangeRate( exchangeRate );
		order.setExchangeAmount( rechargeOrder.getExchangeAmount() );
		order.setCreateTime( new Date() );
		order.setStatus( Integer.valueOf( ConstantEnum.RechargeConstant.STATUS_AUDITING.getCode() ) );
		order.setType( Integer.valueOf( ConstantEnum.RechargeConstant.TYPE_MANUAL.getCode() ) );
		if (rechargeOrder.getOrderCurrency().equals( GlobalConstant.Number.TWO_2 + "" )) {
			order.setOrderAmount( null );
			order.setType( Integer.valueOf( ConstantEnum.RechargeConstant.TYPE_MANUAL_TOKEN.getCode() ) );
		}
		log.info( "com.jdcloud.provider.service.impl.FinanceSettingServiceImpl.rechargeApply:" + JSON.toJSONString( order ) );
		rechargeOrderService.insert( order );
		return WrapMapper.ok( "申请提交成功" );
	}

	/**
	 * 人工提现申请<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/30 10:02
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Wrapper<String> withdrawApply(WithdrawOrder withdrawOrder, SysUser user) {
		Long userId = withdrawOrder.getUserId();
		// 校验用户
		UserAccount account = new UserAccount();
		account.setUserId( userId );
		account = userAccountService.selectOne( new EntityWrapper<>( account ) );
		if (account == null) {
			return WrapMapper.error( "账户不存在" );
		}

		if (account.getBalance().compareTo( withdrawOrder.getAmount() ) < 0) {
			return WrapMapper.error( "可用余额不足！" );
		}

		account.setBalance( account.getBalance().subtract( withdrawOrder.getAmount() ) );// 余额减少
		account.setFrozenAmount( account.getFrozenAmount().add( withdrawOrder.getAmount() ) );// 冻结金额增加
		account.setUpdateTime( new Date() );
		log.info( "com.jdcloud.provider.service.impl.FinanceSettingServiceImpl.withdrawApply:" + JSON.toJSONString( account ) );
		int count = userAccountService.updateAccountById( account );
		if (count != 1) {
			return WrapMapper.error( "更新钱包失败！" );
		}

		// 获取汇率调整参数
		String exchangeRateAdjust = parameterConfigService.getValue( ConstantEnum.ParamConfigConstant.EXCHANGE_RATE_ADJUST.getCode() );
		// 查询汇率
		BigDecimal exchangeRate = this.getExchangeRate();
		log.info( "获取汇率：" + exchangeRate );
		withdrawOrder.setExchangeRate( exchangeRate.subtract( new BigDecimal( exchangeRateAdjust ) ) );// 设置汇率

		// 生成提现订单
		WithdrawOrder order = new WithdrawOrder();
		order.setWithdrawOrderId( "TX" + GeneralMethodUtils.generatorOrderId( userId.toString() ) );
		order.setUserId( userId );
		order.setAmount( withdrawOrder.getAmount() );
		order.setExchangeRate( withdrawOrder.getExchangeRate() );
		order.setExchangeAmount( withdrawOrder.getAmount().multiply( withdrawOrder.getExchangeRate() ).setScale( 2, BigDecimal.ROUND_DOWN ) );
		order.setType( Integer.valueOf( ConstantEnum.WithdrawConstant.TYPE_MANUAL.getCode() ) );
		order.setStatus( Integer.valueOf( ConstantEnum.WithdrawConstant.AUDITING.getCode() ) );
		order.setCreateTime( new Date() );
		log.info( "com.jdcloud.provider.service.impl.FinanceSettingServiceImpl.withdrawApply:" + JSON.toJSONString( order ) );
		withdrawOrderService.insert( order );

		return WrapMapper.ok( "申请提交成功" );
	}

	/**
	 * 用户查询<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/30 11:25
	 */
	@Override
	public Wrapper<List<UserAccount>> queryUser(String keyword) {
		List<UserAccount> list = userAccountService.queryUser( keyword );
		for (UserAccount userAccount : list) {
			if (userAccount.getRealName() == null) {
				userAccount.setRealName( "" );
			}
		}
		return WrapMapper.ok( list );
	}

	/**
	 * 获取蓝鲸汇率<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/10/27 17:00
	 */
	private BigDecimal getExchangeRate() {

		try {
			String url = parameterConfigService.getValue( ConstantEnum.LanjingConstant.EXCHANGE_RATE_URL.getCode() );
			HttpClientResult result = HttpClientUtils.doGet( url );
			if (Wrapper.SUCCESS_CODE != result.getCode()) {
				throw new RuntimeException( "获取汇率失败" );
			}
			ExchangeRate exchangeRateVo = JSON.parseObject( String.valueOf( result.getContent() ), ExchangeRate.class );
			if (GlobalConstant.Number.ZERO_0 != exchangeRateVo.getCode()) {
				throw new RuntimeException( "获取汇率失败" );
			}
			return new BigDecimal( exchangeRateVo.getExchange_rate() );
		} catch (Exception e) {
			// 获取蓝鲸汇率失败，获取备用配置
			log.error( "获取蓝鲸汇率失败" + e.getMessage() );
			// 获取备用汇率
			return parameterConfigService.getBigDecimalValue( ParameterConfigEnum.BACKUP_RATE.getKey() );
		}
	}

	/**
	 * 体验金设置<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/18 19:56
	 */
	@Override
	public TokenSetting queryTokenSetting() {
		TokenSetting tokenSetting = new TokenSetting();
		List<String> keys = new ArrayList<>();
		keys.add( ParameterConfigEnum.REGISTER_TOKEN.getKey() );
		keys.add( ParameterConfigEnum.TOKEN_ORDER_COUNT.getKey() );

		List<ParameterConfig> list = parameterConfigService.selectListByKeys( keys );
		for (ParameterConfig parameterConfig : list) {
			switch (parameterConfig.getDictionaryKey()) {
			case "registerToken":
				tokenSetting.setTokenAmount( new BigDecimal( parameterConfig.getDictionaryValue() ) );
				break;
			case "tokenOrderCount":
				tokenSetting.setTokenOrderCount( Integer.valueOf( parameterConfig.getDictionaryValue() ) );
				break;
			}
		}
		return tokenSetting;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Wrapper<String> tokenSettingEdit(TokenSetting tokenSetting, SysUser user) {

		Integer tokenOrderCount = parameterConfigService.getIntValue( ParameterConfigEnum.TOKEN_ORDER_COUNT.getKey() );
		if (tokenOrderCount.compareTo( tokenSetting.getTokenOrderCount() ) != 0) {
			// 修改体验金次数，用户的体验金下次次数全部清零
			UserAccount account = new UserAccount();
			account.setTokenNumber( GlobalConstant.Number.ZERO_0 );
			userAccountService.update( account, null );
		}

		List<ParameterConfig> list = new ArrayList<>();
		list.add( new ParameterConfig( ParameterConfigEnum.REGISTER_TOKEN.getKey(), tokenSetting.getTokenAmount().toString(), user.getUserName() ) );
		list.add( new ParameterConfig( ParameterConfigEnum.TOKEN_ORDER_COUNT.getKey(), tokenSetting.getTokenOrderCount().toString(),
				user.getUserName() ) );
		// 批量更新
		parameterConfigService.batchUpdate( list );

		// 删除redisKey
		String[] keys = new String[2];
		keys[ 0 ] = RedisKeyUtil.PARAMETER_CONFIG_PREF + ParameterConfigEnum.REGISTER_TOKEN.getKey();
		keys[ 1 ] = RedisKeyUtil.PARAMETER_CONFIG_PREF + ParameterConfigEnum.TOKEN_ORDER_COUNT.getKey();
		Wrapper<String> wrapper = commonsFeignApi.deleteRedisKeys( JSON.toJSONString( keys ) );
		if (wrapper == null) {
			throw new RuntimeException( "删除缓存失败！" );
		}
		return WrapMapper.ok();
	}
}