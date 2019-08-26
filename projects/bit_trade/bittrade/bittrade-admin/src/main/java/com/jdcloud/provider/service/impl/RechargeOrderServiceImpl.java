package com.jdcloud.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.constant.GlobalConstant;
import com.jdcloud.base.enums.ConstantEnum;
import com.jdcloud.base.enums.ParameterConfigEnum;
import com.jdcloud.base.enums.UserEnum;
import com.jdcloud.core.redis.RedisKeyUtil;
import com.jdcloud.provider.dto.Recharge;
import com.jdcloud.provider.dto.RechargeStatistics;
import com.jdcloud.provider.dto.StatisticsDto;
import com.jdcloud.provider.mapper.AmountFlowsMapper;
import com.jdcloud.provider.mapper.RechargeOrderMapper;
import com.jdcloud.provider.mapper.UserAccountMapper;
import com.jdcloud.provider.model.service.BizCommonsFeignApi;
import com.jdcloud.provider.pojo.*;
import com.jdcloud.provider.pojo.vo.RechargeConfigVo;
import com.jdcloud.provider.service.ParameterConfigService;
import com.jdcloud.provider.service.PushService;
import com.jdcloud.provider.service.RechargeOrderService;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yongheng
 * @since 2018-10-25
 */
@Service
public class RechargeOrderServiceImpl extends ServiceImpl<RechargeOrderMapper, RechargeOrder> implements RechargeOrderService {

	@Autowired
	private UserAccountMapper		userAccountMapper;

	@Autowired
	private AmountFlowsMapper		amountFlowsMapper;

	@Autowired
	private PushService				pushService;

	@Autowired
	private ParameterConfigService	parameterConfigService;

	@Autowired
	private BizCommonsFeignApi		commonsFeignApi;

	/**
	 * 分页查询充值订单列表<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/23 16:37
	 */
	@Override
	public Page<Recharge> selectRechargeOrderList(Recharge recharge) {
		List<Recharge> list = baseMapper.selectRechargeOrderList( recharge );
		Integer count = baseMapper.selectRechargeOrderCount( recharge );
		Page<Recharge> page = new Page<>();
		page.setRecords( list );
		page.setSize( recharge.getSize() );
		page.setCurrent( recharge.getCurrent() );
		page.setTotal( count );
		return page;
	}

