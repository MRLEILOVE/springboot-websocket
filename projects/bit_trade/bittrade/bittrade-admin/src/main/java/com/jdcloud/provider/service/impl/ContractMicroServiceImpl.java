package com.jdcloud.provider.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jdcloud.base.enums.UserEnum;
import com.jdcloud.provider.mapper.ContractMicroMapper;
import com.jdcloud.provider.pojo.ContractMicro;
import com.jdcloud.provider.service.ContractMicroService;
import com.jdcloud.util.BigDecimalUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * <p>
 * 微盘合约订单 服务实现类
 * </p>
 *
 * @author helen
 * @since 2018-10-29
 */
@Service
public class ContractMicroServiceImpl extends ServiceImpl<ContractMicroMapper, ContractMicro> implements ContractMicroService {

	@Override
	public Page<ContractMicro> selectContractMicroList(Page<ContractMicro> page, ContractMicro micro) {
		Map<String, Object> map = micro.getParams();
		if (map != null && map.get( "condition" ) != null) {
			Pattern pattern = Pattern.compile( "^[-\\+]?[\\d]*$" );
			String condition = (String) map.get( "condition" );
			if (pattern.matcher( condition ).matches()) {
				map.put( "type", 1 );
			} else {
				map.put( "type", 2 );
			}
		}
		micro.setParams( map );
		page.setRecords( baseMapper.selectContractMicroList( page, micro ) );
		return page;
	}

	@Override
	public List<ContractMicro> selectContractMicroExportList(ContractMicro micro) {
		Map<String, Object> map = micro.getParams();
		if (map != null && map.get( "condition" ) != null) {
			Pattern pattern = Pattern.compile( "^[-\\+]?[\\d]*$" );
			String condition = (String) map.get( "condition" );
			if (pattern.matcher( condition ).matches()) {
				map.put( "type", 1 );
			} else {
				map.put( "type", 2 );
			}
		}
		micro.setParams( map );
		List<ContractMicro> micros = baseMapper.selectContractMicroList( micro );
		for (ContractMicro m : micros) {
			if (m.getDirection() == 1)
				m.setDirectionStr( "买跌" );
			if (m.getDirection() == 2)
				m.setDirectionStr( "买涨" );
			m.setProfitLoss( BigDecimalUtil.turnDown( m.getProfitLoss(), 2 ) );
			m.setUsedMargin( BigDecimalUtil.turnDown( m.getUsedMargin(), 2 ) );
			String internalAccountStr = UserEnum.Internal.INTERNAL_ACCOUNT.getCode() == m.getInternalAccount() ? "是" : "否";
			m.setInternalAccountStr( internalAccountStr );
		}
		return micros;
	}
}
