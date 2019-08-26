package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.constant.GlobalConstant;
import com.jdcloud.base.enums.ConstantEnum;
import com.jdcloud.base.enums.UserEnum;
import com.jdcloud.provider.dto.StatisticsDto;
import com.jdcloud.provider.dto.Withdraw;
import com.jdcloud.provider.dto.WithdrawDetail;
import com.jdcloud.provider.dto.WithdrawStatistics;
import com.jdcloud.provider.mapper.AmountFlowsMapper;
import com.jdcloud.provider.mapper.UserAccountMapper;
import com.jdcloud.provider.mapper.WithdrawOrderMapper;
import com.jdcloud.provider.model.dto.UserAuthenticationDto;
import com.jdcloud.provider.model.service.BizUseAccountFeignApi;
import com.jdcloud.provider.model.service.UacUserFeignApi;
import com.jdcloud.provider.pojo.*;
import com.jdcloud.provider.service.*;
import com.jdcloud.util.wrapper.WrapMapper;
import com.jdcloud.util.wrapper.Wrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 提现服务实现类
 * </p>
 *
 * @author helen
 * @since 2018-10-27
 */
@Service
@Slf4j
public class WithdrawOrderServiceImpl extends ServiceImpl<WithdrawOrderMapper, WithdrawOrder> implements WithdrawOrderService {

	@Autowired
	private UserAccountMapper			userAccountMapper;
	@Resource
	private BizUseAccountFeignApi		bizUseAccountFeignApi;
	@Resource
	private UacUserFeignApi				uacUserFeignApi;
	@Autowired
	private AmountFlowsMapper			amountFlowsMapper;
	@Autowired
	private AgntUserService				agntUserService;
	@Autowired
	private StatisticsService			statisticsService;
	@Autowired
	private ContractPerpetualService	contractPerpetualService;
	@Autowired
	private UserLabourAccountService	labourAccountService;

	/**
	 * 提现列表<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/26 10:27
	 */
	@Override
	public Page<Withdraw> selectWithdrawOrderList(Withdraw withdraw) {
		List<Withdraw> list = baseMapper.selectWithdrawOrderList( withdraw );
		Integer count = baseMapper.selectWithdrawOrderCount( withdraw );
		Page<Withdraw> page = new Page<>();
		page.setRecords( list );
		page.setSize( withdraw.getSize() );
		page.setCurrent( withdraw.getCurrent() );
		page.setTotal( count );
		return page;
	}

	/**
	 * 提现详情<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/26 16:03
	 */
	@Override
	public WithdrawDetail selectWithdrawDetail(String orderNo) {
		// 根据id查询提现订单
		WithdrawOrder withdrawOrder = baseMapper.selectById( orderNo );
		Long userId = withdrawOrder.getUserId();

		// 根据userId查询用户账户信息
		UserAccount account = new UserAccount();
		account.setUserId( userId );
		account = userAccountMapper.selectOne( account );

		// 根据userId查询用户认证信息
		Wrapper<UserAuthenticationDto> authenticationWrapper = uacUserFeignApi.queryUserAuthentication( userId );
		UserAuthenticationDto identity = authenticationWrapper.getResult();
		if (identity == null) {
			log.error( "查询用户认证信息失败" );
		}

		// 计算浮动盈亏
		Wrapper<BigDecimal> wrapper = bizUseAccountFeignApi.queryCalcFloatPL( userId );
		BigDecimal netValue = null;
		String riskRate = "-";
		if (wrapper == null || wrapper.getResult() == null) {
			log.error( "计算浮动盈亏失败！" );
		} else {
			// 计算净值 = 账户余额 + 浮动盈亏 + 占用保证金
			netValue = account.getBalance().add( account.getUsedMargin() ).add( wrapper.getResult() );
			Integer count = contractPerpetualService.selectContractPerpetualCount( userId );
			if (account.getUsedMargin().compareTo( BigDecimal.ZERO ) != 0 && count != 0) {
				// 计算风险率
				riskRate = netValue.divide( account.getUsedMargin(), 4, BigDecimal.ROUND_HALF_UP ).multiply( new BigDecimal( "100" ) ) + "%";
			}
		}

		// 充提统计
		StatisticsDto statistics = statisticsService.amountStatistics( userId );

		// 根据用户查询奖励金转入金额
		UserLabourAccount labourAccount = new UserLabourAccount();
		labourAccount.setUserId( userId );
		labourAccount = labourAccountService.selectOne( new EntityWrapper<>( labourAccount ) );

		// 封装信息
		WithdrawDetail detail = new WithdrawDetail();
		BeanUtils.copyProperties( account, detail );
		BeanUtils.copyProperties( withdrawOrder, detail );
		BeanUtils.copyProperties( statistics, detail );
		if (identity != null) {
			BeanUtils.copyProperties( identity, detail );
			detail.setAuthenticationStatus( identity.getFhasRealValidate().toString() );
		}
		detail.setRiskRate( riskRate );
		detail.setNetValue( netValue );
		detail.setOrderNo( orderNo );
		detail.setBountyAmount( labourAccount == null ? BigDecimal.ZERO : labourAccount.getTotalWithdraw() );
		detail.setWithdrawStatus( withdrawOrder.getStatus().toString() );
		AgntUser agntUser = agntUserService.selectAgntById( userId );
		if (agntUser != null) {
			detail.setRecommendName( agntUser.getAgntName() );
		}

		//账号类型
		detail.setAccountType("普通账号");
		if (account.getInternalAccount() == UserEnum.Internal.INTERNAL_ACCOUNT.getCode()) {
			detail.setAccountType("内部账号");
		}

		return detail;
	}