	/**
	 * 充值审核通过<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/24 15:35
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Wrapper<String> auditPass(String[] orderNos, SysUser user) {
		List<String> list = Arrays.asList( orderNos );
		List<RechargeOrder> rechargeOrders = baseMapper.selectBatchIds( list );
		for (RechargeOrder rechargeOrder : rechargeOrders) {
			if (Integer.valueOf( ConstantEnum.RechargeConstant.STATUS_AUDITING.getCode() ) != rechargeOrder.getStatus()) {
				// return WrapMapper.error( rechargeOrder.getOrderNo() + "订单审核不通过！" );
				throw new RuntimeException( rechargeOrder.getOrderNo() + "订单审核不通过！" );
			}
			// 修改充值订单表
			rechargeOrder.setStatus( Integer.valueOf( ConstantEnum.RechargeConstant.STATUS_AUDIT_PASS.getCode() ) );
			rechargeOrder.setAuditor( user.getUserName() );
			rechargeOrder.setAuditTime( new Date() );
			rechargeOrder.setUpdateTime( new Date() );
			rechargeOrder.setRemark( "审核通过" );
			if (ConstantEnum.RechargeConstant.TYPE_MANUAL_TOKEN.getCode().equals( rechargeOrder.getType().toString() )) {
				rechargeOrder.setRemark( "赠送体验金" );
			}
			baseMapper.updateById( rechargeOrder );

			// 查询用户账户
			UserAccount userAccount = new UserAccount();
			userAccount.setUserId( rechargeOrder.getUserId() );
			userAccount = userAccountMapper.selectOne( userAccount );
			// 充值前金额
			BigDecimal beforeRecharge = userAccount.getBalance().add( userAccount.getFrozenAmount() ).add( userAccount.getUsedMargin() );
			// 充值金额
			BigDecimal exchangeAmount = rechargeOrder.getExchangeAmount();
			// 资金流水币种
			Integer tokenType = GlobalConstant.Number.ONE_1;// 默认：USDT
			if (ConstantEnum.RechargeConstant.TYPE_MANUAL_TOKEN.getCode().equals( rechargeOrder.getType().toString() )) {
				beforeRecharge = userAccount.getBalanceToken().add( userAccount.getUsedToken() );
				userAccount.setBalanceToken( userAccount.getBalanceToken().add( exchangeAmount ) );
				userAccount.setTotalRechargeToken( userAccount.getTotalRechargeToken().add( exchangeAmount ) );
				userAccount.setTokenNumber( GlobalConstant.Number.ZERO_0 );
				tokenType = GlobalConstant.Number.TWO_2;
			} else {
				userAccount.setBalance( userAccount.getBalance().add( exchangeAmount ) );
				userAccount.setTotalDeposits( userAccount.getTotalDeposits().add( exchangeAmount ) );
			}

			// 人工充值标记为内部账户
			if (ConstantEnum.RechargeConstant.TYPE_MANUAL.getCode().equals( rechargeOrder.getType().toString() )) {
				userAccount.setInternalAccount( UserEnum.Internal.INTERNAL_ACCOUNT.getCode() );
			}

			userAccount.setUpdateTime( new Date() );
			int count = userAccountMapper.updateAccountById( userAccount );
			if (count != GlobalConstant.Number.ONE_1) {
				return WrapMapper.error( rechargeOrder.getOrderNo() + "订单更新钱包失败！" );
			}

			// 生成钱包流水
			AmountFlows amountFlows = new AmountFlows();
			amountFlows.setOrderId( rechargeOrder.getOrderNo() );
			amountFlows.setBeforeAmount( beforeRecharge );
			amountFlows.setAmount( exchangeAmount );
			amountFlows.setAfterAmount( beforeRecharge.add( exchangeAmount ) );
			amountFlows.setCreateTime( new Date() );
			amountFlows.setRemark( ConstantEnum.AmountFlowsConstant.RECHARGE.getValue() );
			amountFlows.setType( Integer.valueOf( ConstantEnum.AmountFlowsConstant.RECHARGE.getCode() ) );
			amountFlows.setUserId( rechargeOrder.getUserId() );
			amountFlows.setTokenType( tokenType );

			amountFlowsMapper.insert( amountFlows );

			// 增加推送
			Push push = new Push();
			push.setType( ConstantEnum.PushConstant.TYPE_RECHARGE.getCode() );
			push.setTel( userAccount.getLoginName() );
			push.setAmount( exchangeAmount );
			push.setCreateTime( new Date() );
			pushService.insert( push );
		}
		return WrapMapper.ok();
	}

	/**
	 * 充值审核拒绝<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/24 18:52
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Wrapper<String> auditRefuse(RechargeOrder rechargeOrder, SysUser user) {
		RechargeOrder recharge = baseMapper.selectById( rechargeOrder.getOrderNo() );
		if (Integer.valueOf( ConstantEnum.RechargeConstant.STATUS_AUDITING.getCode() ) != recharge.getStatus()) {
			return WrapMapper.error( rechargeOrder.getOrderNo() + "订单不能审核！" );
		}
		recharge.setRemark( rechargeOrder.getRemark() );
		recharge.setAuditTime( new Date() );
		recharge.setAuditor( user.getUserName() );
		recharge.setUpdateTime( new Date() );
		recharge.setStatus( Integer.valueOf( ConstantEnum.RechargeConstant.STATUS_NOT_PASS.getCode() ) );
		baseMapper.updateById( recharge );
		return WrapMapper.ok();
	}

	/**
	 * 金额修正<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/28 11:12
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Wrapper<String> correctedAmount(RechargeOrder rechargeOrder, SysUser user) {
		RechargeOrder recharge = baseMapper.selectById( rechargeOrder.getOrderNo() );
		if (Integer.valueOf( ConstantEnum.RechargeConstant.STATUS_AUDITING.getCode() ) != recharge.getStatus()) {
			return WrapMapper.error( rechargeOrder.getOrderNo() + "订单不能编辑！" );
		}

		if (Integer.valueOf( ConstantEnum.RechargeConstant.TYPE_CUSTOMER.getCode() ) != recharge.getType()) {
			return WrapMapper.error( rechargeOrder.getOrderNo() + "订单不是用户充值订单，不能编辑！" );
		}

		recharge.setExchangeAmount( rechargeOrder.getExchangeAmount() );
		BigDecimal rate = recharge.getOrderAmount().divide( rechargeOrder.getExchangeAmount(), 2, BigDecimal.ROUND_DOWN );
		recharge.setExchangeRate( rate );
		recharge.setUpdateTime( new Date() );
		baseMapper.updateById( recharge );
		return WrapMapper.ok();
	}

	/**
	 * 充值记录列表<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/28 16:02
	 */
	@Override
	public Page<Recharge> selectRechargeRecordList(Recharge recharge) {
		recharge.setStatus( ConstantEnum.RechargeConstant.STATUS_AUDIT_PASS.getCode() );
		return this.selectRechargeOrderList( recharge );
	}

