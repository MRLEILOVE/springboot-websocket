package com.jdcloud.provider.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.constant.GlobalConstant;
import com.jdcloud.base.enums.ConstantEnum;
import com.jdcloud.base.enums.UserEnum;
import com.jdcloud.core.redis.RedisTemplate;
import com.jdcloud.provider.mapper.ContractPerpetualMapper;
import com.jdcloud.provider.model.service.BizContractPerpetualApi;
import com.jdcloud.provider.pojo.ContractPerpetual;
import com.jdcloud.provider.service.ContractPerpetualService;
import com.jdcloud.provider.service.ProductPerpetualService;
import com.jdcloud.util.BigDecimalUtil;
import com.jdcloud.util.wrapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * <p>
 * 永续合约交易表 服务实现类
 * </p>
 *
 * @author helen
 * @since 2018-11-22
 */
@Service
public class ContractPerpetualServiceImpl extends ServiceImpl<ContractPerpetualMapper, ContractPerpetual> implements ContractPerpetualService {

	@Autowired
	private ProductPerpetualService	productPerpetualService;

	@Autowired
	private BizContractPerpetualApi	bizContractPerpetualApi;

	@Autowired
	private RedisTemplate			redisTemplate;

	@Override
	public Page<ContractPerpetual> selectContractPerpetualList(Page<ContractPerpetual> page, ContractPerpetual perpetual) {
		Map<String, Object> map = perpetual.getParams();
		if (map != null && map.get( "condition" ) != null) {
			Pattern pattern = Pattern.compile( "^[-\\+]?[\\d]*$" );
			String condition = (String) map.get( "condition" );
			if (pattern.matcher( condition ).matches()) {
				map.put( "type", 1 );
			} else {
				map.put( "type", 2 );
			}
		}
		perpetual.setParams( map );
		List<ContractPerpetual> perpetuals = baseMapper.selectContractPerpetualList( page, perpetual );
		page.setRecords( getList( perpetuals, perpetual.getStatus() ) );
		return page;
	}

	@Override
	public Page<ContractPerpetual> selectContractPerpetualHistoryList(Page<ContractPerpetual> page, ContractPerpetual perpetual) {
		Map<String, Object> map = perpetual.getParams();
		if (map != null && map.get( "condition" ) != null) {
			Pattern pattern = Pattern.compile( "^[-\\+]?[\\d]*$" );
			String condition = (String) map.get( "condition" );
			if (pattern.matcher( condition ).matches()) {
				map.put( "type", 1 );
			} else {
				map.put( "type", 2 );
			}
		}
		perpetual.setParams( map );
		List<ContractPerpetual> perpetuals = baseMapper.selectContractPerpetualList( page, perpetual );
		page.setRecords( getList( perpetuals, perpetual.getStatus() ) );
		return page;
	}

	@Override
	public List<ContractPerpetual> selectContractPerpetualExportList(ContractPerpetual perpetual) {
		Map<String, Object> map = perpetual.getParams();
		if (map != null && map.get( "condition" ) != null) {
			Pattern pattern = Pattern.compile( "^[-\\+]?[\\d]*$" );
			String condition = (String) map.get( "condition" );
			if (pattern.matcher( condition ).matches()) {
				map.put( "type", 1 );
			} else {
				map.put( "type", 2 );
			}
		}
		perpetual.setParams( map );
		List<ContractPerpetual> perpetuals = baseMapper.selectContractPerpetualList( perpetual );
		return getList( perpetuals, perpetual.getStatus() );
	}

	@Override
	public ContractPerpetual selectContractPerpetualById(String orderId) {
		return baseMapper.selectContractPerpetualById( orderId );
	}