	/**
	 * <br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/26 20:00
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Wrapper<String> withdrawAudit(Withdraw withdraw, SysUser user) {
		// 根据id查询订单
		WithdrawOrder withdrawOrder = baseMapper.selectById( withdraw.getOrderNo() );
		if (!ConstantEnum.WithdrawConstant.AUDITING.getCode().equals( withdrawOrder.getStatus().toString() )) {
			return WrapMapper.error( withdraw.getOrderNo() + "订单不能审核！" );
		}

		// 根据userId查询用户账户信息
		UserAccount account = new UserAccount();
		account.setUserId( withdrawOrder.getUserId() );
		account = userAccountMapper.selectOne( account );

		if (ConstantEnum.WithdrawConstant.AUDIT_PASS.getCode().equals( withdraw.getStatus() )) {
			// 审核通过
			withdrawOrder.setStatus( Integer.valueOf( ConstantEnum.WithdrawConstant.AUDIT_PASS.getCode() ) );
			BigDecimal beforeWithdraw = account.getBalance().add( account.getFrozenAmount() ).add( account.getUsedMargin() );

			account.setFrozenAmount( account.getFrozenAmount().subtract( withdrawOrder.getAmount() ) );
			account.setTotalWithdrawals( account.getTotalWithdrawals().add( withdrawOrder.getAmount() ) );
			account.setUpdateTime( new Date() );

			// 生成钱包流水
			AmountFlows amountFlows = new AmountFlows();
			amountFlows.setOrderId( withdrawOrder.getWithdrawOrderId() );
			amountFlows.setBeforeAmount( beforeWithdraw );
			amountFlows.setAmount( withdrawOrder.getAmount() );
			amountFlows.setAfterAmount( beforeWithdraw.subtract( withdrawOrder.getAmount() ) );
			amountFlows.setCreateTime( new Date() );
			amountFlows.setRemark( ConstantEnum.AmountFlowsConstant.WITHDRAW.getValue() );
			amountFlows.setType( Integer.valueOf( ConstantEnum.AmountFlowsConstant.WITHDRAW.getCode() ) );
			amountFlows.setUserId( withdrawOrder.getUserId() );
			amountFlows.setTokenType( GlobalConstant.Number.ONE_1 );
			amountFlowsMapper.insert( amountFlows );

		} else if (ConstantEnum.WithdrawConstant.AUDIT_NOT_PASS.getCode().equals( withdraw.getStatus() )) {
			// 审核拒绝
			withdrawOrder.setStatus( Integer.valueOf( ConstantEnum.WithdrawConstant.AUDIT_NOT_PASS.getCode() ) );
			account.setBalance( account.getBalance().add( withdrawOrder.getAmount() ) );
			account.setFrozenAmount( account.getFrozenAmount().subtract( withdrawOrder.getAmount() ) );
			account.setUpdateTime( new Date() );
		} else {
			return WrapMapper.error( withdraw.getOrderNo() + "订单审核状态有误！" );
		}

		int count = userAccountMapper.updateAccountById( account );
		if (count != GlobalConstant.Number.ONE_1) {
			return WrapMapper.error( withdraw.getOrderNo() + "订单更新钱包失败！" );
		}

		withdrawOrder.setRemark( withdraw.getRemark() );
		withdrawOrder.setAuditor( user.getUserName() );
		withdrawOrder.setAuditTime( new Date() );
		withdrawOrder.setUpdateTime( new Date() );
		baseMapper.updateById( withdrawOrder );

		return WrapMapper.ok();
	}

	/**
	 * 充值记录列表<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/28 18:40
	 */
	@Override
	public Page<Withdraw> selectWithdrawRecordList(Withdraw withdraw) {
		withdraw.setStatus( ConstantEnum.WithdrawConstant.AUDIT_PASS.getCode() );
		return this.selectWithdrawOrderList( withdraw );
	}