	/**
	 * 充值记录统计<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/28 16:37
	 */
	@Override
	public RechargeStatistics rechargeStatistics() {

		RechargeStatistics rechargeStatistics = new RechargeStatistics();
		// 记录统计
		Recharge recharge = new Recharge();
		recharge.setStatus( ConstantEnum.RechargeConstant.STATUS_AUDIT_PASS.getCode() );
		List<RechargeStatistics> list = baseMapper.selectRechargeStatistics( ConstantEnum.RechargeConstant.STATUS_AUDIT_PASS.getCode() );
		// 数据处理
		for (RechargeStatistics statistics : list) {
			if (ConstantEnum.RechargeConstant.TYPE_CUSTOMER.getCode().equals( statistics.getType().toString() )) {
				rechargeStatistics.setTotalUserRechargeCNY( statistics.getTotalUserRechargeCNY() );
				rechargeStatistics.setTotalUserRechargeUSDT( statistics.getTotalUserRechargeUSDT() );
			} else if (ConstantEnum.RechargeConstant.TYPE_MANUAL.getCode().equals( statistics.getType().toString() )) {
				rechargeStatistics.setTotalManualRecharge( statistics.getTotalUserRechargeUSDT() );
			} else if (ConstantEnum.RechargeConstant.TYPE_MANUAL_TOKEN.getCode().equals( statistics.getType().toString() )) {
				rechargeStatistics.setTotalManualRechargeToken( statistics.getTotalUserRechargeUSDT() );
			} else if (ConstantEnum.RechargeConstant.LABOUR_CHANGE_INTO.getCode().equals( statistics.getType().toString() )) {
				rechargeStatistics.setLabourChangeInto( statistics.getTotalUserRechargeUSDT() );
			}
			rechargeStatistics.setRechargeCount( rechargeStatistics.getRechargeCount() + statistics.getRechargeCount() );
		}
		return rechargeStatistics;
	}