	/**
	 * 合约订单集合计算
	 * 
	 * @param perpetuals
	 * @param type
	 * @return
	 */
	public List<ContractPerpetual> getList(List<ContractPerpetual> perpetuals, int type) {
		if (perpetuals != null) {
			if (type == 1) {
				JSONArray jsons = new JSONArray();
				for (ContractPerpetual p : perpetuals) {
					jsons.add( p.getOrderId() );
				}
				Wrapper wrapper = bizContractPerpetualApi.selectPerpetual( jsons.toString() );
				if (wrapper.getCode() == 200) {
					Map<String, Object> lossmap = new HashMap<>();
					Map<String, Object> riskMap = new HashMap<>();
					Map<String, Object> idMap = (Map<String, Object>) wrapper.getResult();
					lossmap = (Map<String, Object>) idMap.get( "lossmap" );
					riskMap = (Map<String, Object>) idMap.get( "riskmap" );
					for (ContractPerpetual p : perpetuals) {
						BigDecimal profitLoss = BigDecimal.ZERO;
						BigDecimal risk = BigDecimal.ZERO;
						if (lossmap.get( p.getOrderId() ) != null) {
							profitLoss = new BigDecimal( lossmap.get( p.getOrderId() ).toString() );
						}
						if (riskMap.get( p.getUserId().toString() ) != null) {
							risk = new BigDecimal( riskMap.get( p.getUserId().toString() ).toString() );
						}
						p.setProfitLoss( profitLoss );
						BigDecimal lossRatio = profitLoss.divide( p.getUsedMargin(), GlobalConstant.Number.FOUR_4, BigDecimal.ROUND_DOWN );
						lossRatio = lossRatio.multiply( BigDecimal.valueOf( 100 ) );
						lossRatio = BigDecimalUtil.turnDown( lossRatio, 2 );
						String lossStr = lossRatio.toString() + "%";
						p.setProfitLossRatio( lossStr );
						p.setUserAccountRisk( risk );
						// Excel导出
						if (p.getDirection() == 1)
							p.setDirectionStr( "买跌" );
						if (p.getDirection() == 2)
							p.setDirectionStr( "买涨" );
						if (p.getStatus() == 1)
							p.setStatusStr( "建仓" );

						String internalAccountStr = UserEnum.Internal.INTERNAL_ACCOUNT.getCode() == p.getInternalAccount() ? "是" : "否";
						p.setInternalAccountStr( internalAccountStr );
					}
				}
			} else {
				for (ContractPerpetual p : perpetuals) {
					BigDecimal lossRatio = p.getProfitLoss().divide( p.getUsedMargin(), GlobalConstant.Number.FOUR_4, BigDecimal.ROUND_DOWN );
					lossRatio = lossRatio.multiply( BigDecimal.valueOf( 100 ) );
					lossRatio = BigDecimalUtil.turnDown( lossRatio, 2 );
					String lossStr = lossRatio.toString() + "%";
					p.setProfitLossRatio( lossStr );
					p.setServiceFee( p.getServiceFee().add( p.getCloseFee() ) );
					// Excel导出
					if (p.getDirection() == 1)
						p.setDirectionStr( "买跌" );
					if (p.getDirection() == 2)
						p.setDirectionStr( "买涨" );
					if (p.getStatus() == 2)
						p.setStatusStr( "平仓" );
					if (p.getStatus() == 3)
						p.setStatusStr( "爆仓" );

					String internalAccountStr = UserEnum.Internal.INTERNAL_ACCOUNT.getCode() == p.getInternalAccount() ? "是" : "否";
					p.setInternalAccountStr( internalAccountStr );
				}
			}
		}
		return perpetuals;
	}

	/**
	 * 查询永续合约持仓数量<br>
	 *
	 * @return:
	 * @Author: yongheng
	 * @Date: 2018/12/27 21:54
	 */
	@Override
	public Integer selectContractPerpetualCount(Long userId) {
		ContractPerpetual perpetual = new ContractPerpetual();
		perpetual.setUserId( userId );
		perpetual.setStatus( UserEnum.OrderState.OPEN_ORDER.getCode() );
		perpetual.setTokenOrder( ConstantEnum.OrderType.COMMON_ORDER.getCode() );
		Integer count = baseMapper.selectCount( new EntityWrapper<>( perpetual ) );
		return count == null ? 0 : count;
	}
}