	/**
	 * 提现记录统计 <br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/28 18:43
	 */
	@Override
	public WithdrawStatistics withdrawStatistics() {
		WithdrawStatistics withdrawStatistics = new WithdrawStatistics();
		// 记录统计
		Withdraw withdraw = new Withdraw();
		withdraw.setStatus( ConstantEnum.WithdrawConstant.AUDIT_PASS.getCode() );
		List<WithdrawStatistics> list = baseMapper.selectWithdrawStatistics( ConstantEnum.WithdrawConstant.AUDIT_PASS.getCode() );
		// 数据处理
		for (WithdrawStatistics statistics : list) {
			if (ConstantEnum.WithdrawConstant.TYPE_CUSTOMER.getCode().equals( statistics.getType().toString() )) {
				withdrawStatistics.setTotalUserWithdrawCNY( statistics.getTotalUserWithdrawCNY() );
				withdrawStatistics.setTotalUserWithdrawUSDT( statistics.getTotalUserWithdrawUSDT() );
			} else if (ConstantEnum.WithdrawConstant.TYPE_MANUAL.getCode().equals( statistics.getType().toString() )) {
				withdrawStatistics.setTotalManualWithdraw( statistics.getTotalUserWithdrawUSDT() );
			}
			withdrawStatistics.setWithdrawCount( withdrawStatistics.getWithdrawCount() + statistics.getWithdrawCount() );
		}
		return withdrawStatistics;
	}

	/**
	 * 充值记录导出<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/11/28 18:11
	 */
	@Override
	public List<Withdraw> selectWithdrawRecordExportList(Withdraw withdraw) {
		Integer count = baseMapper.selectWithdrawOrderCount( withdraw );
		withdraw.setCurrent( 1 );
		withdraw.setSize( count );
		withdraw.setStatus( ConstantEnum.WithdrawConstant.AUDIT_PASS.getCode() );
		List<Withdraw> list = baseMapper.selectWithdrawOrderList( withdraw );
		for (Withdraw order : list) {
			if (ConstantEnum.WithdrawConstant.TYPE_CUSTOMER.getCode().equals( order.getOrderType() )) {
				order.setOrderType( ConstantEnum.WithdrawConstant.TYPE_CUSTOMER.getValue() );
			} else if (ConstantEnum.WithdrawConstant.TYPE_MANUAL.getCode().equals( order.getOrderType() )) {
				order.setOrderType( ConstantEnum.WithdrawConstant.TYPE_MANUAL.getValue() );
			}
			String internalAccountStr = UserEnum.Internal.INTERNAL_ACCOUNT.getCode().toString().equals( order.getInternalAccount() ) ? "是" : "否";
			order.setInternalAccount( internalAccountStr );
		}
		return list;
	}

	/**
	 * 用户提现统计<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/19 11:13
	 */
	@Override
	public StatisticsDto statisticsByUserId(Long userId) {
		StatisticsDto statisticsDto = new StatisticsDto();
		List<StatisticsDto> list = baseMapper.statisticsByUserId( userId );
		// 数据处理
		for (StatisticsDto statistics : list) {
			if (ConstantEnum.WithdrawConstant.TYPE_CUSTOMER.getCode().equals( statistics.getType() )) {
				statisticsDto.setTotalUserWithdrawCNY( statistics.getTotalUserWithdrawCNY() );
				statisticsDto.setTotalUserWithdrawUSDT( statistics.getWithdrawAmount() );
			} else if (ConstantEnum.WithdrawConstant.TYPE_MANUAL.getCode().equals( statistics.getType() )) {
				statisticsDto.setTotalManualWithdraw( statistics.getWithdrawAmount() );
			}
		}
		return statisticsDto;
	}

	/**
	 * 充值待审核数<br>
	 * 
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/19 16:32
	 */
	@Override
	public Integer queryAuditingCount() {
		return baseMapper.queryAuditingCount( ConstantEnum.WithdrawConstant.AUDITING.getCode() );
	}
}