	/**
	 * 充值记录导出<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/28 18:11
	 */
	@Override
	public List<Recharge> selectRechargeRecordExportList(Recharge recharge) {
		Integer count = baseMapper.selectRechargeOrderCount( recharge );
		recharge.setCurrent( 1 );
		recharge.setSize( count );
		recharge.setStatus( ConstantEnum.RechargeConstant.STATUS_AUDIT_PASS.getCode() );
		List<Recharge> list = baseMapper.selectRechargeOrderList( recharge );
		for (Recharge order : list) {
			if (ConstantEnum.RechargeConstant.TYPE_CUSTOMER.getCode().equals( order.getOrderType() )) {
				order.setOrderType( ConstantEnum.RechargeConstant.TYPE_CUSTOMER.getValue() );
			} else if (ConstantEnum.RechargeConstant.TYPE_MANUAL.getCode().equals( order.getOrderType() )) {
				order.setOrderType( ConstantEnum.RechargeConstant.TYPE_MANUAL.getValue() );
			} else if (ConstantEnum.RechargeConstant.TYPE_MANUAL_TOKEN.getCode().equals( order.getOrderType() )) {
				order.setOrderType( ConstantEnum.RechargeConstant.TYPE_MANUAL_TOKEN.getValue() );
			} else if (ConstantEnum.RechargeConstant.LABOUR_CHANGE_INTO.getCode().equals( order.getOrderType() )) {
				order.setOrderType( ConstantEnum.RechargeConstant.LABOUR_CHANGE_INTO.getValue() );
			}
			String internalAccountStr = UserEnum.Internal.INTERNAL_ACCOUNT.getCode().toString().equals( order.getInternalAccount() ) ? "是" : "否";
			order.setInternalAccount( internalAccountStr );
		}
		return list;
	}

	/**
	 * 用户充值统计<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/19 11:00
	 */
	@Override
	public StatisticsDto statisticsByUserId(Long userId) {
		StatisticsDto statisticsDto = new StatisticsDto();
		List<StatisticsDto> list = baseMapper.statisticsByUserId( userId );
		// 数据处理
		for (StatisticsDto statistics : list) {
			if (ConstantEnum.RechargeConstant.TYPE_CUSTOMER.getCode().equals( statistics.getType() )) {
				statisticsDto.setTotalUserRechargeCNY( statistics.getTotalUserRechargeCNY() );
				statisticsDto.setTotalUserRechargeUSDT( statistics.getRechargeAmount() );
			} else if (ConstantEnum.RechargeConstant.TYPE_MANUAL.getCode().equals( statistics.getType() )) {
				statisticsDto.setTotalManualRecharge( statistics.getRechargeAmount() );
			}
		}
		return statisticsDto;
	}

	/**
	 * 查询充值待审核数<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/19 16:25
	 */
	@Override
	public Integer queryAuditingCount() {
		return baseMapper.queryAuditingCount( ConstantEnum.RechargeConstant.STATUS_AUDITING.getCode() );
	}

	/**
	 * 跳转自动审核设置页面<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/8 17:01
	 */
	@Override
	public RechargeConfigVo goRechargeConfig() {
		String value = parameterConfigService.getValue( ParameterConfigEnum.RECHARGE_CONFIG.getKey() );
		RechargeConfigVo rechargeConfigVo = JSON.parseObject( value, RechargeConfigVo.class );
		return rechargeConfigVo;
	}

	/**
	 * 修改自动充值设置<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2019/1/8 17:04
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Wrapper<String> rechargeConfig(RechargeConfigVo rechargeConfigVo, SysUser user) {
		ParameterConfig parameterConfig = new ParameterConfig();
		parameterConfig.setDictionaryKey( ParameterConfigEnum.RECHARGE_CONFIG.getKey() );
		parameterConfig = parameterConfigService.selectOne( new EntityWrapper<>( parameterConfig ) );

		parameterConfig.setDictionaryValue( JSON.toJSONString( rechargeConfigVo ) );
		parameterConfig.setUpdateTime( new Date() );
		parameterConfig.setUpdater( user.getUserName() );
		parameterConfigService.updateById( parameterConfig );

		// 删除缓存
		String[] keys = { RedisKeyUtil.PARAMETER_CONFIG_PREF + parameterConfig.getDictionaryKey() };
		Wrapper<String> wrapper = commonsFeignApi.deleteRedisKeys( JSON.toJSONString( keys ) );
		if (wrapper == null) {
			throw new RuntimeException( "删除缓存失败！" );
		}
		return WrapMapper.ok();
	}
}
